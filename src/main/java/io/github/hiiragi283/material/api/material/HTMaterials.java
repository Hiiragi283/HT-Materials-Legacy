package io.github.hiiragi283.material.api.material;

import io.github.hiiragi283.material.HMCommonProxy;
import io.github.hiiragi283.material.api.material.flag.HTMaterialFlagSet;
import io.github.hiiragi283.material.api.material.property.HTMaterialPropertyMap;
import io.github.hiiragi283.material.api.registry.HTNonNullMap;
import io.github.hiiragi283.material.api.registry.HTObjectKeySet;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class HTMaterials {

    //    Init    //

    public static void init() throws IllegalAccessException {
        registerMaterialKey();
        modifyMaterialProperty();
        modifyMaterialFlag();
        modifyMaterialColor();
        modifyMaterialFormula();
        modifyMaterialMolar();
        createMaterial();
    }

    private static final HTObjectKeySet<HTMaterialKey> materialKeySet = HTObjectKeySet.create();

    private static void registerMaterialKey() throws IllegalAccessException {
        HMCommonProxy.getAddons().forEach(addon -> addon.registerMaterialKey(materialKeySet));
    }

    private static final HTNonNullMap<HTMaterialKey, HTMaterialPropertyMap.Builder> propertyMap = HTNonNullMap.create(key -> new HTMaterialPropertyMap.Builder());

    private static void modifyMaterialProperty() throws IllegalAccessException {
        HMCommonProxy.getAddons().forEach(addon -> addon.modifyMaterialProperty(propertyMap));
    }

    private static final HTNonNullMap<HTMaterialKey, HTMaterialFlagSet.Builder> flagMap = HTNonNullMap.create(key -> new HTMaterialFlagSet.Builder());

    private static void modifyMaterialFlag() throws IllegalAccessException {
        HMCommonProxy.getAddons().forEach(addon -> addon.modifyMaterialFlag(flagMap));
    }

    private static final Map<HTMaterialKey, ColorConvertible> colorMap = new HashMap<>();

    private static void modifyMaterialColor() throws IllegalAccessException {
        HMCommonProxy.getAddons().forEach(addon -> addon.modifyMaterialColor(colorMap));
    }

    private static final Map<HTMaterialKey, FormulaConvertible> formulaMap = new HashMap<>();

    private static void modifyMaterialFormula() throws IllegalAccessException {
        HMCommonProxy.getAddons().forEach(addon -> addon.modifyMaterialFormula(formulaMap));
    }

    private static final Map<HTMaterialKey, MolarMassConvertible> molarMap = new HashMap<>();

    private static void modifyMaterialMolar() throws IllegalAccessException {
        HMCommonProxy.getAddons().forEach(addon -> addon.modifyMaterialMolar(molarMap));
    }

    private static void createMaterial() {
        materialKeySet.forEach(key -> {
            HTMaterialPropertyMap property = propertyMap.getOrCreate(key).build();
            HTMaterialFlagSet flag = flagMap.getOrCreate(key).build();
            Color color = colorMap.getOrDefault(key, ColorConvertible.EMPTY).asColor();
            String formula = formulaMap.getOrDefault(key, FormulaConvertible.EMPTY).asFormula();
            double molar = molarMap.getOrDefault(key, MolarMassConvertible.EMPTY).asMolar();
            HTMaterialInfo info = new HTMaterialInfo(color, formula, molar);
            HTMaterial.create(key, info, property, flag);
        });
    }

}