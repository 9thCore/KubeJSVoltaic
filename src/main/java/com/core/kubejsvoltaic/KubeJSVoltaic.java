package com.core.kubejsvoltaic;

import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(KubeJSVoltaic.MODID)
public class KubeJSVoltaic {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "kubejsvoltaic";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    public static ResourceLocation Id(String id) {
        return ResourceLocation.fromNamespaceAndPath(MODID, id);
    }

    public KubeJSVoltaic(IEventBus modEventBus, ModContainer modContainer) {

    }
}
