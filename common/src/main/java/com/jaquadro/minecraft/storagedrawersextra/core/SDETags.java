//
// Created by BONNe
// Copyright - 2025
//


package com.jaquadro.minecraft.storagedrawersextra.core;


import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;


public class SDETags
{
    public static final TagKey<Block> BLOCK_DRAWERS = TagKey.create(Registries.BLOCK,
        Identifier.fromNamespaceAndPath("storagedrawers", "drawers"));

    public static final TagKey<Block> BLOCK_FULL_DRAWERS = TagKey.create(Registries.BLOCK,
        Identifier.fromNamespaceAndPath("storagedrawers", "full_drawers"));

    public static final TagKey<Block> BLOCK_HALF_DRAWERS = TagKey.create(Registries.BLOCK,
        Identifier.fromNamespaceAndPath("storagedrawers", "half_drawers"));

    public static final TagKey<Block> BLOCK_TRIM = TagKey.create(Registries.BLOCK,
        Identifier.fromNamespaceAndPath("storagedrawers", "trim"));

    public static final TagKey<Item> ITEM_DRAWERS = TagKey.create(Registries.ITEM,
        Identifier.fromNamespaceAndPath("storagedrawers", "drawers"));

    public static final TagKey<Item> ITEM_FULL_DRAWERS = TagKey.create(Registries.ITEM,
        Identifier.fromNamespaceAndPath("storagedrawers", "full_drawers"));

    public static final TagKey<Item> ITEM_HALF_DRAWERS = TagKey.create(Registries.ITEM,
        Identifier.fromNamespaceAndPath("storagedrawers", "half_drawers"));

    public static final TagKey<Item> ITEM_TRIM = TagKey.create(Registries.ITEM,
        Identifier.fromNamespaceAndPath("storagedrawers", "trim"));

    public static final TagKey<Item> RODS_WOODEN = TagKey.create(Registries.ITEM,
        Identifier.fromNamespaceAndPath("c", "rods/wooden"));

    public static final TagKey<Item> CHESTS_WOODEN = TagKey.create(Registries.ITEM,
        Identifier.fromNamespaceAndPath("c", "chests/wooden"));
}
