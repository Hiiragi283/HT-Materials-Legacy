package io.github.hiiragi283.material.api.material.materials;

import io.github.hiiragi283.material.api.material.ColorConvertible;
import io.github.hiiragi283.material.api.material.HTMaterialEvent;
import io.github.hiiragi283.material.api.material.HTMaterialKey;
import io.github.hiiragi283.material.api.material.flag.HTMaterialFlags;
import io.github.hiiragi283.material.api.material.property.HTMetalProperty;
import io.github.hiiragi283.material.util.HTColor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.lang.reflect.Field;

@Mod.EventBusSubscriber
public enum HTElementMaterials {
    INSTANCE;

    //    1st Period    //

    public static final HTMaterialKey HYDROGEN = new HTMaterialKey("hydrogen", 1);
    public static final HTMaterialKey HELIUM = new HTMaterialKey("helium", 2);

    //    2nd Period    //

    public static final HTMaterialKey OXYGEN = new HTMaterialKey("oxygen", 8);

    //    3rd Period    //

    //    4th Period    //

    public static final HTMaterialKey IRON = new HTMaterialKey("iron", 28);

    //    5th Period    //

    //    6th Period    //

    public static final HTMaterialKey GOLD = new HTMaterialKey("gold", 79);

    //    7th Period    //

    //    Register    //

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
        //1st Period
        registry.getOrCreate(HYDROGEN);
        registry.getOrCreate(HELIUM);
        //4th Period
        registry.getOrCreate(IRON)
                .add(HTMetalProperty.INSTANCE);
        //6th Period
        registry.getOrCreate(GOLD)
                .add(HTMetalProperty.INSTANCE);
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void modifyFlag(HTMaterialEvent.Flag event) {
        var registry = event.registry;
        //1st Period
        //4th Period
        registry.getOrCreate(IRON)
                .add(HTMaterialFlags.GENERATE_DUST)
                .add(HTMaterialFlags.GENERATE_GEAR)
                .add(HTMaterialFlags.GENERATE_PLATE)
                .add(HTMaterialFlags.GENERATE_STICK);
        //6th Period
        registry.getOrCreate(GOLD)
                .add(HTMaterialFlags.GENERATE_DUST)
                .add(HTMaterialFlags.GENERATE_GEAR)
                .add(HTMaterialFlags.GENERATE_PLATE)
                .add(HTMaterialFlags.GENERATE_STICK);
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void modifyColor(HTMaterialEvent.Color event) {
        //1st Period
        event.put(HYDROGEN, () -> HTColor.BLUE);
        event.put(HELIUM, () -> HTColor.YELLOW);
        //4th Period
        event.put(IRON, () -> HTColor.WHITE);
        //6th Period
        event.put(GOLD, ColorConvertible.ofColor(HTColor.GOLD, HTColor.YELLOW));
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void modifyFormula(HTMaterialEvent.Formula event) {
        //1st Period
        event.put(HYDROGEN, () -> "H");
        event.put(HELIUM, () -> "He");
        //4th Period
        event.put(IRON, () -> "Fe");
        //6th Period
        event.put(GOLD, () -> "Au");
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void modifyMolar(HTMaterialEvent.Molar event) {
        //1st Period
        event.put(HYDROGEN, () -> 1.0);
        event.put(HELIUM, () -> 4.0);
        //4th Period
        event.put(IRON, () -> 55.8);
        //6th Period
        event.put(GOLD, () -> 197.0);
    }

}