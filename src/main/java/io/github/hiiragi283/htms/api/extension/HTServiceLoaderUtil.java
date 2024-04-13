package io.github.hiiragi283.htms.api.extension;

import java.util.List;
import java.util.ServiceLoader;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public final class HTServiceLoaderUtil {

    private HTServiceLoaderUtil() {}

    public static <T> Stream<T> getInstances(Class<T> clazz) {
        return StreamSupport.stream(ServiceLoader.load(clazz).spliterator(), false);
    }

    public static <T> T getSingleton(Class<T> clazz) {
        @SuppressWarnings("SimplifyStreamApiCallChains")
        List<T> instances = getInstances(clazz).collect(Collectors.toList());
        if (instances.isEmpty()) {
            throw new IllegalStateException("");
        } else if (instances.size() > 1) {
            throw new IllegalStateException("");
        } else {
            return instances.get(0);
        }
    }
}
