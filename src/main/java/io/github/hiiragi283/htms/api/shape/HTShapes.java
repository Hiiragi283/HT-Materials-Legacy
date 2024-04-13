package io.github.hiiragi283.htms.api.shape;

import org.jetbrains.annotations.NotNull;

public final class HTShapes {

    private HTShapes() {}

    @NotNull
    public static final HTShape BLOCK = new HTShape("block");

    @NotNull
    public static final HTShape GEM = new HTShape("gem");

    @NotNull
    public static final HTShape INGOT = new HTShape("ingot");
}
