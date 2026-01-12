package com.jaquadro.minecraft.storagedrawersextra.data;


import com.jaquadro.minecraft.storagedrawersextra.StorageDrawersExtra;
import com.jaquadro.minecraft.storagedrawersextra.block.VariantRegistry;
import com.jaquadro.minecraft.storagedrawersextra.core.SDETags;
import org.jetbrains.annotations.NotNull;
import java.util.concurrent.CompletableFuture;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.conditions.ModLoadedCondition;


public class ModRecipeProvider extends RecipeProvider
{
    public ModRecipeProvider(RecipeOutput output, HolderLookup.Provider provider)
    {
        super(provider, output);
        this.provider = provider;
    }


    @Override
    protected void buildRecipes()
    {
        for (VariantRegistry variant : VariantRegistry.values())
        {
            if (variant.getMod() == null || !variant.getMod().isLoaded())
                continue;

            makeTrim(variant, this.output);
            makeDrawer1(variant, true, this.output);
            makeDrawer2(variant, true, this.output);
            makeDrawer4(variant, true, this.output);
            makeDrawer1(variant, false, this.output);
            makeDrawer2(variant, false, this.output);
            makeDrawer4(variant, false, this.output);
        }
    }


    private void makeTrim(VariantRegistry variant, RecipeOutput recipeOutput)
    {
        if (variant.getPlankResource() == null)
        {
            return;
        }

        HolderGetter<Item> holder = provider.lookupOrThrow(Registries.ITEM);

        BuiltInRegistries.ITEM.get(variant.getPlankResource()).ifPresent(plank ->
            ShapedRecipeBuilder.shaped(holder, RecipeCategory.MISC, variant.getData().blockTrim.get(), 4)
                .pattern("x/x")
                .pattern("/x/")
                .pattern("x/x")
                .define('x', plank.value())
                .define('/', SDETags.RODS_WOODEN)
                .group(StorageDrawersExtra.MOD_ID + ":" + variant.name())
                .unlockedBy("has_item", has(plank.value()))
                .unlockedBy("has_item", has(plank.value()))
                .save(recipeOutput.withConditions(new ModLoadedCondition(variant.getModid()))));
    }


    private void makeDrawer1(VariantRegistry variant, boolean half, RecipeOutput recipeOutput)
    {
        ResourceLocation woodResource = half ? variant.getSlabResource() : variant.getPlankResource();

        if (woodResource == null)
            return;

        HolderGetter<Item> holder = provider.lookupOrThrow(Registries.ITEM);

        BuiltInRegistries.ITEM.get(woodResource).ifPresent(plank ->
        {
            Block block = half ? variant.getData().blockHalf1.get() : variant.getData().blockFull1.get();
            ShapedRecipeBuilder.shaped(holder, RecipeCategory.MISC, block, 1)
                .pattern("///")
                .pattern(" x ")
                .pattern("///")
                .define('x', SDETags.CHESTS_WOODEN)
                .define('/', plank.value())
                .group(StorageDrawersExtra.MOD_ID + ":" + variant.name())
                .unlockedBy("has_item", has(SDETags.CHESTS_WOODEN))
                .unlockedBy("has_item", has(plank.value()))
                .save(recipeOutput.withConditions(new ModLoadedCondition(variant.getModid())));
        });
    }


    private void makeDrawer2(VariantRegistry variant, boolean half, RecipeOutput recipeOutput)
    {
        ResourceLocation woodResource = half ? variant.getSlabResource() : variant.getPlankResource();
        if (woodResource == null)
            return;

        HolderGetter<Item> holder = provider.lookupOrThrow(Registries.ITEM);

        BuiltInRegistries.ITEM.get(woodResource).ifPresent(plank ->
        {
            Block block = half ? variant.getData().blockHalf2.get() : variant.getData().blockFull2.get();
            ShapedRecipeBuilder.shaped(holder, RecipeCategory.MISC, block, 2)
                .pattern("/x/")
                .pattern("///")
                .pattern("/x/")
                .define('x', SDETags.CHESTS_WOODEN)
                .define('/', plank.value())
                .group(StorageDrawersExtra.MOD_ID + ":" + variant.name())
                .unlockedBy("has_item", has(SDETags.CHESTS_WOODEN))
                .unlockedBy("has_item", has(plank.value()))
                .save(recipeOutput.withConditions(new ModLoadedCondition(variant.getModid())));
        });
    }


    private void makeDrawer4(VariantRegistry variant, boolean half, RecipeOutput recipeOutput)
    {
        ResourceLocation woodResource = half ? variant.getSlabResource() : variant.getPlankResource();
        if (woodResource == null)
            return;

        HolderGetter<Item> holder = provider.lookupOrThrow(Registries.ITEM);

        BuiltInRegistries.ITEM.get(woodResource).ifPresent(plank ->
        {
            Block block = half ? variant.getData().blockHalf4.get() : variant.getData().blockFull4.get();
            ShapedRecipeBuilder.shaped(holder, RecipeCategory.MISC, block, 4)
                .pattern("x/x")
                .pattern("///")
                .pattern("x/x")
                .define('x', SDETags.CHESTS_WOODEN)
                .define('/', plank.value())
                .group(StorageDrawersExtra.MOD_ID + ":" + variant.name())
                .unlockedBy("has_item", has(SDETags.CHESTS_WOODEN))
                .unlockedBy("has_item", has(plank.value()))
                .save(recipeOutput.withConditions(new ModLoadedCondition(variant.getModid())));
        });
    }


    // The runner to add to the data generator
    public static class Runner extends RecipeProvider.Runner
    {
        // Get the parameters from GatherDataEvent.
        public Runner(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider)
        {
            super(output, lookupProvider);
        }


        @Override
        @NotNull
        protected RecipeProvider createRecipeProvider(HolderLookup.@NotNull Provider provider,
            @NotNull RecipeOutput output)
        {
            return new ModRecipeProvider(output, provider);
        }


        @Override
        @NotNull
        public String getName()
        {
            return "Storage Drawers Extra Recipe Generator";
        }
    }


    private final HolderLookup.Provider provider;
}