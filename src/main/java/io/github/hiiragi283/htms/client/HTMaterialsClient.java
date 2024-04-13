package io.github.hiiragi283.htms.client;

import java.util.Objects;
import java.util.Optional;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import org.jetbrains.annotations.NotNull;

import com.google.gson.JsonObject;

import io.github.hiiragi283.htms.api.HTMaterialsAPI;
import io.github.hiiragi283.htms.api.item.IMaterialItemProvider;
import io.github.hiiragi283.htms.api.material.HTMaterial;
import io.github.hiiragi283.htms.api.resource.HTRuntimeResourcePack;
import io.github.hiiragi283.htms.api.shape.HTShape;

public enum HTMaterialsClient {

    INSTANCE;

    @SubscribeEvent
    public void onModelRegister(ModelRegistryEvent event) {
        for (IMaterialItemProvider provider : HTMaterialsAPI.INSTANCE.getMaterialContentRegistry().values()) {
            provider.getMaterials().forEach(material -> {
                HTShape shape = provider.getShape();
                ResourceLocation modelLocation = HTMaterialsAPI.getId(provider.getShape() + "/" + material);
                ModelLoader.setCustomModelResourceLocation(provider.asItem(), material.index(),
                        new ModelResourceLocation(modelLocation, "inventory"));
                HTRuntimeResourcePack.putItemModel(modelLocation, createItemModelJson(shape, material));
            });
        }
        registerItemModel(HTMaterialsAPI.INSTANCE.getIconItem());
        registerItemModel(HTMaterialsAPI.INSTANCE.getDictionaryItem());
        HTMaterialsAPI.LOGGER.info("Registered item models");
    }

    private @NotNull JsonObject createItemModelJson(@NotNull HTShape shape, @NotNull HTMaterial material) {
        ResourceLocation textureLocation = HTMaterialsAPI.getId(material.type.getTexturePath(shape));
        Optional<ResourceLocation> overlayLocation = Optional.ofNullable(material.type.getOverlayPath(shape))
                .map(HTMaterialsAPI::getId);
        JsonObject rootJson = new JsonObject();
        rootJson.addProperty("parent", "item/generated");
        JsonObject textureJson = new JsonObject();
        textureJson.addProperty("layer0", textureLocation.toString());
        overlayLocation.ifPresent(location -> textureJson.addProperty("layer1", location.toString()));
        rootJson.add("textures", textureJson);
        return rootJson;
    }

    private void registerItemModel(@NotNull Item item) {
        ModelLoader.setCustomModelResourceLocation(item, 0,
                new ModelResourceLocation(Objects.requireNonNull(item.getRegistryName()), "inventory"));
    }

    @SubscribeEvent
    public void onItemColored(ColorHandlerEvent.Item event) {
        HTMaterialsAPI.INSTANCE.getMaterialContentRegistry().values().forEach(
                provider -> event.getItemColors().registerItemColorHandler(provider::getItemColor, provider.asItem()));
        HTMaterialsAPI.LOGGER.info("Registered item coloring");
    }
}
