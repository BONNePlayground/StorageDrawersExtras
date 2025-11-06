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

            this.tag(BlockTags.MINEABLE_WITH_AXE).
                addOptional(variant.getData().blockFull1.getId()).
                addOptional(variant.getData().blockFull2.getId()).
                addOptional(variant.getData().blockFull4.getId()).
                addOptional(variant.getData().blockHalf1.getId()).
                addOptional(variant.getData().blockHalf2.getId()).
                addOptional(variant.getData().blockHalf4.getId()).
                addOptional(variant.getData().blockTrim.getId());

            this.tag(SDETags.BLOCK_DRAWERS).
                addOptional(variant.getData().blockFull1.getId()).
                addOptional(variant.getData().blockFull2.getId()).
                addOptional(variant.getData().blockFull4.getId()).
                addOptional(variant.getData().blockHalf1.getId()).
                addOptional(variant.getData().blockHalf2.getId()).
                addOptional(variant.getData().blockHalf4.getId());
            this.tag(SDETags.BLOCK_FULL_DRAWERS).
                addOptional(variant.getData().blockFull1.getId()).
                addOptional(variant.getData().blockFull2.getId()).
                addOptional(variant.getData().blockFull4.getId());
            this.tag(SDETags.BLOCK_HALF_DRAWERS).
                addOptional(variant.getData().blockHalf1.getId()).
                addOptional(variant.getData().blockHalf2.getId()).
                addOptional(variant.getData().blockHalf4.getId());
            this.tag(SDETags.BLOCK_TRIM).
                addOptional(variant.getData().blockTrim.getId());
        }
    }
}