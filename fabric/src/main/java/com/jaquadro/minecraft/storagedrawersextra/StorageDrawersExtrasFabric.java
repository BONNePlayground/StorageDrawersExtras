package com.jaquadro.minecraft.storagedrawersextra;

import com.jaquadro.minecraft.storagedrawersextra.core.ModCreativeTabs;
import com.texelsaurus.minecraft.chameleon.api.ChameleonInit;

import net.fabricmc.api.ModInitializer;


public class StorageDrawersExtrasFabric implements ModInitializer
{
    @Override
    public void onInitialize()
    {
        StorageDrawersExtra.init(new ChameleonInit.InitContext());
        ModCreativeTabs.init(new ChameleonInit.InitContext());
    }
}
