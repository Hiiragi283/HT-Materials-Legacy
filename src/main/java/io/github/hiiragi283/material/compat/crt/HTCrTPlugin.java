package io.github.hiiragi283.material.compat.crt;

import crafttweaker.CraftTweakerAPI;
import io.github.hiiragi283.material.HMReference;

public class HTCrTPlugin {

    public static final String MATERIAL_PREFIX = "hiiragi283.material.";

    public static final String SHAPE_PREFIX = "hiiragi283.shape.";

    public static void loadScripts() {
        CraftTweakerAPI.tweaker.loadScript(false, HMReference.MOD_ID);
    }

}