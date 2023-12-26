package io.github.hiiragi283.material.api.registry;

import com.google.common.collect.Table;
import io.github.hiiragi283.material.impl.registry.HTNonNullTableImpl;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiFunction;
import java.util.function.Consumer;

public interface HTNonNullTable<R, C, V> {

    static <R, C, V> HTNonNullTable<R, C, V> create(BiFunction<R, C, V> mapping) {
        return new HTNonNullTableImpl<>(mapping);
    }

    @NotNull
    V getOrCreate(R rowKey, C columnKey);

    void forEach(Consumer<Table.Cell<R, C, V>> consumer);

}