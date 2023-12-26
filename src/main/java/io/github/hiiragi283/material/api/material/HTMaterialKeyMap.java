package io.github.hiiragi283.material.api.material;

import it.unimi.dsi.fastutil.ints.Int2ObjectLinkedOpenHashMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2IntLinkedOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class HTMaterialKeyMap {

    private final Object2IntMap<HTMaterialKey> keyMap = new Object2IntLinkedOpenHashMap<>();

    private final Int2ObjectMap<HTMaterialKey> indexMap = new Int2ObjectLinkedOpenHashMap<>();

    public int getIndex(HTMaterialKey key) {
        return keyMap.getOrDefault(key, 0);
    }

    @NotNull
    public HTMaterialKey getKey(int index) {
        return Objects.requireNonNull(indexMap.get(index));
    }

    synchronized public void put(HTMaterialKey key, int index) {
        if (keyMap.putIfAbsent(key, index) != null) {
            throw new IllegalStateException();
        }
        if (indexMap.putIfAbsent(index, key) != null) {
            throw new IllegalStateException();
        }
    }

}