package com.jaquadro.minecraft.storagedrawersextra.core;


import com.jaquadro.minecraft.storagedrawersextra.StorageDrawersExtra;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.registries.RegisterEvent;


public class ModCreativeTabs
{
    public static void init(RegisterEvent event)
    {
        event.register(Registries.CREATIVE_MODE_TAB,
            helper -> helper.register(MAIN,
                CreativeModeTab.builder().
                    icon(() -> SDEItems.REGISTRY.getEntries().iterator().next().get().getDefaultInstance()).
                    title(Component.translatable("category.storagedrawersextra")).
                    displayItems((params, output) ->
                        SDEItems.REGISTRY.getEntries().forEach((reg) -> output.accept(reg.get().getDefaultInstance()))).
                    build()));
    }


    private static final ResourceKey<CreativeModeTab> MAIN = ResourceKey.create(Registries.CREATIVE_MODE_TAB,
        ResourceLocation.fromNamespaceAndPath(StorageDrawersExtra.MOD_ID, "storagedrawersextra"));
}