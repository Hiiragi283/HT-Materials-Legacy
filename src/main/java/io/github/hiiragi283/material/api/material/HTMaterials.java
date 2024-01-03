package io.github.hiiragi283.material.api.material;

import io.github.hiiragi283.material.api.HTMaterialsAddonManager;
import io.github.hiiragi283.material.api.material.flag.HTMaterialFlagSet;
import io.github.hiiragi283.material.api.material.property.HTComponentProperty;
import io.github.hiiragi283.material.api.material.property.HTMaterialProperty;
import io.github.hiiragi283.material.api.material.property.HTMaterialPropertyMap;
import io.github.hiiragi283.material.api.registry.HTNonNullMap;
import io.github.hiiragi283.material.api.registry.HTObjectKeySet;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public abstract class HTMaterials {

    private HTMaterials() {
    }

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
        HTMaterialsAddonManager.getAddons().forEach(addon -> addon.registerMaterialKey(materialKeySet));
    }

    private static final HTNonNullMap<HTMaterialKey, HTMaterialPropertyMap.Builder> propertyMap = HTNonNullMap.create(key -> new HTMaterialPropertyMap.Builder());

    private static void modifyMaterialProperty() throws IllegalAccessException {
        HTMaterialsAddonManager.getAddons().forEach(addon -> addon.modifyMaterialProperty(propertyMap));
    }

    private static final HTNonNullMap<HTMaterialKey, HTMaterialFlagSet.Builder> flagMap = HTNonNullMap.create(key -> new HTMaterialFlagSet.Builder());

    private static void modifyMaterialFlag() throws IllegalAccessException {
        HTMaterialsAddonManager.getAddons().forEach(addon -> addon.modifyMaterialFlag(flagMap));
    }

    private static final Map<HTMaterialKey, ColorConvertible> colorMap = new HashMap<>();

    private static void modifyMaterialColor() throws IllegalAccessException {
        HTMaterialsAddonManager.getAddons().forEach(addon -> addon.modifyMaterialColor(colorMap));
    }

    private static final Map<HTMaterialKey, FormulaConvertible> formulaMap = new HashMap<>();

    private static void modifyMaterialFormula() throws IllegalAccessException {
        HTMaterialsAddonManager.getAddons().forEach(addon -> addon.modifyMaterialFormula(formulaMap));
    }

    private static final Map<HTMaterialKey, MolarMassConvertible> molarMap = new HashMap<>();

    private static void modifyMaterialMolar() throws IllegalAccessException {
        HTMaterialsAddonManager.getAddons().forEach(addon -> addon.modifyMaterialMolar(molarMap));
    }

    @NotNull
    private static ColorConvertible getColor(HTMaterialKey key, HTMaterialPropertyMap map) {
        ColorConvertible color = null;
        for (HTMaterialProperty<?> property : map.values()) {
            if (property instanceof HTComponentProperty<?> component) {
                color = component;
            }
        }
        if (color == null) color = colorMap.get(key);
        return color == null ? ColorConvertible.EMPTY : color;
    }

    @NotNull
    private static FormulaConvertible getFormula(HTMaterialKey key, HTMaterialPropertyMap map) {
        FormulaConvertible formula = null;
        for (HTMaterialProperty<?> property : map.values()) {
            if (property instanceof HTComponentProperty<?> component) {
                formula = component;
            }
        }
        if (formula == null) formula = formulaMap.get(key);
        return formula == null ? FormulaConvertible.EMPTY : formula;
    }

    @NotNull
    private static MolarMassConvertible getMolar(HTMaterialKey key, HTMaterialPropertyMap map) {
        MolarMassConvertible molar = null;
        for (HTMaterialProperty<?> property : map.values()) {
            if (property instanceof HTComponentProperty<?> component) {
                molar = component;
            }
        }
        if (molar == null) molar = molarMap.get(key);
        return molar == null ? MolarMassConvertible.EMPTY : molar;
    }

    private static void createMaterial() {
        materialKeySet.forEach(key -> {
            HTMaterialPropertyMap property = propertyMap.getOrCreate(key).build();
            HTMaterialFlagSet flag = flagMap.getOrCreate(key).build();
            Color color = getColor(key, property).asColor();
            String formula = getFormula(key, property).asFormula();
            double molar = getMolar(key, property).asMolar();
            HTMaterialInfo info = new HTMaterialInfo(color, formula, molar);
            HTMaterial.create(key, info, property, flag);
        });
    }

}