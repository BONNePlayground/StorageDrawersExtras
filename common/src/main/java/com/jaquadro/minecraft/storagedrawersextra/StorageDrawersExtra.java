//
// Created by BONNe
// Copyright - 2025
//


package com.jaquadro.minecraft.storagedrawersextra;


import com.jaquadro.minecraft.storagedrawersextra.core.SDEBlocks;
import com.jaquadro.minecraft.storagedrawersextra.core.SDEItems;
import com.texelsaurus.minecraft.chameleon.api.ChameleonInit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class StorageDrawersExtra
{
    public static void init(ChameleonInit.InitContext initContext)
    {
        StorageDrawersExtra.INIT_CONTEXT = initContext;
    }


    public static void initBlocks()
    {
        SDEBlocks.init(StorageDrawersExtra.INIT_CONTEXT);
    }

    public static void initItems()
    {
        SDEItems.init(StorageDrawersExtra.INIT_CONTEXT);
    }


    /**
     * The context builder for Chameleon registries.
     */
    private static ChameleonInit.InitContext INIT_CONTEXT;

    public static final String MOD_ID = "storagedrawersextrareloaded";

    public static final String MOD_NAME = "Storage Drawers Extra Reloaded";

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);
}
