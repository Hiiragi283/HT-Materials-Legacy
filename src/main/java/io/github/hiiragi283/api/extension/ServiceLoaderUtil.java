package io.github.hiiragi283.api.extension;

import java.util.List;
import java.util.ServiceLoader;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public final class ServiceLoaderUtil {

    private ServiceLoaderUtil() {}

    public static <T> Stream<T> getInstances(Class<T> clazz) {
        return StreamSupport.stream(ServiceLoader.load(clazz).spliterator(), false);
    }

    public static <T> T getSingleton(Class<T> clazz) {
        List<T> list = getInstances(clazz).collect(Collectors.toList());
        if (list.size() != 1) {
            throw new IllegalStateException("");
        }
        return list.get(0);
    }
}
