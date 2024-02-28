package io.github.hiiragi283.api.extension;

import java.util.List;
import java.util.ServiceLoader;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.jetbrains.annotations.NotNull;

public final class ServiceLoaderUtil {

    private ServiceLoaderUtil() {}

    @NotNull
    public static <T> Stream<T> getInstances(@NotNull Class<T> clazz) {
        return StreamSupport.stream(ServiceLoader.load(clazz).spliterator(), false);
    }

    @SuppressWarnings("SimplifyStreamApiCallChains")
    @NotNull
    public static <T> T getSingleton(@NotNull Class<T> clazz) {
        List<T> list = getInstances(clazz).collect(Collectors.toList());
        if (list.size() != 1) {
            throw new IllegalStateException("");
        }
        return list.get(0);
    }
}
