package com.jaquadro.minecraft.storagedrawersextra.data;


import com.jaquadro.minecraft.storagedrawersextra.block.VariantRegistry;
import com.jaquadro.minecraft.storagedrawersextra.core.SDETags;
import org.jetbrains.annotations.NotNull;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;


public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider
{
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture)
    {
        super(output, registriesFuture);
    }


    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider)
    {
        for (VariantRegistry variant : VariantRegistry.values())
        {
            if (variant.getMod() == null || !variant.getMod().isLoaded()) continue;

            this.builder(BlockTags.MINEABLE_WITH_AXE).
                setReplace(false).
                addOptional(variant.getData().blockFull1.get().builtInRegistryHolder().key()).
                addOptional(variant.getData().blockFull2.get().builtInRegistryHolder().key()).
                addOptional(variant.getData().blockFull4.get().builtInRegistryHolder().key()).
                addOptional(variant.getData().blockHalf1.get().builtInRegistryHolder().key()).
                addOptional(variant.getData().blockHalf2.get().builtInRegistryHolder().key()).
                addOptional(variant.getData().blockHalf4.get().builtInRegistryHolder().key()).
                addOptional(variant.getData().blockTrim.get().builtInRegistryHolder().key());

            this.builder(SDETags.BLOCK_DRAWERS).
                setReplace(false).
                addOptional(variant.getData().blockFull1.get().builtInRegistryHolder().key()).
                addOptional(variant.getData().blockFull2.get().builtInRegistryHolder().key()).
                addOptional(variant.getData().blockFull4.get().builtInRegistryHolder().key()).
                addOptional(variant.getData().blockHalf1.get().builtInRegistryHolder().key()).
                addOptional(variant.getData().blockHalf2.get().builtInRegistryHolder().key()).
                addOptional(variant.getData().blockHalf4.get().builtInRegistryHolder().key());
            this.builder(SDETags.BLOCK_FULL_DRAWERS).
                setReplace(false).
                addOptional(variant.getData().blockFull1.get().builtInRegistryHolder().key()).
                addOptional(variant.getData().blockFull2.get().builtInRegistryHolder().key()).
                addOptional(variant.getData().blockFull4.get().builtInRegistryHolder().key());
            this.builder(SDETags.BLOCK_HALF_DRAWERS).
                setReplace(false).
                addOptional(variant.getData().blockHalf1.get().builtInRegistryHolder().key()).
                addOptional(variant.getData().blockHalf2.get().builtInRegistryHolder().key()).
                addOptional(variant.getData().blockHalf4.get().builtInRegistryHolder().key());
            this.builder(SDETags.BLOCK_TRIM).
                setReplace(false).
                addOptional(variant.getData().blockTrim.get().builtInRegistryHolder().key());
        }
    }
}