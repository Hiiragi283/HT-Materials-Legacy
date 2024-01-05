package io.github.hiiragi283.material.compat.crt;

import crafttweaker.annotations.ZenRegister;
import io.github.hiiragi283.material.api.material.ColorConvertible;
import io.github.hiiragi283.material.api.material.FormulaConvertible;
import io.github.hiiragi283.material.api.material.HTMaterialKey;
import io.github.hiiragi283.material.api.material.MolarMassConvertible;
import io.github.hiiragi283.material.api.material.flag.HTMaterialFlagSet;
import io.github.hiiragi283.material.api.material.property.HTMaterialPropertyMap;
import io.github.hiiragi283.material.api.registry.HTNonNullMap;
import io.github.hiiragi283.material.api.registry.HTObjectKeySet;
import org.jetbrains.annotations.NotNull;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import java.util.Map;

@SuppressWarnings("unused")
@ZenClass(HTCrTPlugin.MATERIAL_PREFIX + "HTMaterialRegistry")
@ZenRegister
public final class HTCrTMaterialRegistry {

    private static HTCrTMaterialRegistry INSTANCE;

    private final HTObjectKeySet<HTMaterialKey> materialKeys;
    private final HTNonNullMap<HTMaterialKey, HTMaterialPropertyMap.Builder> propertyMap;
    private final HTNonNullMap<HTMaterialKey, HTMaterialFlagSet.Builder> flagMap;
    private final Map<HTMaterialKey, ColorConvertible> colorMap;
    private final Map<HTMaterialKey, FormulaConvertible> formulaMap;
    private final Map<HTMaterialKey, MolarMassConvertible> molarMap;

    public HTCrTMaterialRegistry(HTObjectKeySet<HTMaterialKey> materialKeys, HTNonNullMap<HTMaterialKey, HTMaterialPropertyMap.Builder> propertyMap, HTNonNullMap<HTMaterialKey, HTMaterialFlagSet.Builder> flagMap, Map<HTMaterialKey, ColorConvertible> colorMap, Map<HTMaterialKey, FormulaConvertible> formulaMap, Map<HTMaterialKey, MolarMassConvertible> molarMap) {
        this.materialKeys = materialKeys;
        this.propertyMap = propertyMap;
        this.flagMap = flagMap;
        this.colorMap = colorMap;
        this.formulaMap = formulaMap;
        this.molarMap = molarMap;
        INSTANCE = this;
    }

    @ZenMethod
    public static void registerMaterialKey(HTMaterialKey materialKey) {
        INSTANCE.materialKeys.add(materialKey);
    }

    @NotNull
    @ZenMethod
    public static HTMaterialPropertyMap.Builder propertyMapBuilder(HTMaterialKey materialKey) {
        return INSTANCE.propertyMap.getOrCreate(materialKey);
    }

    @NotNull
    @ZenMethod
    public static HTMaterialFlagSet.Builder flagSetBuilder(HTMaterialKey materialKey) {
        return INSTANCE.flagMap.getOrCreate(materialKey);
    }

    @ZenMethod
    public static void setColor(HTMaterialKey materialKey, ColorConvertible color) {
        INSTANCE.colorMap.put(materialKey, color);
    }

    @ZenMethod
    public static void setFormula(HTMaterialKey materialKey, FormulaConvertible formula) {
        INSTANCE.formulaMap.put(materialKey, formula);
    }

    @ZenMethod
    public static void setMolar(HTMaterialKey materialKey, MolarMassConvertible molar) {
        INSTANCE.molarMap.put(materialKey, molar);
    }

}