package io.github.hiiragi283.material.api.material;

import io.github.hiiragi283.material.api.material.flag.HTMaterialFlagSet;
import io.github.hiiragi283.material.api.material.property.HTComponentProperty;
import io.github.hiiragi283.material.api.material.property.HTMaterialProperty;
import io.github.hiiragi283.material.api.material.property.HTMaterialPropertyMap;
import io.github.hiiragi283.material.api.registry.HTNonNullMap;
import io.github.hiiragi283.material.api.registry.HTObjectKeySet;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Map;

public abstract class HTMaterials {

    private HTMaterials() {
    }

    private static HTObjectKeySet<HTMaterialKey> materialKeys;
    private static HTNonNullMap<HTMaterialKey, HTMaterialPropertyMap.Builder> propertyMap;
    private static HTNonNullMap<HTMaterialKey, HTMaterialFlagSet.Builder> flagMap;
    private static Map<HTMaterialKey, ColorConvertible> colorMap;
    private static Map<HTMaterialKey, FormulaConvertible> formulaMap;
    private static Map<HTMaterialKey, MolarMassConvertible> molarMap;

    public static void init() {
        materialKeys = HTMaterialEvent.register();
        propertyMap = HTMaterialEvent.property();
        flagMap = HTMaterialEvent.flag();
        colorMap = HTMaterialEvent.color();
        formulaMap = HTMaterialEvent.formula();
        molarMap = HTMaterialEvent.molar();
        createMaterials();
    }

    private static void createMaterials() {
        materialKeys.forEach(key -> {
            HTMaterialPropertyMap property = propertyMap.getOrCreate(key).build();
            HTMaterialFlagSet flag = flagMap.getOrCreate(key).build();
            Color color = getColor(key, property).asColor();
            String formula = getFormula(key, property).asFormula();
            double molar = getMolar(key, property).asMolar();
            HTMaterialInfo info = new HTMaterialInfo(color, formula, molar);
            HTMaterial.create(key, info, property, flag);
        });
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

}