//
// Created by BONNe
// Copyright - 2025
//


package com.jaquadro.minecraft.storagedrawersextra.data;


import com.jaquadro.minecraft.storagedrawersextra.block.VariantRegistry;
import java.util.concurrent.CompletableFuture;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditions;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.ExplosionCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;


public class ModLootTableProvider extends FabricBlockLootTableProvider
{
    public ModLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup)
    {
        super(dataOutput, registryLookup);
    }


    @Override
    public void generate()
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


    private void createBlockLootTable(Block block, String modId)
    {
        LootTable.Builder builder = LootTable.lootTable().withPool(LootPool.lootPool().
            setRolls(ConstantValue.exactly(1.0F)).
            setBonusRolls(ConstantValue.exactly(0.0F)).
            add(LootItem.lootTableItem(block)).
            when(ExplosionCondition.survivesExplosion()));
        this.withConditions(ResourceConditions.allModsLoaded(modId)).add(block, builder);
    }
}