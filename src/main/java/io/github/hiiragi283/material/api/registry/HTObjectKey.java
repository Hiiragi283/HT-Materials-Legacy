package io.github.hiiragi283.material.api.registry;

public interface HTObjectKey<T> {

    String getName();

    Class<T> getObjClass();
}
