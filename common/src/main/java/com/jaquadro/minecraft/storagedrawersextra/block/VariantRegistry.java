package com.jaquadro.minecraft.storagedrawersextra.block;

import com.jaquadro.minecraft.storagedrawers.core.ModBlockVariants;
import com.jaquadro.minecraft.storagedrawersextra.StorageDrawersExtra;
import com.texelsaurus.minecraft.chameleon.registry.ChameleonRegistry;

import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;


public enum VariantRegistry
{
    BIOMESOPLENTY_FIR(EnumMod.BOP, "fir", "fir_planks", "fir_slab"),
    BIOMESOPLENTY_REDWOOD(EnumMod.BOP, "redwood", "redwood_planks", "redwood_slab"),
    BIOMESOPLENTY_MAHOGANY(EnumMod.BOP, "mahogany", "mahogany_planks", "mahogany_slab"),
    BIOMESOPLENTY_JACARANDA(EnumMod.BOP, "jacaranda", "jacaranda_planks", "jacaranda_slab"),
    BIOMESOPLENTY_PALM(EnumMod.BOP, "palm", "palm_planks", "palm_slab"),
    BIOMESOPLENTY_WILLOW(EnumMod.BOP, "willow", "willow_planks", "willow_slab"),
    BIOMESOPLENTY_DEAD(EnumMod.BOP, "dead", "dead_planks", "dead_slab"),
    BIOMESOPLENTY_MAGIC(EnumMod.BOP, "magic", "magic_planks", "magic_slab"),
    BIOMESOPLENTY_UMBRAN(EnumMod.BOP, "umbran", "umbran_planks", "umbran_slab"),
    BIOMESOPLENTY_HELLBARK(EnumMod.BOP, "hellbark", "hellbark_planks", "hellbark_slab"),
    BIOMESOPLENTY_PINE(EnumMod.BOP, "pine", "pine_planks", "pine_slab"),
    BIOMESOPLENTY_MAPLE(EnumMod.BOP, "maple", "maple_planks", "maple_slab"),
    BIOMESOPLENTY_EMPYREAL(EnumMod.BOP, "empyreal", "empyreal_planks", "empyreal_slab"),
    BIOMESOPLENTY_ORIGIN_OAK(EnumMod.BOP, "origin_oak", "origin_oak_planks", "origin_oak_slab"),

    BIOMESWEVEGONE_ASPEN(EnumMod.BIOMESWEVEGONE, "aspen", "aspen_planks", "aspen_slab"),
    BIOMESWEVEGONE_BAOBAB(EnumMod.BIOMESWEVEGONE, "baobab", "baobab_planks", "baobab_slab"),
    BIOMESWEVEGONE_BLUEENCHANTED(EnumMod.BIOMESWEVEGONE, "blue_enchanted", "blue_enchanted_planks", "blue_enchanted_slab"),
    BIOMESWEVEGONE_CIKA(EnumMod.BIOMESWEVEGONE, "cika", "cika_planks", "cika_slab"),
    BIOMESWEVEGONE_CYPRESS(EnumMod.BIOMESWEVEGONE, "cypress", "cypress_planks", "cypress_slab"),
    BIOMESWEVEGONE_EBONY(EnumMod.BIOMESWEVEGONE, "ebony", "ebony_planks", "ebony_slab"),
    BIOMESWEVEGONE_FIR(EnumMod.BIOMESWEVEGONE, "fir", "fir_planks", "fir_slab"),
    BIOMESWEVEGONE_FLORUS(EnumMod.BIOMESWEVEGONE, "florus", "florus_planks", "florus_slab"),
    BIOMESWEVEGONE_GREEN_ENCHANTED(EnumMod.BIOMESWEVEGONE, "green_enchanted", "green_enchanted_planks", "green_enchanted_slab"),
    BIOMESWEVEGONE_HOLLY(EnumMod.BIOMESWEVEGONE, "holly", "holly_planks", "holly_slab"),
    BIOMESWEVEGONE_IRONWOOD(EnumMod.BIOMESWEVEGONE, "ironwood", "ironwood_planks", "ironwood_slab"),
    BIOMESWEVEGONE_JACARANDA(EnumMod.BIOMESWEVEGONE, "jacaranda", "jacaranda_planks", "jacaranda_slab"),
    BIOMESWEVEGONE_MAHOGANY(EnumMod.BIOMESWEVEGONE, "mahogany", "mahogany_planks", "mahogany_slab"),
    BIOMESWEVEGONE_MAPLE(EnumMod.BIOMESWEVEGONE, "maple", "maple_planks", "maple_slab"),
    BIOMESWEVEGONE_PALM(EnumMod.BIOMESWEVEGONE, "palm", "palm_planks", "palm_slab"),
    BIOMESWEVEGONE_PINE(EnumMod.BIOMESWEVEGONE, "pine", "pine_planks", "pine_slab"),
    BIOMESWEVEGONE_RAINBOW_EUCALYPTUS(EnumMod.BIOMESWEVEGONE, "rainbow_eucalyptus", "rainbow_eucalyptus_planks", "rainbow_eucalyptus_slab"),
    BIOMESWEVEGONE_REDWOOD(EnumMod.BIOMESWEVEGONE, "redwood", "redwood_planks", "redwood_slab"),
    BIOMESWEVEGONE_SAKURA(EnumMod.BIOMESWEVEGONE, "sakura", "sakura_planks", "sakura_slab"),
    BIOMESWEVEGONE_SKYRIS(EnumMod.BIOMESWEVEGONE, "skyris", "skyris_planks", "skyris_slab"),
    BIOMESWEVEGONE_WHITE_MANGROVE(EnumMod.BIOMESWEVEGONE, "white_mangrove", "white_mangrove_planks", "white_mangrove_slab"),
    BIOMESWEVEGONE_WILLOW(EnumMod.BIOMESWEVEGONE, "willow", "willow_planks", "willow_slab"),
    BIOMESWEVEGONE_WITCH_HAZEL(EnumMod.BIOMESWEVEGONE, "witch_hazel", "witch_hazel_planks", "witch_hazel_slab"),
    BIOMESWEVEGONE_ZELKOVA(EnumMod.BIOMESWEVEGONE, "zelkova", "zelkova_planks", "zelkova_slab"),
    BIOMESWEVEGONE_SPIRIT(EnumMod.BIOMESWEVEGONE, "spirit", "spirit_planks", "spirit_slab"),

