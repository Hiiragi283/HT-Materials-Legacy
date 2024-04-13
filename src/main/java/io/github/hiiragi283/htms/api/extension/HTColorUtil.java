package io.github.hiiragi283.htms.api.extension;

import java.awt.*;
import java.util.Map;
import java.util.function.Function;

import org.jetbrains.annotations.NotNull;

public final class HTColorUtil {

    private HTColorUtil() {}

    public static <T> @NotNull Color averageColor(@NotNull Map<T, Integer> map,
                                                  @NotNull Function<T, Color> colorFunction) {
        return averageColor(HTMapUtil.mapKeys(map, colorFunction));
    }

    public static @NotNull Color averageColor(@NotNull Map<Color, Integer> colorMap) {
        int redSum = 0;
        int greenSum = 0;
        int blueSum = 0;
        int weightSum = 0;
        for (Map.Entry<Color, Integer> entry : colorMap.entrySet()) {
            Color color = entry.getKey();
            int weight = entry.getValue();
            redSum += color.getRed();
            greenSum += color.getGreen();
            blueSum += color.getBlue();
            weightSum += weight;
        }
        return weightSum == 0 ? HTColor.WHITE :
                new Color(redSum / weightSum, greenSum / weightSum, blueSum / weightSum);
    }
}
