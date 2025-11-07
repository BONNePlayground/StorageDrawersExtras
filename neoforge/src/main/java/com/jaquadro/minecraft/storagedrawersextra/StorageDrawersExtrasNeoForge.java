package com.jaquadro.minecraft.storagedrawersextra;


import com.jaquadro.minecraft.storagedrawersextra.commands.AnalyzeCommand;
import com.jaquadro.minecraft.storagedrawersextra.core.ModCreativeTabs;
import com.texelsaurus.minecraft.chameleon.registry.NeoforgeRegistryContext;

import java.util.*;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.RegisterCommandsEvent;


@Mod(StorageDrawersExtra.MOD_ID)
@EventBusSubscriber(modid = StorageDrawersExtra.MOD_ID)
public class StorageDrawersExtrasNeoForge
{
    public StorageDrawersExtrasNeoForge(IEventBus eventBus)
    {
        StorageDrawersExtra.init(new NeoforgeRegistryContext(eventBus));
        eventBus.addListener(ModCreativeTabs::init);
    }

    @SubscribeEvent
    public static void onCommandRegister(RegisterCommandsEvent event)
    {
        AnalyzeCommand.register(event.getDispatcher());
    }
}
