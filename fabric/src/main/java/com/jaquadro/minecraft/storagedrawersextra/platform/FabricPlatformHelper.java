package com.jaquadro.minecraft.storagedrawersextra.platform;

import com.jaquadro.minecraft.storagedrawersextra.platform.services.IPlatformHelper;
import java.util.List;

import net.fabricmc.loader.api.FabricLoader;

public class FabricPlatformHelper implements IPlatformHelper {

    @Override
    public String getPlatformName() {
        return "Fabric";
    }

    @Override
    public boolean isModLoaded(String modId) {
        return FabricLoader.getInstance().isModLoaded(modId);
    }


    @Override
    public List<String> getLoadedMods()
    {
        return FabricLoader.getInstance().getAllMods().stream().
            map(mod -> mod.getMetadata().getId()).toList();
    }


    @Override
    public boolean isDevelopmentEnvironment() {
        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }
}
