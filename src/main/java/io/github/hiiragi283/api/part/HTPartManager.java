package io.github.hiiragi283.api.part;

import java.util.*;
import java.util.stream.Stream;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.github.bsideup.jabel.Desugar;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;

import io.github.hiiragi283.HMReference;
import io.github.hiiragi283.api.item.HTMetaItem;
import io.github.hiiragi283.api.material.HTMaterialKey;
import io.github.hiiragi283.api.shape.HTShapeKey;

public final class HTPartManager {

    public final ImmutableMap<HTMetaItem, Entry> itemEntryMap;
    public final ImmutableMultimap<HTPart, Entry> partEntryMultimap;

    public HTPartManager(Builder builder) {
        itemEntryMap = ImmutableMap.copyOf(builder.itemEntryMap);
        partEntryMultimap = ImmutableMultimap.copyOf(builder.partEntryMultimap);
    }

    // Object, int -> HTMetaItem -> Entry

    @NotNull
    public Stream<@NotNull Entry> getEntries(@NotNull ItemStack stack) {
        return getEntries(HTMetaItem.fromStack(stack));
    }

    @NotNull
    public Stream<@NotNull Entry> getEntries(@NotNull Object object, int meta) {
        return getEntries(HTMetaItem.from(object, meta));
    }

    @NotNull
    public Stream<@NotNull Entry> getEntries(@NotNull Stream<@NotNull HTMetaItem> stream) {
        return stream.filter(this::hasEntry).map(this::getEntry);
    }

    @Nullable
    public Entry getEntry(@NotNull HTMetaItem metaItem) {
        return itemEntryMap.get(metaItem);
    }

    @NotNull
    public Optional<Entry> getEntryOrEmpty(@NotNull HTMetaItem metaItem) {
        return Optional.ofNullable(getEntry(metaItem));
    }

    public boolean hasEntry(@NotNull HTMetaItem metaItem) {
        return itemEntryMap.containsKey(metaItem);
    }

    // HTPart -> Entry
    @Nullable
    Entry getDefaultEntry(@NotNull HTShapeKey shapeKey, @NotNull HTMaterialKey materialKey) {
        return getDefaultEntry(new HTPart(shapeKey, materialKey));
    }

    @NotNull
    public Optional<Entry> getDefaultOrEmpty(@NotNull HTShapeKey shapeKey, @NotNull HTMaterialKey materialKey) {
        return Optional.ofNullable(getDefaultEntry(shapeKey, materialKey));
    }

    @Nullable
    public Entry getDefaultEntry(@NotNull HTPart part) {
        var entries = new ArrayList<>(getEntries(part));
        for (Entry entry : entries) {
            ResourceLocation location = entry.metaItem.item.getRegistryName();
            Objects.requireNonNull(location);
            String namespace = location.getNamespace();
            if (namespace.equals("minecraft")) {
                return entry;
            } else if (namespace.equals(HMReference.MOD_ID)) {
                return entry;
            }
        }
        return entries.size() > 1 ? entries.get(0) : null;
    }

    @NotNull
    public Optional<Entry> getDefaultOrEmpty(@NotNull HTPart part) {
        return Optional.ofNullable(getDefaultEntry(part));
    }

    @NotNull
    public Collection<Entry> getEntries(@NotNull HTShapeKey shapeKey, @NotNull HTMaterialKey materialKey) {
        return getEntries(new HTPart(shapeKey, materialKey));
    }

    @NotNull
    public Collection<Entry> getEntries(@NotNull HTPart part) {
        return partEntryMultimap.get(part);
    }

    public boolean hasEntry(@NotNull HTShapeKey shapeKey, @NotNull HTMaterialKey materialKey) {
        return hasEntry(new HTPart(shapeKey, materialKey));
    }

    public boolean hasEntry(@NotNull HTPart part) {
        return partEntryMultimap.containsKey(part);
    }

    // Entry
    @Desugar
    public record Entry(HTPart part, HTMetaItem metaItem) {}

    // Builder

    public static final class Builder {

        Map<HTMetaItem, Entry> itemEntryMap = new HashMap<>();
        Multimap<HTPart, Entry> partEntryMultimap = HashMultimap.create();

        public void add(@NotNull HTShapeKey shapeKey, @NotNull HTMaterialKey materialKey, @NotNull ItemStack stack) {
            HTMetaItem.fromStack(stack).forEach(metaItem -> add(shapeKey, materialKey, metaItem));
        }

        public void add(@NotNull HTShapeKey shapeKey, @NotNull HTMaterialKey materialKey, @NotNull Object object,
                        int meta) {
            HTMetaItem.from(object, meta).forEach(metaItem -> add(shapeKey, materialKey, metaItem));
        }

        public void add(@NotNull HTShapeKey shapeKey, @NotNull HTMaterialKey materialKey,
                        @NotNull HTMetaItem metaItem) {
            HTPart part = new HTPart(shapeKey, materialKey);
            Entry entry = new Entry(part, metaItem);
            itemEntryMap.put(metaItem, entry);
            partEntryMultimap.put(part, entry);
        }
    }
}
