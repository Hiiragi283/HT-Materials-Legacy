package io.github.hiiragi283;

import io.github.hiiragi283.api.HTMaterialsAPI;
import io.github.hiiragi283.api.material.HTMaterialRegistry;
import io.github.hiiragi283.api.part.HTPartManager;
import io.github.hiiragi283.api.shape.HTShapeRegistry;

public final class HTMaterialsAPIImpl implements HTMaterialsAPI {

    static HTShapeRegistry shapeRegistry;
    static HTMaterialRegistry materialRegistry;
    static HTPartManager partManager;

    @Override
    public HTShapeRegistry shapeRegistry() {
        return shapeRegistry;
    }

    @Override
    public HTMaterialRegistry materialRegistry() {
        return materialRegistry;
    }

    @Override
    public HTPartManager partManager() {
        return partManager;
    }
}