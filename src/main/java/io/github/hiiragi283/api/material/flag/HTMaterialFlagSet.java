package io.github.hiiragi283.api.material.flag;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.jetbrains.annotations.NotNull;

import io.github.hiiragi283.api.material.HTMaterial;

public final class HTMaterialFlagSet implements Iterable<HTMaterialFlag> {

    private final Collection<HTMaterialFlag> backingSet;

    private HTMaterialFlagSet(Collection<HTMaterialFlag> backingSet) {
        this.backingSet = backingSet;
    }

    public void verify(HTMaterial material) {
        backingSet.forEach(flag -> flag.verify(material));
    }

    public boolean contains(@NotNull HTMaterialFlag flag) {
        return backingSet.contains(flag);
    }

    public boolean containsAll(@NotNull Collection<HTMaterialFlag> c) {
        return backingSet.containsAll(c);
    }

    // Iterable

    @NotNull
    @Override
    public Iterator<HTMaterialFlag> iterator() {
        return backingSet.iterator();
    }

    // Builder

    public static final class Builder {

        private final Set<HTMaterialFlag> backingSet = new HashSet<>();

        public Builder add(HTMaterialFlag flag) {
            backingSet.add(flag);
            return this;
        }

        public Builder remove(HTMaterialFlag flag) {
            backingSet.remove(flag);
            return this;
        }

        public HTMaterialFlagSet build() {
            return new HTMaterialFlagSet(backingSet);
        }
    }
}
