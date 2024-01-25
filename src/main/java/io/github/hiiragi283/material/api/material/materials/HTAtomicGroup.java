package io.github.hiiragi283.material.api.material.materials;

import java.util.function.Consumer;

import com.google.common.collect.ImmutableMap;

import io.github.hiiragi283.material.api.material.HTMaterialKey;

public class HTAtomicGroup {

    public static final Consumer<ImmutableMap.Builder<HTMaterialKey, Integer>> SILICON_OXIDE = builder -> {
        builder.put(HTElementMaterials.SILICON, 1);
        builder.put(HTElementMaterials.OXYGEN, 2);
    };
}
