//
// Created by BONNe
// Copyright - 2025
//


package com.jaquadro.minecraft.storagedrawersextra.data;


import com.google.common.collect.Multimap;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.jaquadro.minecraft.storagedrawersextra.StorageDrawersExtra;
import com.jaquadro.minecraft.storagedrawersextra.block.VariantRegistry;
import com.mojang.serialization.JsonOps;
import com.mojang.serialization.Lifecycle;
import org.jetbrains.annotations.NotNull;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.CompletableFuture;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.Util;
import net.minecraft.core.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ProblemReporter;
import net.minecraft.world.RandomSequence;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.RandomSupport;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.ExplosionCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition;


public class ModLootTableProvider extends LootTableProvider
{
    public ModLootTableProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider)
    {
        super(output, Set.of(), List.of(new SubProviderEntry(ConditionalBlockLootTable::new, LootContextParamSets.BLOCK)), lookupProvider);
        this.registries = lookupProvider;
        this.pathProvider = output.createRegistryElementsPathProvider(Registries.LOOT_TABLE);
        ModLootTableProvider.CONDITION_MAP.clear();
    }

//    @NotNull
//    public CompletableFuture<?> run(@NotNull CachedOutput cachedOutput)
//    {
//        return this.registries.thenCompose(provider -> this.run(cachedOutput, provider));
//    }


    private CompletableFuture<?> run(CachedOutput cachedOutput, HolderLookup.Provider provider)
    {
        WritableRegistry<LootTable> writableregistry =
            new MappedRegistry<>(Registries.LOOT_TABLE, Lifecycle.experimental());
        Map<RandomSupport.Seed128bit, Identifier> map = new Object2ObjectOpenHashMap<>();

        this.getTables().forEach(entry ->
            entry.provider().
                apply(provider).
                generate((tableKey, builder) ->
                {
                    Identifier tableLocation = tableKey.location();
                    Identifier randomSequence = map.put(RandomSequence.seedForKey(tableLocation), tableLocation);

                    if (randomSequence != null)
                    {
                        Util.logAndPauseIfInIde("Loot table random sequence seed collision on "
                            + randomSequence + " and " + tableKey.location());
                    }

                    builder.setRandomSequence(tableLocation);
                    LootTable loottable = builder.setParamSet(entry.paramSet()).build();

                    writableregistry.register(tableKey, loottable, RegistrationInfo.BUILT_IN);
                })
        );

        writableregistry.freeze();
        ProblemReporter.Collector reportCollector = new ProblemReporter.Collector();

        HolderGetter.Provider holdergetter$provider =
            new RegistryAccess.ImmutableRegistryAccess(List.of(writableregistry)).freeze();


        ValidationContext validationcontext = new ValidationContext(reportCollector,
            LootContextParamSets.ALL_PARAMS,
            holdergetter$provider);

        this.validate(writableregistry, validationcontext, reportCollector);
        Multimap<String, String> validationMap = reportCollector.get();

        if (!validationMap.isEmpty())
        {
            validationMap.forEach((key, value) -> StorageDrawersExtra.LOGGER.warn("Found validation problem in {}: {}", key, value));
            throw new IllegalStateException("Failed to validate loot tables, see logs");
        }
        else
        {
            RegistryOps<JsonElement> ops = provider.createSerializationContext(JsonOps.INSTANCE);

            return CompletableFuture.allOf(writableregistry.entrySet().stream().map(lootTableEntry ->
            {
                ResourceKey<LootTable> lootTableKey = lootTableEntry.getKey();
                LootTable loottable = lootTableEntry.getValue();

                JsonElement tableJson =
                    LootTable.DIRECT_CODEC.encodeStart(ops, loottable).getOrThrow(IllegalStateException::new);

                if (CONDITION_MAP.containsKey(lootTableKey) && tableJson.isJsonObject())
                {
                    ICondition condition = CONDITION_MAP.get(lootTableKey);
                    ForgeHooks.writeCondition(condition, (JsonObject) tableJson);
                }

                Path path = this.pathProvider.json(lootTableKey.location());

                return DataProvider.saveStable(
                    cachedOutput,
                    tableJson,
                    path);
            }).toArray(CompletableFuture[]::new));
        }
    }


    private static class ConditionalBlockLootTable extends BlockLootSubProvider
    {
        protected ConditionalBlockLootTable(HolderLookup.Provider lookupProvider)
        {
            super(Set.of(), FeatureFlags.REGISTRY.allFlags(), lookupProvider);
        }


        @Override
        protected void generate()
        {
            for (VariantRegistry variant : VariantRegistry.values())
            {
                if (variant.getMod() == null || !variant.getMod().isLoaded()) continue;

                this.createBlockLootTable(variant.getData().blockFull1.get(), variant.getModid());
                this.createBlockLootTable(variant.getData().blockFull2.get(), variant.getModid());
                this.createBlockLootTable(variant.getData().blockFull4.get(), variant.getModid());
                this.createBlockLootTable(variant.getData().blockHalf1.get(), variant.getModid());
                this.createBlockLootTable(variant.getData().blockHalf2.get(), variant.getModid());
                this.createBlockLootTable(variant.getData().blockHalf4.get(), variant.getModid());
                this.createBlockLootTable(variant.getData().blockTrim.get(), variant.getModid());
            }
        }


        @Override
        @NotNull
        protected Iterable<Block> getKnownBlocks()
        {
            List<Block> blockList = new LinkedList<>();

            for (VariantRegistry variant : VariantRegistry.values())
            {
                if (variant.getMod() == null || !variant.getMod().isLoaded()) continue;

                blockList.add(variant.getData().blockFull1.get());
                blockList.add(variant.getData().blockFull2.get());
                blockList.add(variant.getData().blockFull4.get());
                blockList.add(variant.getData().blockHalf1.get());
                blockList.add(variant.getData().blockHalf2.get());
                blockList.add(variant.getData().blockHalf4.get());
                blockList.add(variant.getData().blockTrim.get());
            }

            return blockList;
        }


        private void createBlockLootTable(Block block, String modId)
        {
            LootTable.Builder lootBuilder = LootTable.lootTable().withPool(LootPool.lootPool().
                setRolls(ConstantValue.exactly(1.0F)).
                setBonusRolls(ConstantValue.exactly(0.0F)).
                add(LootItem.lootTableItem(block)).
                when(ExplosionCondition.survivesExplosion()));

            block.getLootTable().ifPresent(lootTable ->
                CONDITION_MAP.put(lootTable, new ModLoadedCondition(modId)));

            this.add(block, lootBuilder);
        }
    }


    /**
     * Stores mod load condition for each resource table.
     */
    private static final Map<ResourceKey<LootTable>, ICondition> CONDITION_MAP = new HashMap<>();

    private final CompletableFuture<HolderLookup.Provider> registries;

    private final PackOutput.PathProvider pathProvider;
}