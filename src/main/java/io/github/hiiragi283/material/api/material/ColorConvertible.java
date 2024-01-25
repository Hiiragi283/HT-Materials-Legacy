package io.github.hiiragi283.material.api.material;

import java.awt.*;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.jetbrains.annotations.NotNull;

import com.google.common.collect.ImmutableMap;

import io.github.hiiragi283.material.util.HTCollectors;

@FunctionalInterface
public interface ColorConvertible {

    @NotNull
    Color asColor();

    ColorConvertible EMPTY = () -> Color.WHITE;

    static ColorConvertible of(ColorConvertible... colors) {
        return () -> average(Arrays.stream(colors).map(ColorConvertible::asColor));
    }

    static ColorConvertible of(Map<ColorConvertible, Integer> map) {
        return () -> average(map.entrySet().stream().collect(HTCollectors.mapKeys(ColorConvertible::asColor)));
    }

    static ColorConvertible ofColor(Color... colors) {
        return () -> average(Arrays.asList(colors));
    }

    static ColorConvertible ofColor(Consumer<ImmutableMap.Builder<Color, Integer>> consumer) {
        var builder = new ImmutableMap.Builder<Color, Integer>();
        consumer.accept(builder);
        return () -> average(builder.build());
    }

    static Color average(Iterable<Color> colors) {
        return average(StreamSupport.stream(colors.spliterator(), false));
    }

    static Color average(Stream<Color> colors) {
        return average(colors.collect(HTCollectors.associateWith(1)));
    }

    static Color average(Map<Color, Integer> map) {
        int redSum = 0;
        int greenSum = 0;
        int blueSum = 0;
        int weightSum = 0;
        for (Map.Entry<Color, Integer> entry : map.entrySet()) {
            Color color = entry.getKey();
            int weight = entry.getValue();
            redSum += color.getRed() * weight;
            greenSum += color.getGreen() * weight;
            blueSum += color.getBlue() * weight;
            weightSum += weight;
        }
        return weightSum == 0 ? Color.WHITE : new Color(redSum / weightSum, greenSum / weightSum, blueSum / weightSum);
    }
}
