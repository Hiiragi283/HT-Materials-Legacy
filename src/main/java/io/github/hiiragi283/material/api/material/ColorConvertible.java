package io.github.hiiragi283.material.api.material;

import java.awt.*;

@FunctionalInterface
public interface ColorConvertible {

    ColorConvertible EMPTY = () -> Color.WHITE;

    Color asColor();

}