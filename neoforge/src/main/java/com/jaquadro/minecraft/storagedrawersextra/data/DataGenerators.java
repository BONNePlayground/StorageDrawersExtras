package com.jaquadro.minecraft.storagedrawersextra.data;

import com.jaquadro.minecraft.storagedrawersextra.StorageDrawersExtra;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;


@EventBusSubscriber(modid = StorageDrawersExtra.MOD_ID)
public class DataGenerators
{
    @SubscribeEvent
    public static void gatherData(GatherDataEvent.Server event)
    {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();

        generator.addProvider(true, new ModRecipeProvider.Runner(output, event.getLookupProvider()));
        generator.addProvider(true, new ModLootTableProvider(output, event.getLookupProvider()));
        generator.addProvider(true, new ModBlockTagProvider(output, event.getLookupProvider()));
        generator.addProvider(true, new ModItemTagProvider(output, event.getLookupProvider()));
    }


    @SubscribeEvent
    public static void gatherClientData(GatherDataEvent.Client event)
    {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();

        generator.addProvider(true, new ModModelProvider(output));
        generator.addProvider(true, new ModLanguageProvider(output));
    }
}
