package io.github.hiiragi283.api.shape;

import java.util.Objects;
import java.util.function.Function;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistry;

import org.jetbrains.annotations.NotNull;

import io.github.hiiragi283.api.item.IMaterialItemProvider;

public final class HTShapeItemBuilder {

    public final HTShapeKey shapeKey;
    private final Function<HTShapeKey, ? extends IMaterialItemProvider> itemBuilder;
    private final Function<HTShapeKey, ResourceLocation> locationBuilder;

    HTShapeItemBuilder(HTShapeKey shapeKey, Function<HTShapeKey, ? extends IMaterialItemProvider> itemBuilder,
                       Function<HTShapeKey, ResourceLocation> locationBuilder) {
        this.shapeKey = shapeKey;
        this.itemBuilder = itemBuilder;
        this.locationBuilder = locationBuilder;
    }

    private IMaterialItemProvider itemProvider;
    private Item item;

    @NotNull
    public IMaterialItemProvider getItemProvider() {
        return Objects.requireNonNull(itemProvider, "Not initialized!");
    }

    @NotNull
    public Item getItem() {
        return Objects.requireNonNull(item, "Not initialized!");
    }

    public void initItem(@NotNull IForgeRegistry<Item> registry) {
        itemProvider = itemBuilder.apply(shapeKey);
        item = itemProvider.asItem().setRegistryName(locationBuilder.apply(shapeKey));
        registry.register(item);
    }
}
