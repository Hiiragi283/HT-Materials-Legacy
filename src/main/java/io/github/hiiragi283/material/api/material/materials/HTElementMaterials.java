package io.github.hiiragi283.material.api.material.materials;

import java.lang.reflect.Field;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import io.github.hiiragi283.material.api.material.ColorConvertible;
import io.github.hiiragi283.material.api.material.HTMaterialEvent;
import io.github.hiiragi283.material.api.material.HTMaterialKey;
import io.github.hiiragi283.material.api.material.flag.HTMaterialFlags;
import io.github.hiiragi283.material.api.material.property.HTFluidProperty;
import io.github.hiiragi283.material.api.material.property.HTMetalProperty;
import io.github.hiiragi283.material.util.HTColor;

@Mod.EventBusSubscriber
public enum HTElementMaterials {

    INSTANCE;

    // 1st Period //

    public static final HTMaterialKey HYDROGEN = new HTMaterialKey("hydrogen", 1);
    public static final HTMaterialKey HELIUM = new HTMaterialKey("helium", 2);

    // 2nd Period //

    public static final HTMaterialKey LITHIUM = new HTMaterialKey("lithium", 3);
    public static final HTMaterialKey BERYLLIUM = new HTMaterialKey("beryllium", 4);
    public static final HTMaterialKey BORON = new HTMaterialKey("boron", 5);
    public static final HTMaterialKey CARBON = new HTMaterialKey("carbon", 6);
    public static final HTMaterialKey NITROGEN = new HTMaterialKey("nitrogen", 7);
    public static final HTMaterialKey OXYGEN = new HTMaterialKey("oxygen", 8);
    public static final HTMaterialKey FLUORINE = new HTMaterialKey("fluorine", 9);
    public static final HTMaterialKey NEON = new HTMaterialKey("neon", 10);

    // 3rd Period //

    public static final HTMaterialKey SODIUM = new HTMaterialKey("sodium", 11);
    public static final HTMaterialKey MAGNESIUM = new HTMaterialKey("magnesium", 12);
    public static final HTMaterialKey ALUMINUM = new HTMaterialKey("aluminum", 13);
    public static final HTMaterialKey SILICON = new HTMaterialKey("silicon", 14);
    public static final HTMaterialKey PHOSPHORUS = new HTMaterialKey("phosphorus", 15);
    public static final HTMaterialKey SULFUR = new HTMaterialKey("sulfur", 16);
    public static final HTMaterialKey CHLORINE = new HTMaterialKey("chlorine", 17);

    // 4th Period //

    public static final HTMaterialKey IRON = new HTMaterialKey("iron", 28);

    // 5th Period //

    // 6th Period //

    public static final HTMaterialKey GOLD = new HTMaterialKey("gold", 79);

    // 7th Period //

