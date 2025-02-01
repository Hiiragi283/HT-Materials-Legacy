package hiiragi283.materials.common;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import net.minecraftforge.common.MinecraftForge;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import com.google.common.base.Preconditions;
import com.google.common.collect.HashBiMap;

import hiiragi283.materials.api.HTMaterialsAPI;
import hiiragi283.materials.api.HTMaterialsAddon;
import hiiragi283.materials.api.HTRegisterAddonEvent;
import hiiragi283.materials.api.mateial.HTMaterialRegistry;
import hiiragi283.materials.api.mateial.item.HTMaterialItem;
import hiiragi283.materials.api.mateial.part.HTPart;

public final class HTMaterialsAPIImpl implements HTMaterialsAPI {

  private static final Logger LOGGER =
      LogManager.getLogger(HTMaterialsAPIImpl.class.getSimpleName());
  static List<HTMaterialsAddon> addonList;
  private static Map<HTPart, HTMaterialItem> partItemMap;

  static void registerAddons() {
    List<HTMaterialsAddon> addons = new ArrayList<>();
    var event = new HTRegisterAddonEvent(addons);
    MinecraftForge.EVENT_BUS.post(event);
    addons.sort(Comparator.comparingInt(HTMaterialsAddon::getPriority));
    for (HTMaterialsAddon addon : addons) {
      LOGGER.info("- Priority: {} ... {}", addon.getPriority(), addon.getClass().getName());
    }
    addonList = addons;
    LOGGER.info("Registered addons!");
  }

  static void registerParts() {
    // Register Items
    Set<HTPart> parts = new HashSet<>();
    Consumer<HTPart> register =
        (HTPart part) ->
            Preconditions.checkArgument(parts.add(part), "Duplicate part: " + part.name);
    for (HTMaterialsAddon addon : addonList) {
      addon.registerPart(register);
    }

    partItemMap =
        HashBiMap.create(
            parts.stream().collect(Collectors.toMap(Function.identity(), HTPart::createItem)));
    LOGGER.info("Created material items!");
  }

  //    HTMaterialsAPI    //

  @Override
  public @NotNull Stream<HTMaterialsAddon> getAddons() {
    return addonList.stream();
  }

  @Override
  public @NotNull Map<HTPart, HTMaterialItem> getPartItemMap() {
    return partItemMap;
  }

  @Override
  public @NotNull HTMaterialRegistry getMaterialRegistry() {
    return HTMaterialRegistryImpl.INSTANCE;
  }
}
