package io.github.hiiragi283;

import java.util.Comparator;
import java.util.stream.Collectors;

import net.minecraftforge.fml.common.Loader;

import io.github.hiiragi283.api.HTMaterialsAPI;
import io.github.hiiragi283.api.HTMaterialsAddon;
import io.github.hiiragi283.api.extension.ServiceLoaderUtil;
import io.github.hiiragi283.api.part.HTPartManager;

final class HTMaterialsCore {

    private static Iterable<HTMaterialsAddon> addons;

    public static void initAddons() {
        addons = ServiceLoaderUtil.getInstances(HTMaterialsAddon.class)
                .filter(addon -> Loader.isModLoaded(addon.getModId()))
                .sorted(Comparator.comparing(HTMaterialsAddon::getPriority)
                        .thenComparing(addon -> addon.getClass().getCanonicalName()))
                .collect(Collectors.toList());
        // Print sorted addons
        HTMaterialsAPI.LOGGER.info("HTMaterialsAddon collected!");
        HTMaterialsAPI.LOGGER.info("=== List ===");
        addons.forEach(addon -> HTMaterialsAPI.LOGGER.info("{} - Priority: {}", addon.getClass().getCanonicalName(),
                addon.getPriority()));
        HTMaterialsAPI.LOGGER.info("============");
    }

    // PreInit

    // Init

    public static void bindItemToPart() {
        HTPartManager.Builder builder = new HTPartManager.Builder();
        addons.forEach(addon -> addon.bindItemToPart(builder));
        HTMaterialsAPIImpl.partManager = new HTPartManager(builder);
    }
}
