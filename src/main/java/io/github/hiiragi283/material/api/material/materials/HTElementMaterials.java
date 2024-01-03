package io.github.hiiragi283.material.api.material.materials;

import io.github.hiiragi283.material.api.HTMaterialsAddon;
import io.github.hiiragi283.material.api.material.ColorConvertible;
import io.github.hiiragi283.material.api.material.FormulaConvertible;
import io.github.hiiragi283.material.api.material.HTMaterialKey;
import io.github.hiiragi283.material.api.material.MolarMassConvertible;
import io.github.hiiragi283.material.api.material.flag.HTMaterialFlagSet;
import io.github.hiiragi283.material.api.material.flag.HTMaterialFlags;
import io.github.hiiragi283.material.api.material.property.HTMaterialPropertyMap;
import io.github.hiiragi283.material.api.material.property.HTMetalProperty;
import io.github.hiiragi283.material.api.registry.HTNonNullMap;
import io.github.hiiragi283.material.api.registry.HTObjectKeySet;
import io.github.hiiragi283.material.util.HTColor;

import java.lang.reflect.Field;
import java.util.Map;

public enum HTElementMaterials implements HTMaterialsAddon {
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

    //    HTMaterialsAddon    //

    @Override
    public int getPriority() {
        return -100;
    }

    @Override
    public void registerMaterialKey(HTObjectKeySet<HTMaterialKey> registry) {
        for (Field field : HTElementMaterials.class.getDeclaredFields()) {
            field.setAccessible(true);
            try {
                Object obj = field.get(this);
                if (obj instanceof HTMaterialKey key) {
                    registry.add(key);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void modifyMaterialProperty(HTNonNullMap<HTMaterialKey, HTMaterialPropertyMap.Builder> registry) {
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

    @Override
    public void modifyMaterialFlag(HTNonNullMap<HTMaterialKey, HTMaterialFlagSet.Builder> registry) {
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

    @Override
    public void modifyMaterialColor(Map<HTMaterialKey, ColorConvertible> registry) {
        //1st Period
        registry.put(HYDROGEN, () -> HTColor.BLUE);
        registry.put(HELIUM, () -> HTColor.YELLOW);
        //4th Period
        registry.put(IRON, () -> HTColor.WHITE);
        //6th Period
        registry.put(GOLD, ColorConvertible.ofColor(HTColor.GOLD, HTColor.YELLOW));
    }

    @Override
    public void modifyMaterialFormula(Map<HTMaterialKey, FormulaConvertible> registry) {
        //1st Period
        registry.put(HYDROGEN, () -> "H");
        registry.put(HELIUM, () -> "He");
        //4th Period
        registry.put(IRON, () -> "Fe");
        //6th Period
        registry.put(GOLD, () -> "Au");
    }

    @Override
    public void modifyMaterialMolar(Map<HTMaterialKey, MolarMassConvertible> registry) {
        //1st Period
        registry.put(HYDROGEN, () -> 1.0);
        registry.put(HELIUM, () -> 4.0);
        //4th Period
        registry.put(IRON, () -> 55.8);
        //6th Period
        registry.put(GOLD, () -> 197.0);
    }

}