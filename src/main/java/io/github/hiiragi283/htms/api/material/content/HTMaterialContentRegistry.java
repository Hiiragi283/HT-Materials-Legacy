package io.github.hiiragi283.htms.api.material.content;

import java.util.*;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.google.common.collect.ImmutableMap;

import io.github.hiiragi283.htms.api.item.IMaterialItemProvider;
import io.github.hiiragi283.htms.api.shape.HTShape;

public final class HTMaterialContentRegistry {

    @NotNull
    private final Map<@NotNull HTShape, @NotNull IMaterialItemProvider> itemMap = new LinkedHashMap<>();

    public HTMaterialContentRegistry(HTMaterialContentRegistry.Builder builder) {
        builder.itemBuilderMap.forEach((shape, itemBuilder) -> itemMap.put(shape, itemBuilder.build()));
    }

    public boolean hasItem(@NotNull HTShape shape) {
        return itemMap.containsKey(shape);
    }

    public @NotNull Set<@NotNull HTShape> keySet() {
        return itemMap.keySet();
    }

    public @NotNull Collection<@NotNull IMaterialItemProvider> values() {
        return itemMap.values();
    }

    public @Nullable IMaterialItemProvider get(@NotNull HTShape key) {
        return itemMap.get(key);
    }

    public @NotNull ImmutableMap<@NotNull HTShape, @NotNull IMaterialItemProvider> keyMap() {
        return ImmutableMap.copyOf(itemMap);
    }

    public void register(RegistryEvent.Register<Item> event) {
        itemMap.values().forEach(item -> event.getRegistry().register(item.asItem()));
    }

    public static final class Builder {

        @NotNull
        Map<@NotNull HTShape, @NotNull HTMaterialItemBuilder> itemBuilderMap = new HashMap<>();

        public @NotNull HTMaterialItemBuilder getItemBuilder(@NotNull HTShape shape) {
            return itemBuilderMap.computeIfAbsent(shape, HTMaterialItemBuilder::new);
        }
    }
}
