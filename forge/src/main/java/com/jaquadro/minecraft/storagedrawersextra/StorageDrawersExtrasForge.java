package com.jaquadro.minecraft.storagedrawersextra;

import com.jaquadro.minecraft.storagedrawersextra.commands.AnalyzeCommand;
import com.jaquadro.minecraft.storagedrawersextra.core.ModCreativeTabs;
import com.texelsaurus.minecraft.chameleon.registry.ForgeRegistryContext;

import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;


@Mod(StorageDrawersExtra.MOD_ID)
@Mod.EventBusSubscriber(modid = StorageDrawersExtra.MOD_ID)
public class StorageDrawersExtrasForge
{
    public StorageDrawersExtrasForge(FMLJavaModLoadingContext context)
    {
        StorageDrawersExtra.init(new ForgeRegistryContext(context.getModEventBus()));
        context.getModEventBus().addListener(ModCreativeTabs::init);
    }


    @SubscribeEvent
    public static void onCommandRegister(RegisterCommandsEvent event)
    {
        AnalyzeCommand.register(event.getDispatcher());
    }
}
