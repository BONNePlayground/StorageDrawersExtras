package com.jaquadro.minecraft.storagedrawersextra.data;


import com.jaquadro.minecraft.storagedrawersextra.StorageDrawersExtra;
import com.jaquadro.minecraft.storagedrawersextra.block.VariantRegistry;

import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;


public class ModItemModelProvider extends ItemModelProvider
{
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper)
    {
        super(output, StorageDrawersExtra.MOD_ID, existingFileHelper);
    }


    @Override
    protected void registerModels()
    {
        for (VariantRegistry variant : VariantRegistry.values())
        {
            if (variant.getMod() == null || !variant.getMod().isLoaded()) continue;

            this.withExistingParent(variant.getTrimItemName(),
                this.modLoc(variant.getTrimModelName()));
            this.withExistingParent(variant.getItemModelName(1, false),
                this.modLoc(variant.getDrawerModelName(1, false)));
            this.withExistingParent(variant.getItemModelName(2, false),
                this.modLoc(variant.getDrawerModelName(2, false)));
            this.withExistingParent(variant.getItemModelName(4, false),
                this.modLoc(variant.getDrawerModelName(4, false)));
            this.withExistingParent(variant.getItemModelName(1, true),
                this.modLoc(variant.getDrawerModelName(1, true)));
            this.withExistingParent(variant.getItemModelName(2, true),
                this.modLoc(variant.getDrawerModelName(2, true)));
            this.withExistingParent(variant.getItemModelName(4, true),
                this.modLoc(variant.getDrawerModelName(4, true)));
        }
    }
}
