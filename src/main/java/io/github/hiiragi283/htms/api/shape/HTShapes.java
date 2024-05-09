package io.github.hiiragi283.htms.api.shape;

import org.jetbrains.annotations.NotNull;

public final class HTShapes {

    private HTShapes() {}

    @NotNull
    public static final HTShape BLOCK = new HTShape("block");

    @NotNull
    public static final HTShape ORE = new HTShape("ore");

    @NotNull
    public static final HTShape DUST = new HTShape("dust");

    @NotNull
    public static final HTShape GEAR = new HTShape("gear");

    @NotNull
    public static final HTShape GEM = new HTShape("gem");

    @NotNull
    public static final HTShape INGOT = new HTShape("ingot");

    @NotNull
    public static final HTShape NUGGET = new HTShape("nugget");

    @NotNull
    public static final HTShape PLATE = new HTShape("plate");

    @NotNull
    public static final HTShape ROD = new HTShape("rod");
}
