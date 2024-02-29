package io.github.hiiragi283.compat;

import io.github.hiiragi283.api.HTMaterialsAPI;
import io.github.hiiragi283.api.HTMaterialsAddon;
import io.github.hiiragi283.api.item.HTMaterialItem;
import io.github.hiiragi283.api.material.HTMaterialHelper;
import io.github.hiiragi283.api.material.flag.HTMaterialFlags;
import io.github.hiiragi283.api.shape.HTShapeHelper;
import io.github.hiiragi283.api.shape.HTShapeKeys;

public class HTMaterialsInit implements HTMaterialsAddon {

    @Override
    public String getModId() {
        return HTMaterialsAPI.MOD_ID;
    }

    @Override
    public int getPriority() {
        return -100;
    }

    @Override
    public void registerShape(HTShapeHelper shapeHelper) {
        shapeHelper.addShapeKey(HTShapeKeys.BLOCK);

        shapeHelper.addShapeKey(HTShapeKeys.DUST);
        shapeHelper.addShapeItem(
                HTShapeKeys.DUST,
                shapeKey -> new HTMaterialItem(shapeKey, HTMaterialFlags.GENERATE_DUST));

        shapeHelper.addShapeKey(HTShapeKeys.DUST_TINY);

        shapeHelper.addShapeKey(HTShapeKeys.GEAR);
        shapeHelper.addShapeItem(
                HTShapeKeys.GEAR,
                shapeKey -> new HTMaterialItem(shapeKey, HTMaterialFlags.GENERATE_GEAR));

        shapeHelper.addShapeKey(HTShapeKeys.GEM);
        shapeHelper.addShapeItem(
                HTShapeKeys.GEM,
                shapeKey -> new HTMaterialItem(shapeKey, HTMaterialFlags.GENERATE_GEM));

        shapeHelper.addShapeKey(HTShapeKeys.INGOT);
        shapeHelper.addShapeItem(
                HTShapeKeys.INGOT,
                shapeKey -> new HTMaterialItem(shapeKey, HTMaterialFlags.GENERATE_INGOT));

        shapeHelper.addShapeKey(HTShapeKeys.NUGGET);
        shapeHelper.addShapeItem(
                HTShapeKeys.NUGGET,
                shapeKey -> new HTMaterialItem(shapeKey, HTMaterialFlags.GENERATE_NUGGET));

        shapeHelper.addShapeKey(HTShapeKeys.ORE);

        shapeHelper.addShapeKey(HTShapeKeys.PLATE);
        shapeHelper.addShapeItem(
                HTShapeKeys.PLATE,
                shapeKey -> new HTMaterialItem(shapeKey, HTMaterialFlags.GENERATE_PLATE));

        shapeHelper.addShapeKey(HTShapeKeys.STICK);
        shapeHelper.addShapeItem(
                HTShapeKeys.STICK,
                shapeKey -> new HTMaterialItem(shapeKey, HTMaterialFlags.GENERATE_STICK));
    }

    @Override
    public void registerMaterial(HTMaterialHelper materialHelper) {}
}
