//
// Created by BONNe
// Copyright - 2025
//


package com.jaquadro.minecraft.storagedrawersextra.mixin;


import com.jaquadro.minecraft.storagedrawers.core.ModBlocks;
import com.jaquadro.minecraft.storagedrawersextra.StorageDrawersExtra;
import com.jaquadro.minecraft.storagedrawersextra.core.SDEBlocks;
import com.texelsaurus.minecraft.chameleon.api.ChameleonInit;
import com.texelsaurus.minecraft.chameleon.registry.RegistryEntry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import java.util.stream.Stream;


/**
 * This mixin injects blocks from Storage Drawers Extra right after Storage Drawers creates their own blocks.
 * It allows to automatically map all drawers to correct block entity.
 */
@Mixin(value = ModBlocks.class, remap = false)
public class ModBlocksMixin
{
    @Inject(method = "getBlocksOfType", at = @At("RETURN"), cancellable = true)
    private static void injectCustomVariants(Class<?> blockClass, CallbackInfoReturnable<Stream<?>> cir)
    {
        Stream<?> stream = SDEBlocks.REGISTRY.getEntries().stream().
            map(RegistryEntry::get).
            filter(blockClass::isInstance)
            .map(blockClass::cast);

        // Concat streams for output.
        cir.setReturnValue(Stream.concat(cir.getReturnValue(), stream));
    }

    @Inject(method = "init", at = @At("RETURN"))
    private static void registryCustomVariants(ChameleonInit.InitContext context, CallbackInfo ci)
    {
        StorageDrawersExtra.initBlocks();
    }
}
