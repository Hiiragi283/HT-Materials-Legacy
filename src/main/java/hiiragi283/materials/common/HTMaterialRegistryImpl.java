package hiiragi283.materials.common;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Stream;

import net.minecraftforge.oredict.OreDictionary;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.google.common.base.Preconditions;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.mojang.realmsclient.util.Pair;

import hiiragi283.materials.api.HTMaterialsAPI;
import hiiragi283.materials.api.HTMaterialsAddon;
import hiiragi283.materials.api.mateial.HTMaterialKey;
import hiiragi283.materials.api.mateial.HTMaterialRegistry;
import hiiragi283.materials.api.mateial.item.HTMaterialItem;
import hiiragi283.materials.api.property.HTPropertyHolder;
import hiiragi283.materials.api.property.HTPropertyHolderBuilder;

enum HTMaterialRegistryImpl implements HTMaterialRegistry {
  INSTANCE;

  private static final Logger LOGGER =
      LogManager.getLogger(HTMaterialRegistryImpl.class.getSimpleName());
  private static BiMap<Integer, HTMaterialKey> materialMap;
  private static final Map<HTMaterialKey, HTPropertyHolder> propertyMap = new HashMap<>();

  static void registerMaterials() {
    // Collect materials
    Map<Integer, HTMaterialKey> materials = new TreeMap<>();
    BiConsumer<HTMaterialKey, Integer> register =
        (HTMaterialKey material, Integer index) -> {
          Preconditions.checkArgument(index > 0, "index must be greater than 0");
          Preconditions.checkArgument(
              index < OreDictionary.WILDCARD_VALUE, "index must be smaller than wildcard value");
          Preconditions.checkArgument(
              materials.get(index) == null,
              "The index: " + index + " has already bound to " + material);
          materials.put(index, material);
        };
    HTMaterialsAPI.INSTANCE
        .getAddons()
        .forEach((HTMaterialsAddon addon) -> addon.registerMaterial(register));

    materialMap = HashBiMap.create(materials);
    LOGGER.info("Collected materials!");
  }

  static void setupProperties() {
    // Setup properties
    Map<HTMaterialKey, HTPropertyHolderBuilder> properties = new HashMap<>();
    Function<HTMaterialKey, HTPropertyHolderBuilder> register =
        (HTMaterialKey key) ->
            properties.computeIfAbsent(key, (HTMaterialKey k) -> new HTPropertyHolderBuilder());
    HTMaterialsAPI.INSTANCE
        .getAddons()
        .forEach((HTMaterialsAddon addon) -> addon.setupProperties(register));

    properties.forEach(
        (HTMaterialKey materialKey, HTPropertyHolderBuilder builder) ->
            propertyMap.put(materialKey, builder.build()));
    LOGGER.info("Set up properties!");
  }

  static void registerOreDicts() {
    for (HTMaterialItem item : HTMaterialsAPI.INSTANCE.getPartItemMap().values()) {
      item.getValidMaterials()
          .forEach(
              (HTMaterialKey material) ->
                  OreDictionary.registerOre(
                      item.getPart().createOreDict(material), item.getStackFromMaterial(material)));
    }

    LOGGER.info("Registered Ore Dictionary!");
  }

  //    HTMaterialRegistry    //

  @Override
  public @NotNull Set<HTMaterialKey> getMaterials() {
    return materialMap.values();
  }

  @Override
  public @NotNull OptionalInt getIndex(@NotNull HTMaterialKey materialKey) {
    int index = materialMap.inverse().getOrDefault(materialKey, 0);
    return index == 0 ? OptionalInt.empty() : OptionalInt.of(index);
  }

  @Override
  public @Nullable HTMaterialKey getMaterialFromIndex(int index) {
    return materialMap.get(index);
  }

  @Override
  public @NotNull Stream<Pair<HTMaterialKey, Integer>> getIndexedMaterials() {
    return materialMap.entrySet().stream()
        .map(
            (Map.Entry<Integer, HTMaterialKey> entry) -> Pair.of(entry.getValue(), entry.getKey()));
  }

  @Override
  public @NotNull HTPropertyHolder getPropertyHolder(@NotNull HTMaterialKey materialKey) {
    return propertyMap.getOrDefault(materialKey, HTPropertyHolder.empty());
  }
}
