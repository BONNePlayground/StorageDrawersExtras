package com.jaquadro.minecraft.storagedrawersextra.data;


import com.jaquadro.minecraft.storagedrawersextra.StorageDrawersExtra;
import com.jaquadro.minecraft.storagedrawersextra.block.VariantRegistry;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;


public class ModLanguageProvider extends LanguageProvider
{
    public ModLanguageProvider(PackOutput output)
    {
        super(output, StorageDrawersExtra.MOD_ID, "en_us");
    }


    @Override
    protected void addTranslations()
    {
        this.add("category.storagedrawersextra", "Storage Drawers Extra");

        for (VariantRegistry variant : VariantRegistry.values())
        {
            this.add("block.storagedrawersextrareloaded.mat." + variant.name().toLowerCase(),
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