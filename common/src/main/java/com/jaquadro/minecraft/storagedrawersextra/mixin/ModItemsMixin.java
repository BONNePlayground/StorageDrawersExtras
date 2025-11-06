//
// Created by BONNe
// Copyright - 2025
//


package com.jaquadro.minecraft.storagedrawersextra.mixin;


import com.jaquadro.minecraft.storagedrawers.core.ModItems;
import com.jaquadro.minecraft.storagedrawersextra.StorageDrawersExtra;
import com.texelsaurus.minecraft.chameleon.api.ChameleonInit;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


/**
 * This mixin injects items from Storage Drawers Extra right after Storage Drawers creates their own items.
 */
@Mixin(value = ModItems.class, remap = false)
public class ModItemsMixin
{
    @Inject(method = "init", at = @At("RETURN"))
    private static void registryCustomVariants(ChameleonInit.InitContext context, CallbackInfo ci)
    {
        StorageDrawersExtra.initItems();
    }
}
