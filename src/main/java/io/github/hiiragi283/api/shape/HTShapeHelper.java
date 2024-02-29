package io.github.hiiragi283.api.shape;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;

import org.jetbrains.annotations.NotNull;

import com.google.common.base.Preconditions;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import io.github.hiiragi283.api.item.IMaterialItemProvider;

public final class HTShapeHelper {

    // Shape key
    private final Set<HTShapeKey> keys = new LinkedHashSet<>();

    @NotNull
    public Set<HTShapeKey> getKeys() {
        return Collections.unmodifiableSet(keys);
    }

    public void addShapeKey(@NotNull HTShapeKey shapeKey) {
        Preconditions.checkArgument(keys.add(shapeKey), "ShapeKey; %s is already registered!", shapeKey.name());
    }

    // Shape tem
    private final Multimap<HTShapeKey, HTShapeItemBuilder> contentMultimap = HashMultimap.create();

    @NotNull
    public Collection<HTShapeItemBuilder> getShapeItems(@NotNull HTShapeKey shapeKey) {
        return contentMultimap.get(shapeKey);
    }

    public void addShapeItem(@NotNull HTShapeKey shapeKey,
                             @NotNull Function<HTShapeKey, ? extends IMaterialItemProvider> itemBuilder) {
        contentMultimap.put(shapeKey, new HTShapeItemBuilder.Builder(shapeKey, itemBuilder).build());
    }

    public void addShapeItem(@NotNull HTShapeKey shapeKey,
                             @NotNull Function<HTShapeKey, ? extends IMaterialItemProvider> itemBuilder,
                             @NotNull Consumer<HTShapeItemBuilder.Builder> builderConsumer) {
        HTShapeItemBuilder.Builder builder = new HTShapeItemBuilder.Builder(shapeKey, itemBuilder);
        builderConsumer.accept(builder);
        contentMultimap.put(shapeKey, builder.build());
    }
}
