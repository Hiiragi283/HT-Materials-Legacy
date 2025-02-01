package hiiragi283.materials.common;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import hiiragi283.materials.api.event.HTRegisterMaterialEvent;
import hiiragi283.materials.api.event.HTSetupPropertiesEvent;
import hiiragi283.materials.api.mateial.HTMaterialKey;
import hiiragi283.materials.api.mateial.HTMaterialRegistry;
import hiiragi283.materials.api.property.HTPropertyHolder;
import hiiragi283.materials.api.property.HTPropertyHolderBuilder;
import net.minecraftforge.common.MinecraftForge;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

enum HTMaterialRegistryImpl implements HTMaterialRegistry {
    INSTANCE;

    private static final Logger LOGGER = LogManager.getLogger(HTMaterialRegistryImpl.class.getSimpleName());
    private static BiMap<Integer, HTMaterialKey> materialMap;
    private static final Map<HTMaterialKey, HTPropertyHolder> propertyMap = new HashMap<>();

    static void registerMaterials() {
        // Collect materials
        Map<Integer, HTMaterialKey> materials = new TreeMap<>();
        var event = new HTRegisterMaterialEvent(materials);
        MinecraftForge.EVENT_BUS.post(event);

        materialMap = HashBiMap.create(materials);
        LOGGER.info("Collected materials!");
    }

    static void setupProperties() {
        // Setup properties
        Map<HTMaterialKey, HTPropertyHolderBuilder> properties = new HashMap<>();
        var event = new HTSetupPropertiesEvent(properties);
        MinecraftForge.EVENT_BUS.post(event);

        properties.forEach((HTMaterialKey materialKey, HTPropertyHolderBuilder builder) -> propertyMap.put(materialKey, builder.build()));
        LOGGER.info("Set up properties!");
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
    public @NotNull HTPropertyHolder getPropertyHolder(@NotNull HTMaterialKey materialKey) {
        return propertyMap.getOrDefault(materialKey, HTPropertyHolder.empty());
    }
}