    // Register //

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void registerMaterialKey(HTMaterialEvent.Register event) {
        for (Field field : HTElementMaterials.class.getDeclaredFields()) {
            field.setAccessible(true);
            try {
                Object obj = field.get(HTElementMaterials.INSTANCE);
                if (obj instanceof HTMaterialKey key) {
                    event.registry.add(key);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void modifyProperty(HTMaterialEvent.Property event) {
        var registry = event.registry;
        // 1st Period
        registry.getOrCreate(HYDROGEN)
                .add(new HTFluidProperty(), HTFluidProperty::setGaseous);
        registry.getOrCreate(HELIUM)
                .add(new HTFluidProperty(), HTFluidProperty::setGaseous);
        // 2nd Period
        registry.getOrCreate(LITHIUM)
                .add(HTMetalProperty.INSTANCE);
        registry.getOrCreate(BERYLLIUM)
                .add(HTMetalProperty.INSTANCE);
        registry.getOrCreate(BORON);
        registry.getOrCreate(CARBON);
        registry.getOrCreate(NITROGEN)
                .add(new HTFluidProperty(), HTFluidProperty::setGaseous);
        registry.getOrCreate(OXYGEN)
                .add(new HTFluidProperty(), HTFluidProperty::setGaseous);
        registry.getOrCreate(FLUORINE)
                .add(new HTFluidProperty(), HTFluidProperty::setGaseous);
        registry.getOrCreate(NEON)
                .add(new HTFluidProperty(), HTFluidProperty::setGaseous);
        // 3rd Period
        registry.getOrCreate(SODIUM);
        registry.getOrCreate(MAGNESIUM);
        registry.getOrCreate(ALUMINUM)
                .add(HTMetalProperty.INSTANCE);
        registry.getOrCreate(SILICON)
                .add(HTMetalProperty.INSTANCE);
        registry.getOrCreate(PHOSPHORUS);
        registry.getOrCreate(SULFUR);
        registry.getOrCreate(CHLORINE)
                .add(new HTFluidProperty(), HTFluidProperty::setGaseous);
        // 4th Period
        registry.getOrCreate(IRON)
                .add(HTMetalProperty.INSTANCE);
        // 6th Period
        registry.getOrCreate(GOLD)
                .add(HTMetalProperty.INSTANCE);
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void modifyFlag(HTMaterialEvent.Flag event) {
        var registry = event.registry;
        // 1st Period
        registry.getOrCreate(HYDROGEN);
        registry.getOrCreate(HELIUM);
        // 2nd Period
        registry.getOrCreate(LITHIUM);
        registry.getOrCreate(BERYLLIUM);
        registry.getOrCreate(BORON);
        registry.getOrCreate(CARBON)
                .add(HTMaterialFlags.GENERATE_DUST)
                .add(HTMaterialFlags.GENERATE_INGOT)
                .add(HTMaterialFlags.GENERATE_PLATE)
                .add(HTMaterialFlags.GENERATE_STICK);
        registry.getOrCreate(NITROGEN);
        registry.getOrCreate(OXYGEN);
        registry.getOrCreate(FLUORINE);
        registry.getOrCreate(NEON);
        // 3rd Period
        registry.getOrCreate(SODIUM);
        registry.getOrCreate(MAGNESIUM)
                .add(HTMaterialFlags.GENERATE_DUST);
        registry.getOrCreate(ALUMINUM)
                .add(HTMaterialFlags.GENERATE_DUST)
                .add(HTMaterialFlags.GENERATE_GEAR)
                .add(HTMaterialFlags.GENERATE_INGOT)
                .add(HTMaterialFlags.GENERATE_NUGGET)
                .add(HTMaterialFlags.GENERATE_PLATE)
                .add(HTMaterialFlags.GENERATE_STICK);
        registry.getOrCreate(SILICON)
                .add(HTMaterialFlags.GENERATE_DUST)
                .add(HTMaterialFlags.GENERATE_INGOT)
                .add(HTMaterialFlags.GENERATE_NUGGET)
                .add(HTMaterialFlags.GENERATE_PLATE);
        registry.getOrCreate(PHOSPHORUS)
                .add(HTMaterialFlags.GENERATE_DUST);
        registry.getOrCreate(SULFUR)
                .add(HTMaterialFlags.GENERATE_DUST);
        registry.getOrCreate(CHLORINE);
        // 4th Period
        registry.getOrCreate(IRON)
                .add(HTMaterialFlags.GENERATE_DUST)
                .add(HTMaterialFlags.GENERATE_GEAR)
                .add(HTMaterialFlags.GENERATE_PLATE)
                .add(HTMaterialFlags.GENERATE_STICK);
        // 6th Period
        registry.getOrCreate(GOLD)
                .add(HTMaterialFlags.GENERATE_DUST)
                .add(HTMaterialFlags.GENERATE_GEAR)
                .add(HTMaterialFlags.GENERATE_PLATE)
                .add(HTMaterialFlags.GENERATE_STICK);
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void modifyColor(HTMaterialEvent.Color event) {
        // 1st Period
        event.put(HYDROGEN, () -> HTColor.BLUE);
        event.put(HELIUM, () -> HTColor.YELLOW);
        // 2nd Period
        event.put(LITHIUM, () -> HTColor.GRAY);
        event.put(BERYLLIUM, () -> HTColor.DARK_GREEN);
        event.put(BORON, () -> HTColor.GRAY);
        event.put(CARBON, ColorConvertible.ofColor(HTColor.BLACK, HTColor.DARK_GRAY));
        event.put(NITROGEN, () -> HTColor.AQUA);
        event.put(OXYGEN, ColorConvertible.EMPTY);
        event.put(FLUORINE, () -> HTColor.GREEN);
        event.put(NEON, () -> HTColor.LIGHT_PURPLE);
        // 3rd Period
        event.put(SODIUM, ColorConvertible.ofColor(builder -> {
            builder.put(HTColor.DARK_BLUE, 1);
            builder.put(HTColor.BLUE, 4);
        }));
        event.put(MAGNESIUM, () -> HTColor.GRAY);
        event.put(ALUMINUM, ColorConvertible.ofColor(builder -> {
            builder.put(HTColor.BLUE, 1);
            builder.put(HTColor.WHITE, 5);
        }));
        event.put(SILICON, ColorConvertible.ofColor(builder -> {
            builder.put(HTColor.BLACK, 2);
            builder.put(HTColor.GRAY, 1);
            builder.put(HTColor.BLUE, 1);
        }));
        event.put(PHOSPHORUS, () -> HTColor.YELLOW);
        event.put(SULFUR, ColorConvertible.ofColor(HTColor.GOLD, HTColor.YELLOW));
        event.put(CHLORINE, () -> HTColor.YELLOW);
        // 4th Period
        event.put(IRON, ColorConvertible.EMPTY);
        // 6th Period
        event.put(GOLD, ColorConvertible.ofColor(HTColor.GOLD, HTColor.YELLOW));
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void modifyFormula(HTMaterialEvent.Formula event) {
        // 1st Period
        event.put(HYDROGEN, () -> "H");
        event.put(HELIUM, () -> "He");
        // 2nd Period
        event.put(LITHIUM, () -> "Li");
        event.put(BERYLLIUM, () -> "Be");
        event.put(BORON, () -> "B");
        event.put(CARBON, () -> "C");
        event.put(NITROGEN, () -> "N");
        event.put(OXYGEN, () -> "O");
        event.put(FLUORINE, () -> "F");
        event.put(NEON, () -> "Ne");
        // 3rd Period
        event.put(SODIUM, () -> "Na");
        event.put(MAGNESIUM, () -> "Mg");
        event.put(ALUMINUM, () -> "Al");
        event.put(SILICON, () -> "Si");
        event.put(PHOSPHORUS, () -> "P");
        event.put(SULFUR, () -> "S");
        event.put(CHLORINE, () -> "Cl");
        // 4th Period
        event.put(IRON, () -> "Fe");
        // 6th Period
        event.put(GOLD, () -> "Au");
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void modifyMolar(HTMaterialEvent.Molar event) {
        // 1st Period
        event.put(HYDROGEN, () -> 1.0);
        event.put(HELIUM, () -> 4.0);
        // 2nd Period
        event.put(LITHIUM, () -> 6.9);
        event.put(BERYLLIUM, () -> 9.0);
        event.put(BORON, () -> 10.8);
        event.put(CARBON, () -> 12.0);
        event.put(NITROGEN, () -> 14.0);
        event.put(OXYGEN, () -> 16.0);
        event.put(FLUORINE, () -> 19.0);
        event.put(NEON, () -> 20.2);
        // 3rd Period
        event.put(SODIUM, () -> 23.0);
        event.put(MAGNESIUM, () -> 24.3);
        event.put(ALUMINUM, () -> 27.0);
        event.put(SILICON, () -> 28.1);
        event.put(PHOSPHORUS, () -> 31.0);
        event.put(SULFUR, () -> 32.1);
        event.put(CHLORINE, () -> 35.5);
        // 4th Period
        event.put(IRON, () -> 55.8);
        // 6th Period
        event.put(GOLD, () -> 197.0);
    }
}
