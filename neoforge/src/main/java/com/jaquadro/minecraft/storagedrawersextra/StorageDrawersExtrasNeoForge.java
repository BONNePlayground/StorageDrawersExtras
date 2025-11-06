package com.jaquadro.minecraft.storagedrawersextra;


import com.jaquadro.minecraft.storagedrawersextra.core.ModCreativeTabs;
import com.texelsaurus.minecraft.chameleon.registry.NeoforgeRegistryContext;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;


@Mod(StorageDrawersExtra.MOD_ID)
public class StorageDrawersExtrasNeoForge
{
    public StorageDrawersExtrasNeoForge(IEventBus eventBus)
    {
        StorageDrawersExtra.init(new NeoforgeRegistryContext(eventBus));
        eventBus.addListener(ModCreativeTabs::init);
    }
}
