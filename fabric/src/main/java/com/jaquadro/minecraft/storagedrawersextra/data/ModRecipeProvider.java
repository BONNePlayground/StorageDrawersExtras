package com.jaquadro.minecraft.storagedrawersextra.data;


import com.jaquadro.minecraft.storagedrawersextra.StorageDrawersExtra;
import com.jaquadro.minecraft.storagedrawersextra.block.VariantRegistry;
import com.jaquadro.minecraft.storagedrawersextra.core.SDETags;
import org.jetbrains.annotations.NotNull;
import java.util.concurrent.CompletableFuture;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditions;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;


public class ModRecipeProvider extends FabricRecipeProvider
{
    public ModRecipeProvider(FabricDataOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider)
    {
        super(packOutput, lookupProvider);
    }


    @Override
    @NotNull
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider provider, RecipeOutput recipeOutput)
    {
        return new RecipeProvider(provider, recipeOutput)
        {
            @Override
            public void buildRecipes()
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
                        .save(withConditions(recipeOutput, ResourceConditions.allModsLoaded(variant.getModid()))));
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
                        .save(withConditions(recipeOutput, ResourceConditions.allModsLoaded(variant.getModid())));
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
                        .save(withConditions(recipeOutput, ResourceConditions.allModsLoaded(variant.getModid())));
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
                        .save(withConditions(recipeOutput, ResourceConditions.allModsLoaded(variant.getModid())));
                });
            }
        };
    }



    @Override
    @NotNull
    public String getName()
    {
        return "Storage Drawers Extra Recipe Provider";
    }
}