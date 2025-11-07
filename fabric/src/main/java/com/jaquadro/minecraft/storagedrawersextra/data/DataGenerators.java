package com.jaquadro.minecraft.storagedrawersextra.data;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;


public class DataGenerators implements DataGeneratorEntrypoint
{
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator)
    {
        FabricDataGenerator.Pack pack = generator.createPack();

        pack.addProvider(ModRecipeProvider::new);
        pack.addProvider(ModLootTableProvider::new);

        ModBlockTagProvider modBlockTagProvider = pack.addProvider(ModBlockTagProvider::new);
        pack.addProvider((dataOutput, registryLookup) ->
            new ModItemTagProvider(dataOutput, registryLookup, modBlockTagProvider));

        pack.addProvider(ModModelProvider::new);
        pack.addProvider(ModLanguageProvider::new);
    }
}