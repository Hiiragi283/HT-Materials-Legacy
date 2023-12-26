package io.github.hiiragi283.material.impl.registry;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import io.github.hiiragi283.material.api.registry.HTNonNullTable;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiFunction;
import java.util.function.Consumer;

public final class HTNonNullTableImpl<R, C, V> implements HTNonNullTable<R, C, V> {

    private final Table<R, C, V> backingTable = HashBasedTable.create();

    private final BiFunction<R, C, V> mapping;

    public HTNonNullTableImpl(BiFunction<R, C, V> mapping) {
        this.mapping = mapping;
    }

    @NotNull
    @Override
    public V getOrCreate(R rowKey, C columnKey) {
        V v;
        if ((v = backingTable.get(rowKey, columnKey)) == null) {
            V newValue;
            if ((newValue = mapping.apply(rowKey, columnKey)) != null) {
                backingTable.put(rowKey, columnKey, newValue);
                return newValue;
            }
        }
        return v;
    }

    @Override
    public void forEach(Consumer<Table.Cell<R, C, V>> consumer) {
        backingTable.cellSet().forEach(consumer);
    }

}