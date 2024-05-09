package io.github.hiiragi283.htms.common;

import org.jetbrains.annotations.NotNull;

import io.github.hiiragi283.htms.api.HMConstants;
import io.github.hiiragi283.htms.api.HTMaterialsPlugin;

public class HTDefaultPlugin implements HTMaterialsPlugin {

    @Override
    public @NotNull String getModId() {
        return HMConstants.MOD_ID;
    }

    @Override
    public int getPriority() {
        return -100;
    }
}
