package com.jaquadro.minecraft.storagedrawersextra.data;


import com.jaquadro.minecraft.storagedrawers.ModConstants;
import com.jaquadro.minecraft.storagedrawers.block.BlockStandardDrawers;
import com.jaquadro.minecraft.storagedrawers.core.ModBlockVariants;
import com.jaquadro.minecraft.storagedrawersextra.StorageDrawersExtra;
import com.jaquadro.minecraft.storagedrawersextra.block.VariantRegistry;

import java.util.Optional;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.resources.ResourceLocation;


public class ModModelProvider extends FabricModelProvider
{
    public ModModelProvider(FabricDataOutput output)
    {
        super(output);
    }


    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerators)
    {
        for (VariantRegistry variant : VariantRegistry.values())
        {
            if (variant.getMod() == null || !variant.getMod().isLoaded()) continue;

            this.registerVariant(blockModelGenerators, variant);
        }
    }


    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerators)
    {
    }


    void registerVariant(BlockModelGenerators gen, VariantRegistry variant)
    {
        // Register trim block with simple cube_all model
        ResourceLocation trimTexture = ResourceLocation.fromNamespaceAndPath(StorageDrawersExtra.MOD_ID, variant.getTextureName("side"));
        ModBlockVariants.VariantData data = variant.getData();

        gen.createTrivialBlock(data.blockTrim.get(),
            TexturedModel.CUBE.updateTexture(mapping ->
                mapping.put(TextureSlot.ALL, trimTexture)));

        // Register full drawers
        this.standardDrawer(gen, variant, data.blockFull1.get(),
            ResourceLocation.fromNamespaceAndPath(StorageDrawersExtra.MOD_ID, variant.getTextureName("side")),
            ResourceLocation.fromNamespaceAndPath(StorageDrawersExtra.MOD_ID, variant.getTextureName("front_1")),
            ResourceLocation.fromNamespaceAndPath(StorageDrawersExtra.MOD_ID, variant.getTextureName("trim")),
            1, false);
        this.standardDrawer(gen, variant, data.blockFull2.get(),
            ResourceLocation.fromNamespaceAndPath(StorageDrawersExtra.MOD_ID, variant.getTextureName("side")),
            ResourceLocation.fromNamespaceAndPath(StorageDrawersExtra.MOD_ID, variant.getTextureName("front_2")),
            ResourceLocation.fromNamespaceAndPath(StorageDrawersExtra.MOD_ID, variant.getTextureName("trim")),
            2, false);
        this.standardDrawer(gen, variant, data.blockFull4.get(),
            ResourceLocation.fromNamespaceAndPath(StorageDrawersExtra.MOD_ID, variant.getTextureName("side")),
            ResourceLocation.fromNamespaceAndPath(StorageDrawersExtra.MOD_ID, variant.getTextureName("front_4")),
            ResourceLocation.fromNamespaceAndPath(StorageDrawersExtra.MOD_ID, variant.getTextureName("trim")),
            4, false);

        // Register half drawers
        this.standardDrawer(gen, variant, data.blockHalf1.get(),
            ResourceLocation.fromNamespaceAndPath(StorageDrawersExtra.MOD_ID, variant.getTextureName("side")),
            ResourceLocation.fromNamespaceAndPath(StorageDrawersExtra.MOD_ID, variant.getTextureName("front_1")),
            ResourceLocation.fromNamespaceAndPath(StorageDrawersExtra.MOD_ID, variant.getTextureName("side_h")),
            ResourceLocation.fromNamespaceAndPath(StorageDrawersExtra.MOD_ID, variant.getTextureName("side")),
            ResourceLocation.fromNamespaceAndPath(StorageDrawersExtra.MOD_ID, variant.getTextureName("trim")),
            1, true);
        this.standardDrawer(gen, variant, data.blockHalf2.get(),
            ResourceLocation.fromNamespaceAndPath(StorageDrawersExtra.MOD_ID, variant.getTextureName("side")),
            ResourceLocation.fromNamespaceAndPath(StorageDrawersExtra.MOD_ID, variant.getTextureName("front_2")),
            ResourceLocation.fromNamespaceAndPath(StorageDrawersExtra.MOD_ID, variant.getTextureName("side_h")),
            ResourceLocation.fromNamespaceAndPath(StorageDrawersExtra.MOD_ID, variant.getTextureName("side")),
            ResourceLocation.fromNamespaceAndPath(StorageDrawersExtra.MOD_ID, variant.getTextureName("trim")),
            2, true);
        this.standardDrawer(gen, variant, data.blockHalf4.get(),
            ResourceLocation.fromNamespaceAndPath(StorageDrawersExtra.MOD_ID, variant.getTextureName("side")),
            ResourceLocation.fromNamespaceAndPath(StorageDrawersExtra.MOD_ID, variant.getTextureName("front_4")),
            ResourceLocation.fromNamespaceAndPath(StorageDrawersExtra.MOD_ID, variant.getTextureName("side_h")),
            ResourceLocation.fromNamespaceAndPath(StorageDrawersExtra.MOD_ID, variant.getTextureName("side")),
            ResourceLocation.fromNamespaceAndPath(StorageDrawersExtra.MOD_ID, variant.getTextureName("trim")),
            4, true);
    }


    private void standardDrawer(BlockModelGenerators gen,
        VariantRegistry variant,
        BlockStandardDrawers block,
        ResourceLocation side,
        ResourceLocation front,
        ResourceLocation trim,
        int size,
        boolean half)
    {
        this.standardDrawer(gen, variant, block, side, front, side, side, trim, size, half);
    }


    private void standardDrawer(BlockModelGenerators gen,
        VariantRegistry variant,
        BlockStandardDrawers block,
        ResourceLocation side,
        ResourceLocation front,
        ResourceLocation top,
        ResourceLocation back,
        ResourceLocation trim,
        int size,
        boolean half)
    {
        String parentType = half ? "half" : "full";
        ResourceLocation parent =
            ResourceLocation.fromNamespaceAndPath(ModConstants.MOD_ID, "block/" + parentType + "_drawers_orientable");

        TextureSlot trim1 = TextureSlot.create("trim");

        ModelTemplate blockModel = new ModelTemplate(Optional.of(parent),
            Optional.empty(),
            TextureSlot.PARTICLE,
            TextureSlot.NORTH,
            TextureSlot.SOUTH,
            TextureSlot.EAST,
            TextureSlot.WEST,
            TextureSlot.UP,
            TextureSlot.DOWN,
            trim1);

        TextureMapping textureMapping = new TextureMapping().
            put(TextureSlot.PARTICLE, front).
            put(TextureSlot.EAST, side).
            put(TextureSlot.WEST, side).
            put(TextureSlot.NORTH, front).
            put(TextureSlot.UP, top).
            put(TextureSlot.DOWN, top).
            put(TextureSlot.SOUTH, back).
            put(trim1, trim);

        gen.createHorizontallyRotatedBlock(block, TexturedModel.createDefault(block1 -> textureMapping, blockModel));
    }
}

