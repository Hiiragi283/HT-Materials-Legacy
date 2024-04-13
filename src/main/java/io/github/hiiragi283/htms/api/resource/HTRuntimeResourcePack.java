package io.github.hiiragi283.htms.api.resource;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import net.minecraft.client.resources.IResourcePack;
import net.minecraft.client.resources.data.IMetadataSection;
import net.minecraft.client.resources.data.MetadataSerializer;
import net.minecraft.util.ResourceLocation;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import io.github.hiiragi283.htms.api.HMConstants;
import io.github.hiiragi283.htms.api.HTMaterialsAPI;

public enum HTRuntimeResourcePack implements IResourcePack {

    INSTANCE;

    @NotNull
    private static final Set<@NotNull String> DOMAINS = new HashSet<>();

    public static void addDomains(@NotNull String... domains) {
        DOMAINS.addAll(Arrays.asList(domains));
    }

    @NotNull
    private static final ConcurrentMap<@NotNull ResourceLocation, @NotNull JsonObject> DATA = new ConcurrentHashMap<>();

    public static void putData(@NotNull ResourceLocation location, @NotNull JsonObject data) {
        HTMaterialsAPI.LOGGER.debug("Put location; " + location);
        DATA.put(location, data);
    }

    public static void putItemModel(@NotNull ResourceLocation location, @NotNull JsonObject model) {
        ResourceLocation fixedLocation = new ResourceLocation(location.getNamespace(), "item/" + location.getPath());
        putModel(fixedLocation, model);
    }

    public static void putModel(@NotNull ResourceLocation location, @NotNull JsonObject model) {
        ResourceLocation fixedLocation = new ResourceLocation(location.getNamespace(),
                "models/" + location.getPath() + ".json");
        putData(fixedLocation, model);
    }

    static {
        DOMAINS.add("minecraft");
        DOMAINS.add("forge");
        DOMAINS.add(HMConstants.MOD_ID);
    }

    // IResourcePack //

    @Override
    public @NotNull InputStream getInputStream(@NotNull ResourceLocation location) throws IOException {
        return Optional.ofNullable(DATA.get(location))
                .map(JsonElement::toString)
                .map(s -> s.getBytes(StandardCharsets.UTF_8))
                .map(ByteArrayInputStream::new)
                .orElseThrow(() -> new FileNotFoundException(location.toString()));
    }

    @Override
    public boolean resourceExists(@NotNull ResourceLocation location) {
        return DATA.containsKey(location);
    }

    @Override
    public @NotNull Set<String> getResourceDomains() {
        return DOMAINS;
    }

    @Nullable
    @Override
    public <T extends IMetadataSection> T getPackMetadata(@NotNull MetadataSerializer metadataSerializer,
                                                          @NotNull String metadataSectionName) throws IOException {
        return null;
    }

    @Override
    public @NotNull BufferedImage getPackImage() throws IOException {
        throw new IOException("Not implemented yet");
    }

    @Override
    public @NotNull String getPackName() {
        return "HT Runtime Resource Pack";
    }
}
