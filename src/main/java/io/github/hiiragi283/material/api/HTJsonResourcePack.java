package io.github.hiiragi283.material.api;

import com.google.common.collect.ImmutableSet;
import com.google.gson.JsonElement;
import io.github.hiiragi283.material.HMReference;
import io.github.hiiragi283.material.util.HTUtils;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.client.resources.data.IMetadataSection;
import net.minecraft.client.resources.data.MetadataSerializer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@SideOnly(Side.CLIENT)
public enum HTJsonResourcePack implements IResourcePack {
    INSTANCE;

    private static final Logger LOGGER = LogManager.getLogger(HTJsonResourcePack.class.getSimpleName());

    private static final ConcurrentMap<ResourceLocation, JsonElement> resourceMap = new ConcurrentHashMap<>();

    public static void addResource(ResourceLocation location, JsonElement jsonElement) {
        resourceMap.put(location, jsonElement);
    }

    //    IResourcePack    //

    @NotNull
    @Override
    public InputStream getInputStream(@NotNull ResourceLocation location) {
        return Optional.ofNullable(resourceMap.get(location))
                .map(JsonElement::toString)
                .map(String::getBytes)
                .map(ByteArrayInputStream::new)
                .orElse(new ByteArrayInputStream(new byte[0]));
    }

    @Override
    public boolean resourceExists(@NotNull ResourceLocation location) {
        return resourceMap.containsKey(location);
    }

    @NotNull
    @Override
    public Set<String> getResourceDomains() {
        ImmutableSet.Builder<String> builder = ImmutableSet.builder();
        builder.add("minecraft");
        builder.add(HMReference.MOD_ID);
        return builder.build();
    }

    @Override
    public <T extends IMetadataSection> T getPackMetadata(@NotNull MetadataSerializer metadataSerializer, @NotNull String metadataSectionName) {
        return metadataSerializer.parseMetadataSection(metadataSectionName, HTUtils.buildJson(metadata -> metadata.add("pack", HTUtils.buildJson(pack -> {
            pack.addProperty("description", "");
            pack.addProperty("pack_format", 3);
        }))));
    }

    @NotNull
    @Override
    public BufferedImage getPackImage() throws IOException {
        throw new IOException();
    }

    @NotNull
    @Override
    public String getPackName() {
        return "HT Materials' Dynamic Resource Pack";
    }

}