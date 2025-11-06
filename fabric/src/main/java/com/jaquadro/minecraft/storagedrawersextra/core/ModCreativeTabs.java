package com.jaquadro.minecraft.storagedrawersextra.core;


import com.jaquadro.minecraft.storagedrawersextra.StorageDrawersExtra;
import com.texelsaurus.minecraft.chameleon.api.ChameleonInit;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;


public class ModCreativeTabs
{
    public static void init(ChameleonInit.InitContext context)
    {
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, MAIN, MAIN_TAB);

        ItemGroupEvents.modifyEntriesEvent(MAIN).register(itemGroup ->
            SDEItems.REGISTRY.getEntries().iterator().forEachRemaining(
                itemEntry -> itemGroup.accept(itemEntry.get())));
    }

    private static final ResourceKey<CreativeModeTab> MAIN = ResourceKey.create(Registries.CREATIVE_MODE_TAB,
        ResourceLocation.fromNamespaceAndPath(StorageDrawersExtra.MOD_ID, "storagedrawersextra"));

    private static final CreativeModeTab MAIN_TAB = FabricItemGroup.builder().
        icon(() -> new ItemStack(SDEItems.REGISTRY.getEntries().iterator().next().get())).
        title(Component.translatable("category.storagedrawersextra")).
        build();
}
