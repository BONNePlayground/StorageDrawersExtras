package com.jaquadro.minecraft.storagedrawersextra.data;


import com.jaquadro.minecraft.storagedrawersextra.block.VariantRegistry;
import com.jaquadro.minecraft.storagedrawersextra.core.SDETags;
import org.jetbrains.annotations.NotNull;
import java.util.concurrent.CompletableFuture;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;


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

            this.valueLookupBuilder(BlockTags.MINEABLE_WITH_AXE).
                setReplace(false).
                addOptional(variant.getData().blockFull1.get()).
                addOptional(variant.getData().blockFull2.get()).
                addOptional(variant.getData().blockFull4.get()).
                addOptional(variant.getData().blockHalf1.get()).
                addOptional(variant.getData().blockHalf2.get()).
                addOptional(variant.getData().blockHalf4.get()).
                addOptional(variant.getData().blockTrim.get());

            this.valueLookupBuilder(SDETags.BLOCK_DRAWERS).
                setReplace(false).
                addOptional(variant.getData().blockFull1.get()).
                addOptional(variant.getData().blockFull2.get()).
                addOptional(variant.getData().blockFull4.get()).
                addOptional(variant.getData().blockHalf1.get()).
                addOptional(variant.getData().blockHalf2.get()).
                addOptional(variant.getData().blockHalf4.get());
            this.valueLookupBuilder(SDETags.BLOCK_FULL_DRAWERS).
                setReplace(false).
                addOptional(variant.getData().blockFull1.get()).
                addOptional(variant.getData().blockFull2.get()).
                addOptional(variant.getData().blockFull4.get());
            this.valueLookupBuilder(SDETags.BLOCK_HALF_DRAWERS).
                setReplace(false).
                addOptional(variant.getData().blockHalf1.get()).
                addOptional(variant.getData().blockHalf2.get()).
                addOptional(variant.getData().blockHalf4.get());
            this.valueLookupBuilder(SDETags.BLOCK_TRIM).
                setReplace(false).
                addOptional(variant.getData().blockTrim.get());
        }
    }
}