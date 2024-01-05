package io.github.hiiragi283.material.api.material.flag;

import crafttweaker.annotations.ZenRegister;
import io.github.hiiragi283.material.api.material.HTMaterial;
import io.github.hiiragi283.material.compat.crt.HTCrTPlugin;
import org.jetbrains.annotations.NotNull;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

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

    @ZenClass(HTCrTPlugin.MATERIAL_PREFIX + "flag.HTMaterialFlagSetBuilder")
    @ZenRegister
    public static final class Builder {

        private final Set<HTMaterialFlag> backingSet = new HashSet<>();

        @ZenMethod
        public Builder add(HTMaterialFlag flag) {
            backingSet.add(flag);
            return this;
        }

        @ZenMethod
        public Builder remove(HTMaterialFlag flag) {
            backingSet.remove(flag);
            return this;
        }

        public HTMaterialFlagSet build() {
            return new HTMaterialFlagSet(backingSet);
        }

    }

}