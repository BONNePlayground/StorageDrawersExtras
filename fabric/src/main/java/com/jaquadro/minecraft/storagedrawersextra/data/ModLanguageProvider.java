package com.jaquadro.minecraft.storagedrawersextra.data;


import com.jaquadro.minecraft.storagedrawersextra.block.VariantRegistry;

import java.util.concurrent.CompletableFuture;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;


public class ModLanguageProvider extends FabricLanguageProvider
{
    public ModLanguageProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup)
    {
        super(dataOutput, registryLookup);
    }


    @Override
    public void generateTranslations(HolderLookup.Provider provider, TranslationBuilder translationBuilder)
    {
        translationBuilder.add("category.storagedrawersextra", "Storage Drawers Extra");

        for (VariantRegistry variant : VariantRegistry.values())
        {
            translationBuilder.add("block.storagedrawersextrareloaded.mat." + variant.getModid() + "_" + variant.getName(),
                ModLanguageProvider.capitalizeFirstLetters(variant.getName()));
        }
    }


    private static String capitalizeFirstLetters(String input)
    {
        if (input == null || input.isEmpty())
        {
            return "";
        }

        String[] words = input.split("_");
        StringBuilder result = new StringBuilder();

        for (String word : words)
        {
            if (!word.isEmpty())
            {
                result.append(Character.toUpperCase(word.charAt(0))).
                    append(word.substring(1).toLowerCase()).
                    append(" ");
            }
        }

        return result.toString().trim();
    }
}