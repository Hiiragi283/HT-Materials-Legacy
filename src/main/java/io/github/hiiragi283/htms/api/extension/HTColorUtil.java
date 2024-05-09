package io.github.hiiragi283.htms.api.extension;

import java.awt.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import net.minecraft.client.renderer.GlStateManager;

import org.jetbrains.annotations.NotNull;

public final class HTColorUtil {

    private HTColorUtil() {}

    public static @NotNull Color averageColor(Color... colors) {
        return averageColor(Arrays.asList(colors));
    }

    public static <T> @NotNull Color averageColor(@NotNull Collection<@NotNull T> colors,
                                                  @NotNull Function<@NotNull T, @NotNull Color> colorFunction) {
        return averageColor(colors.stream().collect(Collectors.toMap(colorFunction, color -> 1)));
    }

    public static @NotNull Color averageColor(@NotNull Collection<@NotNull Color> colors) {
        return averageColor(colors.stream().collect(Collectors.toMap(Function.identity(), color -> 1)));
    }

    public static <T> @NotNull Color averageColor(@NotNull Map<@NotNull T, @NotNull Integer> map,
                                                  @NotNull Function<@NotNull T, @NotNull Color> colorFunction) {
        return averageColor(HTMapUtil.mapKeys(map, colorFunction));
    }

    public static @NotNull Color averageColor(@NotNull Map<@NotNull Color, @NotNull Integer> colorMap) {
        int redSum = 0;
        int greenSum = 0;
        int blueSum = 0;
        int weightSum = 0;
        for (Map.Entry<Color, Integer> entry : colorMap.entrySet()) {
            Color color = entry.getKey();
            int weight = entry.getValue();
            redSum += color.getRed() * weight;
            greenSum += color.getGreen() * weight;
            blueSum += color.getBlue() * weight;
            weightSum += weight;
        }
        return weightSum == 0 ? HTColor.WHITE :
                new Color(redSum / weightSum, greenSum / weightSum, blueSum / weightSum);
    }

    public static void setGLColor(Color color) {
        setGLColor(color.getRGB());
    }

    public static void setGLColor(int color) {
        float red = (color >> 16 & 255) / 255.0F;
        float green = (color >> 8 & 255) / 255.0F;
        float blue = (color & 255) / 255.0F;
        GlStateManager.color(red, green, blue);
    }
}
