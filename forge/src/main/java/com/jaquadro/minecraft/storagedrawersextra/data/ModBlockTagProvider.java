package com.jaquadro.minecraft.storagedrawersextra.data;


import com.jaquadro.minecraft.storagedrawersextra.block.VariantRegistry;
import com.jaquadro.minecraft.storagedrawersextra.core.SDETags;
import org.jetbrains.annotations.NotNull;
import java.util.concurrent.CompletableFuture;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.IntrinsicHolderTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;


public class ModBlockTagProvider extends IntrinsicHolderTagsProvider<Block>
{
    public ModBlockTagProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider)
    {
        super(packOutput, Registries.BLOCK, lookupProvider, block -> block.builtInRegistryHolder().key());
    }


    @Override
    protected void addTags(@NotNull HolderLookup.Provider provider)
    {
        for (VariantRegistry variant : VariantRegistry.values())
        {
            if (variant.getMod() == null || !variant.getMod().isLoaded()) continue;

            this.tag(BlockTags.MINEABLE_WITH_AXE).
                replace(false).
                addOptional(variant.getData().blockFull1.getId()).
                addOptional(variant.getData().blockFull2.getId()).
                addOptional(variant.getData().blockFull4.getId()).
                addOptional(variant.getData().blockHalf1.getId()).
                addOptional(variant.getData().blockHalf2.getId()).
                addOptional(variant.getData().blockHalf4.getId()).
                addOptional(variant.getData().blockTrim.getId());

            this.tag(SDETags.BLOCK_DRAWERS).
                replace(false).
                addOptional(variant.getData().blockFull1.getId()).
                addOptional(variant.getData().blockFull2.getId()).
                addOptional(variant.getData().blockFull4.getId()).
                addOptional(variant.getData().blockHalf1.getId()).
                addOptional(variant.getData().blockHalf2.getId()).
                addOptional(variant.getData().blockHalf4.getId());
            this.tag(SDETags.BLOCK_FULL_DRAWERS).
                replace(false).
                addOptional(variant.getData().blockFull1.getId()).
                addOptional(variant.getData().blockFull2.getId()).
                addOptional(variant.getData().blockFull4.getId());
            this.tag(SDETags.BLOCK_HALF_DRAWERS).
                replace(false).
                addOptional(variant.getData().blockHalf1.getId()).
                addOptional(variant.getData().blockHalf2.getId()).
                addOptional(variant.getData().blockHalf4.getId());
            this.tag(SDETags.BLOCK_TRIM).
                replace(false).
                addOptional(variant.getData().blockTrim.getId());
        }
    }
}