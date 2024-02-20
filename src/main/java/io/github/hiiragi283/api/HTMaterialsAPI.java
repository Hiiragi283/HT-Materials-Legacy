package io.github.hiiragi283.api;

import net.minecraft.util.ResourceLocation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.github.hiiragi283.HMReference;
import io.github.hiiragi283.api.extension.ServiceLoaderUtil;
import io.github.hiiragi283.api.material.HTMaterialRegistry;
import io.github.hiiragi283.api.part.HTPartManager;
import io.github.hiiragi283.api.shape.HTShapeRegistry;

public interface HTMaterialsAPI {

    String MOD_ID = HMReference.MOD_ID;
    String MOD_NAME = HMReference.MOD_NAME;
    String VERSION = HMReference.VERSION;
    Logger LOGGER = LogManager.getLogger(MOD_NAME);
    HTMaterialsAPI INSTANCE = ServiceLoaderUtil.getSingleton(HTMaterialsAPI.class);

    static ResourceLocation location(String path) {
        return new ResourceLocation(MOD_ID, path);
    }

    HTShapeRegistry shapeRegistry();

    HTMaterialRegistry materialRegistry();

    HTPartManager partManager();
}
