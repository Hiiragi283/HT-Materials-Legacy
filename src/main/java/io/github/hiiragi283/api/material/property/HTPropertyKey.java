package io.github.hiiragi283.api.material.property;

import com.github.bsideup.jabel.Desugar;

@Desugar
public record HTPropertyKey<T extends HTMaterialProperty<T>> (String name, Class<T> objClass) {

    // Object //

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj instanceof HTPropertyKey<?>otherKey)
            return otherKey.name.equals(this.name) && otherKey.objClass.equals(this.objClass);
        return false;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return name;
    }
}
