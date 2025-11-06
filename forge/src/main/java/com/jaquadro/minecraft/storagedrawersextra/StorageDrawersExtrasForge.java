package com.jaquadro.minecraft.storagedrawersextra;

import com.jaquadro.minecraft.storagedrawersextra.core.ModCreativeTabs;
import com.texelsaurus.minecraft.chameleon.registry.ForgeRegistryContext;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;


@Mod(StorageDrawersExtra.MOD_ID)
public class StorageDrawersExtrasForge
{
    public StorageDrawersExtrasForge(FMLJavaModLoadingContext context)
    {
        StorageDrawersExtra.init(new ForgeRegistryContext(context.getModEventBus()));
        context.getModEventBus().addListener(ModCreativeTabs::init);
    }
}
