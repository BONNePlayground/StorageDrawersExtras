package com.jaquadro.minecraft.storagedrawersextra.block;

import com.jaquadro.minecraft.storagedrawers.core.ModBlockVariants;
import com.jaquadro.minecraft.storagedrawersextra.StorageDrawersExtra;
import com.texelsaurus.minecraft.chameleon.registry.ChameleonRegistry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;


public enum VariantRegistry
{
    BIOMESOPLENTY_FIR(MODID.BIOMESOPLENTY, "fir", "fir_planks", "fir_slab"),
    BIOMESOPLENTY_REDWOOD(MODID.BIOMESOPLENTY, "redwood", "redwood_planks", "redwood_slab"),
    BIOMESOPLENTY_MAHOGANY(MODID.BIOMESOPLENTY, "mahogany", "mahogany_planks", "mahogany_slab"),
    BIOMESOPLENTY_JACARANDA(MODID.BIOMESOPLENTY, "jacaranda", "jacaranda_planks", "jacaranda_slab"),
    BIOMESOPLENTY_PALM(MODID.BIOMESOPLENTY, "palm", "palm_planks", "palm_slab"),
    BIOMESOPLENTY_WILLOW(MODID.BIOMESOPLENTY, "willow", "willow_planks", "willow_slab"),
    BIOMESOPLENTY_DEAD(MODID.BIOMESOPLENTY, "dead", "dead_planks", "dead_slab"),
    BIOMESOPLENTY_MAGIC(MODID.BIOMESOPLENTY, "magic", "magic_planks", "magic_slab"),
    BIOMESOPLENTY_UMBRAN(MODID.BIOMESOPLENTY, "umbran", "umbran_planks", "umbran_slab"),
    BIOMESOPLENTY_HELLBARK(MODID.BIOMESOPLENTY, "hellbark", "hellbark_planks", "hellbark_slab"),
    BIOMESOPLENTY_PINE(MODID.BIOMESOPLENTY, "pine", "pine_planks", "pine_slab"),
    BIOMESOPLENTY_MAPLE(MODID.BIOMESOPLENTY, "maple", "maple_planks", "maple_slab"),
    BIOMESOPLENTY_EMPYREAL(MODID.BIOMESOPLENTY, "empyreal", "empyreal_planks", "empyreal_slab"),

    BIOMESWEVEGONE_ASPEN(MODID.BIOMESWEVEGONE, "aspen", "aspen_planks", "aspen_slab"),
    BIOMESWEVEGONE_BAOBAB(MODID.BIOMESWEVEGONE, "baobab", "baobab_planks", "baobab_slab"),
    BIOMESWEVEGONE_BLUEENCHANTED(MODID.BIOMESWEVEGONE, "blue_enchanted", "blue_enchanted_planks", "blue_enchanted_slab"),
    BIOMESWEVEGONE_CIKA(MODID.BIOMESWEVEGONE, "cika", "cika_planks", "cika_slab"),
    BIOMESWEVEGONE_CYPRESS(MODID.BIOMESWEVEGONE, "cypress", "cypress_planks", "cypress_slab"),
    BIOMESWEVEGONE_EBONY(MODID.BIOMESWEVEGONE, "ebony", "ebony_planks", "ebony_slab"),
    BIOMESWEVEGONE_FIR(MODID.BIOMESWEVEGONE, "fir", "fir_planks", "fir_slab"),
    BIOMESWEVEGONE_FLORUS(MODID.BIOMESWEVEGONE, "florus", "florus_planks", "florus_slab"),
    BIOMESWEVEGONE_GREEN_ENCHANTED(MODID.BIOMESWEVEGONE, "green_enchanted", "green_enchanted_planks", "green_enchanted_slab"),
    BIOMESWEVEGONE_HOLLY(MODID.BIOMESWEVEGONE, "holly", "holly_planks", "holly_slab"),
    BIOMESWEVEGONE_IRONWOOD(MODID.BIOMESWEVEGONE, "ironwood", "ironwood_planks", "ironwood_slab"),
    BIOMESWEVEGONE_JACARANDA(MODID.BIOMESWEVEGONE, "jacaranda", "jacaranda_planks", "jacaranda_slab"),
    BIOMESWEVEGONE_MAHOGANY(MODID.BIOMESWEVEGONE, "mahogany", "mahogany_planks", "mahogany_slab"),
    BIOMESWEVEGONE_MAPLE(MODID.BIOMESWEVEGONE, "maple", "maple_planks", "maple_slab"),
    BIOMESWEVEGONE_PALM(MODID.BIOMESWEVEGONE, "palm", "palm_planks", "palm_slab"),
    BIOMESWEVEGONE_PINE(MODID.BIOMESWEVEGONE, "pine", "pine_planks", "pine_slab"),
    BIOMESWEVEGONE_RAINBOW_EUCALYPTUS(MODID.BIOMESWEVEGONE, "rainbow_eucalyptus", "rainbow_eucalyptus_planks", "rainbow_eucalyptus_slab"),
    BIOMESWEVEGONE_REDWOOD(MODID.BIOMESWEVEGONE, "redwood", "redwood_planks", "redwood_slab"),
    BIOMESWEVEGONE_SAKURA(MODID.BIOMESWEVEGONE, "sakura", "sakura_planks", "sakura_slab"),
    BIOMESWEVEGONE_SKYRIS(MODID.BIOMESWEVEGONE, "skyris", "skyris_planks", "skyris_slab"),
    BIOMESWEVEGONE_WHITE_MANGROVE(MODID.BIOMESWEVEGONE, "white_mangrove", "white_mangrove_planks", "white_mangrove_slab"),
    BIOMESWEVEGONE_WILLOW(MODID.BIOMESWEVEGONE, "willow", "willow_planks", "willow_slab"),
    BIOMESWEVEGONE_WITCH_HAZEL(MODID.BIOMESWEVEGONE, "witch_hazel", "witch_hazel_planks", "witch_hazel_slab"),
    BIOMESWEVEGONE_ZELKOVA(MODID.BIOMESWEVEGONE, "zelkova", "zelkova_planks", "zelkova_slab"),
    BIOMESWEVEGONE_SPIRIT(MODID.BIOMESWEVEGONE, "spirit", "spirit_planks", "spirit_slab"),

