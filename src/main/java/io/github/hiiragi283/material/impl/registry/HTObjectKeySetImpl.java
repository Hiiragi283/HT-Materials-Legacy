package io.github.hiiragi283.material.impl.registry;

import io.github.hiiragi283.material.api.registry.HTObjectKey;
import io.github.hiiragi283.material.api.registry.HTObjectKeySet;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public final class HTObjectKeySetImpl<T extends HTObjectKey<?>> implements HTObjectKeySet<T> {

    private final Set<T> backingSet = new HashSet<>();

    @Override
    public void add(T key) {
        backingSet.add(key);
    }

    @Override
    public boolean contains(T key) {
        return backingSet.contains(key);
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return backingSet.iterator();
    }

}