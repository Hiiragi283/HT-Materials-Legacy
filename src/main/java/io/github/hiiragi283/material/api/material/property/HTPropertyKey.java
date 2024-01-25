package io.github.hiiragi283.material.api.material.property;

import java.util.HashMap;
import java.util.Map;

import io.github.hiiragi283.material.api.registry.HTObjectKey;

@SuppressWarnings("ClassCanBeRecord")
public final class HTPropertyKey<T extends HTMaterialProperty<T>> implements HTObjectKey<T> {

    private final String name;

    private final Class<T> objClass;

    public HTPropertyKey(String name, Class<T> objClass) {
        this.name = name;
        this.objClass = objClass;
        registry.putIfAbsent(name, this);
    }

    // HTObjectKey //

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Class<T> getObjClass() {
        return objClass;
    }

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

    // Registry //

    private static final Map<String, HTPropertyKey<?>> registry = new HashMap<>();

    @SuppressWarnings("unchecked")
    public static <T extends HTMaterialProperty<T>> HTPropertyKey<T> getKey(String name) {
        return (HTPropertyKey<T>) registry.get(name);
    }

    static {
        HTPropertyKeys.init();
    }
}