    IMMERSIVEENGINEERING_TREATED_WOOD(EnumMod.IMMENG, "treated_wood", "treated_wood_horizontal", "slab_treated_wood_horizontal"),

    AUTUMNITY_MAPLE(EnumMod.AUTUMNITY, "maple", "maple_planks", "maple_slab"),

    ECOLOGICS_AZALEA_PLANKS(EnumMod.ECOLOGICS, "azalea", "azalea_planks", "azalea_slab"),
    ECOLOGICS_FLOWERING_AZALEA_PLANKS(EnumMod.ECOLOGICS, "flowering_azalea", "flowering_azalea_planks", "flowering_azalea_slab"),
    ECOLOGICS_COCONUT_PLANKS(EnumMod.ECOLOGICS, "coconut", "coconut_planks", "coconut_slab"),
    ECOLOGICS_WALNUT_PLANKS(EnumMod.ECOLOGICS, "walnut", "walnut_planks", "walnut_slab"),

    UPGRADE_AQUATIC_DRIFTWOOD(EnumMod.UPGRADE_AQUATIC, "driftwood", "driftwood_planks", "driftwood_slab"),
    UPGRADE_AQUATIC_RIVER(EnumMod.UPGRADE_AQUATIC, "river", "river_planks", "river_slab"),
    ;

    private final String modid;

    private final String name;

    private final Identifier plankResource;

    private final Identifier slabResource;

    private final ModBlockVariants.VariantData data;


    VariantRegistry(EnumMod mod, String name, String plankName, String slabName)
    {
        this.modid = mod.getSerializedName();
        this.name = name;
        this.plankResource = plankName != null ? Identifier.fromNamespaceAndPath(this.modid, plankName) : null;
        this.slabResource = slabName != null ? Identifier.fromNamespaceAndPath(this.modid, slabName) : null;

        this.data = new ModBlockVariants.VariantData(Identifier.fromNamespaceAndPath(StorageDrawersExtra.MOD_ID,
            this.modid + "_" + name));
    }


    public String getModid()
    {
        return this.modid;
    }


    public String getName()
    {
        return name;
    }


    public EnumMod getMod()
    {
        return EnumMod.byId(this.modid);
    }


    public Identifier getPlankResource()
    {
        return this.plankResource;
    }


    public Identifier getSlabResource()
    {
        return this.slabResource;
    }


    public ModBlockVariants.VariantData getData()
    {
        return this.data;
    }


    public String getTrimModelName()
    {
        return "block/" + this.modid + "_" + this.name + "_trim";
    }


    public String getTrimItemName()
    {
        return "item/" + this.modid + "_" + this.name + "_trim";
    }


    public String getDrawerModelName(int size, boolean half)
    {
        String type = half ? "half" : "full";
        return "block/" + this.modid + "_" + this.name + "_" + type + "_drawers_" + size;
    }


    public String getItemModelName(int size, boolean half)
    {
        String type = half ? "half" : "full";
        return "item/" + this.modid + "_" + this.name + "_" + type + "_drawers_" + size;
    }


    public String getTextureName(String textureVariant)
    {
        return "block/" + this.modid + "/drawers_" + this.name + "_" + textureVariant;
    }


    public void registerBlocks(ChameleonRegistry<Block> register)
    {
        ModBlockVariants.registerVariant(register, this.data);
    }


    public void registerItems(ChameleonRegistry<Item> register)
    {
        ModBlockVariants.registerVariantItem(register, this.data);
    }
}
