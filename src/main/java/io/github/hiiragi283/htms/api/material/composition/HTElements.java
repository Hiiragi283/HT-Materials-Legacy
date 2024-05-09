package io.github.hiiragi283.htms.api.material.composition;

import java.awt.*;

import org.jetbrains.annotations.NotNull;

import com.google.common.collect.ImmutableMap;

import io.github.hiiragi283.htms.api.extension.HTColor;
import io.github.hiiragi283.htms.api.extension.HTColorUtil;

public final class HTElements {

    private HTElements() {}
    // Elements //

    @NotNull
    public static final HTElement H = HTElement.of(HTColor.BLUE, "H", 1.0);

    @NotNull
    public static final HTElement He = HTElement.of(HTColor.YELLOW, "He", 4.0);

    @NotNull
    public static final HTElement Li = HTElement.of(HTColor.GRAY, "Li", 6.9);

    @NotNull
    public static final HTElement Be = HTElement.of(HTColor.DARK_GREEN, "Be", 9.0);

    @NotNull
    public static final HTElement C = HTElement.of(HTColorUtil.averageColor(HTColor.BLACK, HTColor.DARK_GRAY), "C",
            12.0);

    @NotNull
    public static final HTElement N = HTElement.of(HTColor.AQUA, "N", 14.0);

    @NotNull
    public static final HTElement O = HTElement.of(HTColor.WHITE, "O", 16.0);

    @NotNull
    public static final HTElement F = HTElement.of(HTColor.GREEN, "F", 19.0);

    @NotNull
    public static final HTElement Na = HTElement
            .of(HTColorUtil.averageColor(ImmutableMap.of(HTColor.DARK_BLUE, 1, HTColor.BLUE, 4)), "Na", 23.0);

    @NotNull
    public static final HTElement Mg = HTElement.of(HTColor.GRAY, "Mg", 24.0);

    @NotNull
    public static final HTElement Al = HTElement
            .of(HTColorUtil.averageColor(ImmutableMap.of(HTColor.BLUE, 1, HTColor.WHITE, 5)), "Al", 27.0);

    @NotNull
    public static final HTElement Si = HTElement.of(
            HTColorUtil.averageColor(ImmutableMap.of(HTColor.BLACK, 2, HTColor.GRAY, 1, HTColor.BLUE, 1)), "Si", 28.1);

    @NotNull
    public static final HTElement P = HTElement.of(HTColor.YELLOW, "P", 31.0);

    @NotNull
    public static final HTElement S = HTElement.of(HTColorUtil.averageColor(HTColor.GOLD, HTColor.YELLOW), "S", 32.1);

    @NotNull
    public static final HTElement Cl = HTElement.of(HTColor.YELLOW, "Cl", 35.5);

    @NotNull
    public static final HTElement K = HTElement
            .of(HTColorUtil.averageColor(ImmutableMap.of(HTColor.DARK_BLUE, 2, HTColor.BLUE, 3)), "K", 39.1);

    @NotNull
    public static final HTElement Ca = HTElement.of(HTColor.GRAY, "Ca", 40.1);

    @NotNull
    public static final HTElement Ti = HTElement
            .of(HTColorUtil.averageColor(ImmutableMap.of(HTColor.GOLD, 1, HTColor.WHITE, 2)), "Ti", 47.9);

    @NotNull
    public static final HTElement Cr = HTElement.of(HTColor.GREEN, "Cr", 52.0);

    @NotNull
    public static final HTElement Mn = HTElement.of(HTColor.GRAY, "Mn", 54.9);

    @NotNull
    public static final HTElement Fe = HTElement.of(HTColor.WHITE, "Fe", 55.8);

    @NotNull
    public static final HTElement Co = HTElement.of(HTColor.BLUE, "Co", 58.9);

    @NotNull
    public static final HTElement Ni = HTElement.of(
            HTColorUtil.averageColor(ImmutableMap.of(HTColor.GOLD, 2, HTColor.GREEN, 1, HTColor.WHITE, 1)), "Ni", 58.7);

    @NotNull
    public static final HTElement Cu = HTElement.of(HTColorUtil.averageColor(HTColor.GOLD, HTColor.RED), "Cu", 63.5);

    @NotNull
    public static final HTElement Zn = HTElement
            .of(HTColorUtil.averageColor(ImmutableMap.of(HTColor.GREEN, 1, HTColor.WHITE, 2)), "Zn", 65.4);

    @NotNull
    public static final HTElement Ag = HTElement
            .of(HTColorUtil.averageColor(ImmutableMap.of(HTColor.AQUA, 1, HTColor.WHITE, 3)), "Ag", 107.9);

    @NotNull
    public static final HTElement Sn = HTElement.of(
            HTColorUtil.averageColor(ImmutableMap.of(HTColor.BLUE, 1, HTColor.AQUA, 1, HTColor.WHITE, 3)), "Sn", 118.7);

    @NotNull
    public static final HTElement W = HTElement
            .of(HTColorUtil.averageColor(ImmutableMap.of(HTColor.BLACK, 2, HTColor.DARK_GRAY, 1)), "W", 183.8);

    @NotNull
    public static final HTElement Ir = HTElement.of(HTColor.WHITE, "Ir", 192.2);

    @NotNull
    public static final HTElement Pt = HTElement.of(new Color(0x87cefa), "Pt", 195.1);

    @NotNull
    public static final HTElement Au = HTElement.of(HTColorUtil.averageColor(HTColor.GOLD, HTColor.YELLOW), "Au",
            197.0);

    @NotNull
    public static final HTElement Hg = HTElement.of(HTColor.WHITE, "Hg", 200.6);

    @NotNull
    public static final HTElement Pb = HTElement
            .of(HTColorUtil.averageColor(HTColor.DARK_BLUE, HTColor.DARK_GRAY, HTColor.WHITE), "Pb", 207.2);

    @NotNull
    public static final HTElement U = HTElement.of(HTColor.GREEN, "U", 238.0);

    @NotNull
    public static final HTElement Pu = HTElement.of(HTColor.RED, "Pu", 244.1);

    @NotNull
    public static final HTElement Nr = HTElement.of(
            HTColorUtil.averageColor(
                    ImmutableMap.of(HTColor.BLACK, 5, HTColor.DARK_BLUE, 1, HTColor.DARK_RED, 1, HTColor.YELLOW, 1)),
            "Nr", 116.5);

    // Groups //

    @NotNull
    public static final HTElement WATER = HTElement.group(ImmutableMap.of(H, 2, O, 1));

    @NotNull
    public static final HTElement CO3 = HTElement.group(ImmutableMap.of(C, 1, O, 3));

    @NotNull
    public static final HTElement Al2O3 = HTElement.group(ImmutableMap.of(Al, 2, O, 3));

    @NotNull
    public static final HTElement SiO2 = HTElement.group(ImmutableMap.of(Si, 1, O, 2));
}
