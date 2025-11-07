package com.jaquadro.minecraft.storagedrawersextra.platform;

import com.jaquadro.minecraft.storagedrawersextra.platform.services.IPlatformHelper;
import java.util.List;

import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLLoader;
import net.minecraftforge.forgespi.language.IModInfo;


public class ForgePlatformHelper implements IPlatformHelper {

    @Override
    public String getPlatformName() {
        return "Forge";
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
        return !FMLLoader.isProduction();
    }
}
