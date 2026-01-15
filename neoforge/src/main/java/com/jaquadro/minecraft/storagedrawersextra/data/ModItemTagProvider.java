package com.jaquadro.minecraft.storagedrawersextra.data;


import com.jaquadro.minecraft.storagedrawersextra.block.VariantRegistry;
import com.jaquadro.minecraft.storagedrawersextra.core.SDETags;
import org.jetbrains.annotations.NotNull;
import java.util.concurrent.CompletableFuture;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.IntrinsicHolderTagsProvider;
import net.minecraft.world.item.Item;


public class ModItemTagProvider extends IntrinsicHolderTagsProvider<Item>
{
    public ModItemTagProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider)
    {
        super(packOutput, Registries.ITEM, lookupProvider, item -> item.builtInRegistryHolder().key());
    }


    @Override
    protected void addTags(@NotNull HolderLookup.Provider provider)
    {
        for (VariantRegistry variant : VariantRegistry.values())
        {
            if (variant.getMod() == null || !variant.getMod().isLoaded()) continue;

            this.tag(SDETags.ITEM_DRAWERS).
                replace(false).
                addOptional(variant.getData().blockFull1.get().asItem()).
                addOptional(variant.getData().blockFull2.get().asItem()).
                addOptional(variant.getData().blockFull4.get().asItem()).
                addOptional(variant.getData().blockHalf1.get().asItem()).
                addOptional(variant.getData().blockHalf2.get().asItem()).
                addOptional(variant.getData().blockHalf4.get().asItem());
            this.tag(SDETags.ITEM_FULL_DRAWERS).
                replace(false).
                addOptional(variant.getData().blockFull1.get().asItem()).
                addOptional(variant.getData().blockFull2.get().asItem()).
                addOptional(variant.getData().blockFull4.get().asItem());
            this.tag(SDETags.ITEM_HALF_DRAWERS).
                replace(false).
                addOptional(variant.getData().blockHalf1.get().asItem()).
                addOptional(variant.getData().blockHalf2.get().asItem()).
                addOptional(variant.getData().blockHalf4.get().asItem());
            this.tag(SDETags.ITEM_TRIM).
                replace(false).
                addOptional(variant.getData().blockTrim.get().asItem());
        }
    }
}