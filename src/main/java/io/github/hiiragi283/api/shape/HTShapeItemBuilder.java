package io.github.hiiragi283.api.shape;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.registries.IForgeRegistry;

import org.jetbrains.annotations.NotNull;

import io.github.hiiragi283.api.HTMaterialsAPI;
import io.github.hiiragi283.api.item.IMaterialItemProvider;
import io.github.hiiragi283.api.material.HTMaterial;

public final class HTShapeItemBuilder {

    public final HTShapeKey shapeKey;
    private final Function<HTShapeKey, ? extends IMaterialItemProvider> itemFunction;
    private final Function<HTShapeKey, ResourceLocation> locationFunction;
    private final BiFunction<HTShapeKey, HTMaterial, ResourceLocation> modelFunction;

    private HTShapeItemBuilder(HTShapeKey shapeKey, Function<HTShapeKey, ? extends IMaterialItemProvider> itemFunction,
                               Function<HTShapeKey, ResourceLocation> locationFunction,
                               BiFunction<HTShapeKey, HTMaterial, ResourceLocation> modelFunction) {
        this.shapeKey = shapeKey;
        this.itemFunction = itemFunction;
        this.locationFunction = locationFunction;
        this.modelFunction = modelFunction;
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

    public void registerItem(@NotNull IForgeRegistry<Item> registry) {
        itemProvider = itemFunction.apply(shapeKey);
        item = itemProvider.asItem().setRegistryName(locationFunction.apply(shapeKey));
        registry.register(item);
    }

    public void registerModel() {
        itemProvider.getValidMaterials().forEach(material -> ModelLoader.setCustomModelResourceLocation(item,
                material.index(), new ModelResourceLocation(modelFunction.apply(shapeKey, material), "inventory")));
    }

    // Builder
    public static final class Builder {

        public final HTShapeKey shapeKey;
        private final Function<HTShapeKey, ? extends IMaterialItemProvider> itemFunction;

        public Builder(HTShapeKey shapeKey, Function<HTShapeKey, ? extends IMaterialItemProvider> itemFunction) {
            this.shapeKey = shapeKey;
            this.itemFunction = itemFunction;
        }

        public Function<HTShapeKey, ResourceLocation> locationFunction = shapeKey -> new ResourceLocation(
                HTMaterialsAPI.MOD_ID, shapeKey.name());
        public BiFunction<HTShapeKey, HTMaterial, ResourceLocation> modelFunction = (shapeKey,
                                                                                     material) -> new ResourceLocation(
                                                                                             HTMaterialsAPI.MOD_ID,
                                                                                             shapeKey.name());

        HTShapeItemBuilder build() {
            return new HTShapeItemBuilder(shapeKey, itemFunction, locationFunction, modelFunction);
        }
    }
}
