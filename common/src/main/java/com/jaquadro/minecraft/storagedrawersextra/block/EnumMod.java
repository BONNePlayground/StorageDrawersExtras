package com.jaquadro.minecraft.storagedrawersextra.block;

import com.jaquadro.minecraft.storagedrawersextra.platform.Services;
import org.jetbrains.annotations.NotNull;
import net.minecraft.util.StringRepresentable;


public enum EnumMod implements StringRepresentable
{
    BOP("biomesoplenty", VariantRegistry.BIOMESOPLENTY_FIR),
    BIOMESWEVEGONE("biomeswevegone", VariantRegistry.BIOMESWEVEGONE_ASPEN),
    IMMENG("immersiveengineering", VariantRegistry.IMMERSIVEENGINEERING_TREATED_WOOD),
    AUTUMNITY("autumnity", VariantRegistry.AUTUMNITY_MAPLE),
    ECOLOGICS("ecologics", VariantRegistry.ECOLOGICS_AZALEA_PLANKS),
    ;

    private final String id;

    private final VariantRegistry defaultMaterial;


    EnumMod(String modId, VariantRegistry defaultMaterial)
    {
        this.id = modId;
        this.defaultMaterial = defaultMaterial;
    }


    @Override
    @NotNull
    public String getSerializedName()
    {
        return id;
    }


    public VariantRegistry getDefaultMaterial()
    {
        return defaultMaterial;
    }


    public boolean isLoaded()
    {
        return Services.PLATFORM.isModLoaded(id);
    }


    public static EnumMod byId(String id)
    {
        for (EnumMod mod : values())
        {
            if (mod.getSerializedName().equals(id))
                return mod;
        }

        return null;
    }
}
