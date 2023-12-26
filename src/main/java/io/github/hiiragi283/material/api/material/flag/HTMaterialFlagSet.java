package io.github.hiiragi283.material.api.material.flag;

import io.github.hiiragi283.material.api.material.HTMaterial;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public final class HTMaterialFlagSet implements Collection<HTMaterialFlag> {

    private final Collection<HTMaterialFlag> backingSet;

    private HTMaterialFlagSet(Collection<HTMaterialFlag> backingSet) {
        this.backingSet = backingSet;
    }

    public void verify(HTMaterial material) {
        backingSet.forEach(flag -> flag.verify(material));
    }

    //    Collection    //

    @Override
    public int size() {
        return backingSet.size();
    }

    @Override
    public boolean isEmpty() {
        return backingSet.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return backingSet.contains(o);
    }

    @NotNull
    @Override
    public Iterator<HTMaterialFlag> iterator() {
        return backingSet.iterator();
    }

    @Override
    public Object @NotNull [] toArray() {
        return backingSet.toArray();
    }

    @Override
    public <T> T @NotNull [] toArray(@NotNull T[] a) {
        return backingSet.toArray(a);
    }

    @Override
    public boolean add(HTMaterialFlag flag) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(@NotNull Collection<?> c) {
        return backingSet.containsAll(c);
    }

    @Override
    public boolean addAll(@NotNull Collection<? extends HTMaterialFlag> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(@NotNull Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(@NotNull Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    //    Builder    //
    public static class Builder {

        public final Set<HTMaterialFlag> backingSet = new HashSet<>();

        HTMaterialFlagSet build() {
            return new HTMaterialFlagSet(backingSet);
        }

    }

}