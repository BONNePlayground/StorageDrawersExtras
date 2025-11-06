package com.jaquadro.minecraft.storagedrawersextra.data;


import com.jaquadro.minecraft.storagedrawersextra.block.VariantRegistry;
import com.jaquadro.minecraft.storagedrawersextra.core.SDETags;
import org.jetbrains.annotations.NotNull;
import java.util.concurrent.CompletableFuture;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;


public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider
{
    public ModItemTagProvider(FabricDataOutput output,
        CompletableFuture<HolderLookup.Provider> registriesFuture,
        BlockTagProvider btp)
    {
        super(output, registriesFuture, btp);
    }

    @Override
    protected void addTags(@NotNull HolderLookup.Provider provider)
    {
        for (VariantRegistry variant : VariantRegistry.values())
        {
            if (variant.getMod() == null || !variant.getMod().isLoaded()) continue;

            copy(SDETags.BLOCK_DRAWERS, SDETags.ITEM_DRAWERS);
            copy(SDETags.BLOCK_FULL_DRAWERS, SDETags.ITEM_FULL_DRAWERS);
            copy(SDETags.BLOCK_HALF_DRAWERS, SDETags.ITEM_HALF_DRAWERS);
            copy(SDETags.BLOCK_TRIM, SDETags.ITEM_TRIM);
        }
    }
}