package com.jaquadro.minecraft.storagedrawersextra.data;

import com.jaquadro.minecraft.storagedrawers.StorageDrawers;
import com.jaquadro.minecraft.storagedrawers.block.BlockDrawers;
import com.jaquadro.minecraft.storagedrawers.block.BlockStandardDrawers;
import com.jaquadro.minecraft.storagedrawers.core.ModBlockVariants;
import com.jaquadro.minecraft.storagedrawersextra.StorageDrawersExtra;
import com.jaquadro.minecraft.storagedrawersextra.block.VariantRegistry;

import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.model.generators.*;
import net.neoforged.neoforge.common.data.ExistingFileHelper;


public class ModBlockStateProvider extends BlockStateProvider
{
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper)
    {
        super(output, StorageDrawersExtra.MOD_ID, exFileHelper);
    }


    @Override
    protected void registerStatesAndModels()
    {
        for (VariantRegistry variant : VariantRegistry.values())
        {
            if (variant.getMod() == null || !variant.getMod().isLoaded()) continue;

            this.registerVariant(variant);
        }
    }


    void registerVariant(VariantRegistry variant)
    {
        BlockModelBuilder blockTrim =
            models().cubeAll(variant.getTrimModelName(), modLoc(variant.getTextureName("side")));

        ModBlockVariants.VariantData data = variant.getData();
        this.simpleBlock(data.blockTrim.get(), blockTrim);

        this.standardDrawer(variant, data.blockFull1.get(),
            ResourceLocation.fromNamespaceAndPath(StorageDrawersExtra.MOD_ID, variant.getTextureName("side")),
            ResourceLocation.fromNamespaceAndPath(StorageDrawersExtra.MOD_ID, variant.getTextureName("front_1")),
            ResourceLocation.fromNamespaceAndPath(StorageDrawersExtra.MOD_ID, variant.getTextureName("trim")),
            1, false);
        this.standardDrawer(variant, data.blockFull2.get(),
            ResourceLocation.fromNamespaceAndPath(StorageDrawersExtra.MOD_ID, variant.getTextureName("side")),
            ResourceLocation.fromNamespaceAndPath(StorageDrawersExtra.MOD_ID, variant.getTextureName("front_2")),
            ResourceLocation.fromNamespaceAndPath(StorageDrawersExtra.MOD_ID, variant.getTextureName("trim")),
            2, false);
        this.standardDrawer(variant, data.blockFull4.get(),
            ResourceLocation.fromNamespaceAndPath(StorageDrawersExtra.MOD_ID, variant.getTextureName("side")),
            ResourceLocation.fromNamespaceAndPath(StorageDrawersExtra.MOD_ID, variant.getTextureName("front_4")),
            ResourceLocation.fromNamespaceAndPath(StorageDrawersExtra.MOD_ID, variant.getTextureName("trim")),
            4, false);

        this.standardDrawer(variant, data.blockHalf1.get(),
            ResourceLocation.fromNamespaceAndPath(StorageDrawersExtra.MOD_ID, variant.getTextureName("side")),
            ResourceLocation.fromNamespaceAndPath(StorageDrawersExtra.MOD_ID, variant.getTextureName("front_1")),
            ResourceLocation.fromNamespaceAndPath(StorageDrawersExtra.MOD_ID, variant.getTextureName("side_h")),
            ResourceLocation.fromNamespaceAndPath(StorageDrawersExtra.MOD_ID, variant.getTextureName("side")),
            ResourceLocation.fromNamespaceAndPath(StorageDrawersExtra.MOD_ID, variant.getTextureName("trim")),
            1, true);
        this.standardDrawer(variant, data.blockHalf2.get(),
            ResourceLocation.fromNamespaceAndPath(StorageDrawersExtra.MOD_ID, variant.getTextureName("side")),
            ResourceLocation.fromNamespaceAndPath(StorageDrawersExtra.MOD_ID, variant.getTextureName("front_2")),
            ResourceLocation.fromNamespaceAndPath(StorageDrawersExtra.MOD_ID, variant.getTextureName("side_h")),
            ResourceLocation.fromNamespaceAndPath(StorageDrawersExtra.MOD_ID, variant.getTextureName("side")),
            ResourceLocation.fromNamespaceAndPath(StorageDrawersExtra.MOD_ID, variant.getTextureName("trim")),
            2, true);
        this.standardDrawer(variant, data.blockHalf4.get(),
            ResourceLocation.fromNamespaceAndPath(StorageDrawersExtra.MOD_ID, variant.getTextureName("side")),
            ResourceLocation.fromNamespaceAndPath(StorageDrawersExtra.MOD_ID, variant.getTextureName("front_4")),
            ResourceLocation.fromNamespaceAndPath(StorageDrawersExtra.MOD_ID, variant.getTextureName("side_h")),
            ResourceLocation.fromNamespaceAndPath(StorageDrawersExtra.MOD_ID, variant.getTextureName("side")),
            ResourceLocation.fromNamespaceAndPath(StorageDrawersExtra.MOD_ID, variant.getTextureName("trim")),
            4, true);
    }


    private void standardDrawer(VariantRegistry variant,
        BlockStandardDrawers block,
        ResourceLocation side,
        ResourceLocation front,
        ResourceLocation trim,
        int size,
        boolean half)
    {
        this.standardDrawer(variant, block, side, front, side, side, trim, size, half);
    }


    private void standardDrawer(VariantRegistry variant,
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
            ResourceLocation.fromNamespaceAndPath(StorageDrawers.MOD_ID, "block/" + parentType + "_drawers_orientable");

        ModelFile model = this.models()
            .withExistingParent(variant.getDrawerModelName(size, half), parent)
            .texture("particle", front)
            .texture("east", side)
            .texture("west", side)
            .texture("north", front)
            .texture("up", top)
            .texture("down", top)
            .texture("south", back)
            .texture("trim", trim);

        this.drawerState(block, model);
    }


    private void drawerState(BlockStandardDrawers block, ModelFile model)
    {
        VariantBlockStateBuilder builder = this.getVariantBuilder(block);

        builder.partialState().with(BlockDrawers.FACING, Direction.NORTH).
            addModels(new ConfiguredModel(model));
        builder.partialState().with(BlockDrawers.FACING, Direction.EAST).
            addModels(new ConfiguredModel(model, 0, 90, false));
        builder.partialState().with(BlockDrawers.FACING, Direction.SOUTH).
            addModels(new ConfiguredModel(model, 0, 180, false));
        builder.partialState().with(BlockDrawers.FACING, Direction.WEST).
            addModels(new ConfiguredModel(model, 0, 270, false));
    }
}
