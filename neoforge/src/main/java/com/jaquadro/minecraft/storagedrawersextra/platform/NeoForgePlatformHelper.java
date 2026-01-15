package com.jaquadro.minecraft.storagedrawersextra.platform;

import com.jaquadro.minecraft.storagedrawersextra.platform.services.IPlatformHelper;
import java.util.List;

import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.neoforgespi.language.IModInfo;


public class NeoForgePlatformHelper implements IPlatformHelper {

    @Override
    public String getPlatformName() {
        return "NeoForge";
    }

    @Override
    public boolean isModLoaded(String modId) {
        return ModList.get().isLoaded(modId);
    }

    @Override
    public List<String> getLoadedMods()
    {
        return ModList.get().getMods().stream().map(IModInfo::getModId).toList();
    }

    @Override
    public boolean isDevelopmentEnvironment() {
        return !FMLLoader.getCurrent().isProduction();
    }
}
