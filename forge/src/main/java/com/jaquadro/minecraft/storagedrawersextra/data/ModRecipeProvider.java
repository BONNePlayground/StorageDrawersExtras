package com.jaquadro.minecraft.storagedrawersextra.data;


import com.jaquadro.minecraft.storagedrawers.block.BlockStandardDrawers;
import com.jaquadro.minecraft.storagedrawersextra.StorageDrawersExtra;
import com.jaquadro.minecraft.storagedrawersextra.block.VariantRegistry;
import com.jaquadro.minecraft.storagedrawersextra.core.SDETags;
import com.texelsaurus.minecraft.chameleon.registry.RegistryEntry;
import org.jetbrains.annotations.NotNull;
import java.util.concurrent.CompletableFuture;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.crafting.ConditionalRecipe;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;


public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder
{
    public ModRecipeProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider)
    {
        super(packOutput, lookupProvider);
    }


    @Override
    protected void buildRecipes(@NotNull RecipeOutput recipeOutput)
    {
        for (VariantRegistry variant : VariantRegistry.values())
        {
            if (variant.getMod() == null || !variant.getMod().isLoaded())
                continue;

            makeTrim(variant, recipeOutput);
            makeDrawer1(variant, true, recipeOutput);
            makeDrawer2(variant, true, recipeOutput);
            makeDrawer4(variant, true, recipeOutput);
            makeDrawer1(variant, false, recipeOutput);
            makeDrawer2(variant, false, recipeOutput);
            makeDrawer4(variant, false, recipeOutput);
        }
    }


    private void makeTrim(VariantRegistry variant, RecipeOutput recipeOutput)
    {
        if (variant.getPlankResource() == null)
        {
            return;
        }

        BuiltInRegistries.ITEM.getHolder(variant.getPlankResource()).ifPresent(plank ->
        {
            ConditionalRecipe.builder().condition(modLoaded(variant.getModid())).
                recipe(ShapedRecipeBuilder.shaped(RecipeCategory.MISC, variant.getData().blockTrim.get(), 4)
                    .pattern("x/x")
                    .pattern("/x/")
                    .pattern("x/x")
                    .define('x', plank.value())
                    .define('/', SDETags.RODS_WOODEN)
                    .group(StorageDrawersExtra.MOD_ID)
                    .unlockedBy("has_item", has(plank.value()))::save).
                save(recipeOutput, variant.getData().blockTrim.getId());
        });
    }


    private void makeDrawer1(VariantRegistry variant, boolean half, RecipeOutput recipeOutput)
    {
        ResourceLocation woodResource = half ? variant.getSlabResource() : variant.getPlankResource();

        if (woodResource == null)
            return;

        BuiltInRegistries.ITEM.getHolder(woodResource).ifPresent(plank ->
        {
            RegistryEntry<BlockStandardDrawers> block =
                half ? variant.getData().blockHalf1 : variant.getData().blockFull1;

            ConditionalRecipe.builder().condition(modLoaded(variant.getModid())).
                recipe(ShapedRecipeBuilder.shaped(RecipeCategory.MISC, block.get(), 1)
                    .pattern("///")
                    .pattern(" x ")
                    .pattern("///")
                    .define('x', SDETags.CHESTS_WOODEN)
                    .define('/', plank.value())
                    .group(StorageDrawersExtra.MOD_ID)
                    .unlockedBy("has_item", has(SDETags.CHESTS_WOODEN))::save).
                save(recipeOutput, block.getId());
        });
    }


    private void makeDrawer2(VariantRegistry variant, boolean half, RecipeOutput recipeOutput)
    {
        ResourceLocation woodResource = half ? variant.getSlabResource() : variant.getPlankResource();
        if (woodResource == null)
            return;

        BuiltInRegistries.ITEM.getHolder(woodResource).ifPresent(plank ->
        {
            RegistryEntry<BlockStandardDrawers> block =
                half ? variant.getData().blockHalf2 : variant.getData().blockFull2;

            ConditionalRecipe.builder().condition(modLoaded(variant.getModid())).
                recipe(ShapedRecipeBuilder.shaped(RecipeCategory.MISC, block.get(), 2)
                    .pattern("/x/")
                    .pattern("///")
                    .pattern("/x/")
                    .define('x', SDETags.CHESTS_WOODEN)
                    .define('/', plank.value())
                    .group(StorageDrawersExtra.MOD_ID)
                    .unlockedBy("has_item", has(SDETags.CHESTS_WOODEN))::save).
                save(recipeOutput, block.getId());
        });
    }


    private void makeDrawer4(VariantRegistry variant, boolean half, RecipeOutput recipeOutput)
    {
        ResourceLocation woodResource = half ? variant.getSlabResource() : variant.getPlankResource();
        if (woodResource == null)
            return;

        BuiltInRegistries.ITEM.getHolder(woodResource).ifPresent(plank ->
        {
            RegistryEntry<BlockStandardDrawers> block =
                half ? variant.getData().blockHalf4 : variant.getData().blockFull4;

            ConditionalRecipe.builder().condition(modLoaded(variant.getModid())).
                recipe(ShapedRecipeBuilder.shaped(RecipeCategory.MISC, block.get(), 4)
                    .pattern("x/x")
                    .pattern("///")
                    .pattern("x/x")
                    .define('x', SDETags.CHESTS_WOODEN)
                    .define('/', plank.value())
                    .group(StorageDrawersExtra.MOD_ID)
                    .unlockedBy("has_item", has(SDETags.CHESTS_WOODEN))::save).
                save(recipeOutput, block.getId());
        });
    }
}