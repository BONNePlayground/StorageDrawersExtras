package com.jaquadro.minecraft.storagedrawersextra;

import com.jaquadro.minecraft.storagedrawersextra.commands.AnalyzeCommand;
import com.jaquadro.minecraft.storagedrawersextra.core.ModCreativeTabs;
import com.texelsaurus.minecraft.chameleon.api.ChameleonInit;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;


public class StorageDrawersExtrasFabric implements ModInitializer
{
    @Override
    public void onInitialize()
    {
        StorageDrawersExtra.init(new ChameleonInit.InitContext());
        ModCreativeTabs.init(new ChameleonInit.InitContext());

        CommandRegistrationCallback.EVENT.register(
            (dispatcher, registryAccess, environment) ->
                AnalyzeCommand.register(dispatcher));
    }
}
