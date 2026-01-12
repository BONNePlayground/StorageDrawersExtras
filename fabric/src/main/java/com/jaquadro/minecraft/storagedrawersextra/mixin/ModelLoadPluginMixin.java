//
// Created by BONNe
// Copyright - 2026
//


package com.jaquadro.minecraft.storagedrawersextra.mixin;


import com.jaquadro.minecraft.storagedrawers.client.model.ModelLoadPlugin;
import com.jaquadro.minecraft.storagedrawersextra.StorageDrawersExtra;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;


@Mixin(value = ModelLoadPlugin.class, remap = false)
public class ModelLoadPluginMixin
{
    @Redirect(method = "lambda$initialize$0",
        at = @At(value = "INVOKE",
            target = "Ljava/lang/String;equals(Ljava/lang/Object;)Z"))
    private static boolean addExtraModels(String instance, Object o)
    {
        return instance.equals(o) || instance.equals(StorageDrawersExtra.MOD_ID);
    }
}
