package com.jaquadro.minecraft.storagedrawersextra.core;

import com.jaquadro.minecraft.storagedrawersextra.StorageDrawersExtra;
import com.jaquadro.minecraft.storagedrawersextra.block.VariantRegistry;
import com.texelsaurus.minecraft.chameleon.ChameleonServices;
import com.texelsaurus.minecraft.chameleon.api.ChameleonInit;
import com.texelsaurus.minecraft.chameleon.registry.ChameleonRegistry;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;


/**
 * This class register all items from VariantRegistry into ChameleonRegistry.
 */
public class SDEItems
{
    private static void register()
    {
        for (VariantRegistry variant : VariantRegistry.values())
        {
            if (variant.getMod() == null || !variant.getMod().isLoaded()) continue;

            variant.registerItems(REGISTRY);
        }
    }


    public static void init(ChameleonInit.InitContext context)
    {
        register();
        REGISTRY.init(context);
    }


    public static final ChameleonRegistry<Item> REGISTRY =
        ChameleonServices.REGISTRY.create(BuiltInRegistries.ITEM, StorageDrawersExtra.MOD_ID);
}