    IMMERSIVEENGINEERING_TREATED_WOOD(MODID.IMMERSIVEENGINEERING, "treated_wood", "treated_wood_horizontal", "slab_treated_wood_horizontal"),
    ;

    private final String modid;

    private final String name;

    private final ResourceLocation plankResource;

    private final ResourceLocation slabResource;

    private final ModBlockVariants.VariantData data;


    VariantRegistry(String modid, String name, String plankName, String slabName)
    {
        this.modid = modid;
        this.name = name;
        this.plankResource = plankName != null ? ResourceLocation.fromNamespaceAndPath(modid, plankName) : null;
        this.slabResource = slabName != null ? ResourceLocation.fromNamespaceAndPath(modid, slabName) : null;

        this.data = new ModBlockVariants.VariantData(ResourceLocation.fromNamespaceAndPath(StorageDrawersExtra.MOD_ID,
            modid + "_" + name));
    }


    public String getModid()
    {
        return modid;
    }


    public String getName()
    {
        return name;
    }


    public EnumMod getMod()
    {
        return EnumMod.byId(modid);
    }


    public ResourceLocation getPlankResource()
    {
        return plankResource;
    }


    public ResourceLocation getSlabResource()
    {
        return slabResource;
    }


    public ModBlockVariants.VariantData getData()
    {
        return data;
    }


    public String getTrimModelName()
    {
        return "block/" + modid + "_" + name + "_trim";
    }


    public String getTrimItemName()
    {
        return "item/" + modid + "_" + name + "_trim";
    }


    public String getDrawerModelName(int size, boolean half)
    {
        String type = half ? "half" : "full";
        return "block/" + modid + "_" + name + "_" + type + "_drawers_" + size;
    }


    public String getItemModelName(int size, boolean half)
    {
        String type = half ? "half" : "full";
        return "item/" + modid + "_" + name + "_" + type + "_drawers_" + size;
    }


    public String getTextureName(String textureVariant)
    {
        return "block/" + modid + "/drawers_" + name + "_" + textureVariant;
    }


    public void registerBlocks(ChameleonRegistry<Block> register)
    {
        ModBlockVariants.registerVariant(register, data);
    }


    public void registerItems(ChameleonRegistry<Item> register)
    {
        ModBlockVariants.registerVariantItem(register, data);
    }


    private static class MODID
    {
        public static final String BIOMESOPLENTY = "biomesoplenty";

        public static final String BIOMESWEVEGONE = "biomeswevegone";

        public static final String IMMERSIVEENGINEERING = "immersiveengineering";
    }
}
