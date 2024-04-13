package io.github.hiiragi283.htms.api.material.content;

import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.jetbrains.annotations.NotNull;

import io.github.hiiragi283.htms.api.HTMaterialsAPI;
import io.github.hiiragi283.htms.api.item.IMaterialItemProvider;
import io.github.hiiragi283.htms.api.item.ItemShapedMaterial;
import io.github.hiiragi283.htms.api.material.HTMaterial;
import io.github.hiiragi283.htms.api.material.HTMaterialKey;
import io.github.hiiragi283.htms.api.shape.HTShape;

public final class HTMaterialItemBuilder {

    @NotNull
    private final HTShape shape;

    @NotNull
    Predicate<@NotNull HTMaterial> filter = material -> false;

    @NotNull
    BiFunction<HTShape, Stream<HTMaterialKey>, IMaterialItemProvider> constructor = ItemShapedMaterial::new;

    public HTMaterialItemBuilder(@NotNull HTShape shape) {
        this.shape = shape;
    }

    public HTMaterialItemBuilder setConstructor(@NotNull BiFunction<HTShape, Stream<HTMaterialKey>, IMaterialItemProvider> constructor) {
        this.constructor = constructor;
        return this;
    }

    public HTMaterialItemBuilder setMaterialFilter(@NotNull Predicate<@NotNull HTMaterial> filter) {
        this.filter = filter;
        return this;
    }

    public HTMaterialItemBuilder resetMaterialFilter() {
        this.filter = material -> false;
        return this;
    }

    IMaterialItemProvider build() {
        return constructor.apply(shape, HTMaterialsAPI.INSTANCE.getMaterialRegistry().values()
                .stream()
                .filter(filter)
                .map(HTMaterial::key));
    }
}
