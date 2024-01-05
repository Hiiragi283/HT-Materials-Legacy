package io.github.hiiragi283.material.api.registry;

import io.github.hiiragi283.material.impl.registry.HTObjectKeySetImpl;

import java.util.Arrays;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public interface HTObjectKeySet<T extends HTObjectKey<?>> extends Iterable<T> {

    static <T extends HTObjectKey<?>> HTObjectKeySet<T> create() {
        return new HTObjectKeySetImpl<>();
    }

    void add(T key);

    default void addAll(Iterable<T> keys) {
        keys.forEach(this::add);
    }

    default void addAll(T... keys) {
        Arrays.stream(keys).forEach(this::add);
    }

    boolean contains(T key);

    default Stream<T> stream() {
        return StreamSupport.stream(this.spliterator(), false);
    }

}