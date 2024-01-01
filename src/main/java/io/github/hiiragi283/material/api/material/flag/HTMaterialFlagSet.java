package io.github.hiiragi283.material.api.material.flag;

import io.github.hiiragi283.material.api.material.HTMaterial;
import org.jetbrains.annotations.NotNull;

import java.util.*;

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

    //    Iterable    //

    @NotNull
    @Override
    public Iterator<HTMaterialFlag> iterator() {
        return backingSet.iterator();
    }

    //    Builder    //
    public static class Builder {

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