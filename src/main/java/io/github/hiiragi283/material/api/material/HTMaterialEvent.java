package io.github.hiiragi283.material.api.material;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.Event;

import org.jetbrains.annotations.NotNull;

import com.google.common.collect.ImmutableBiMap;

import io.github.hiiragi283.material.api.material.flag.HTMaterialFlagSet;
import io.github.hiiragi283.material.api.material.property.HTMaterialProperty;
import io.github.hiiragi283.material.api.material.property.HTMaterialPropertyMap;
import io.github.hiiragi283.material.api.material.property.component.HTComponentPropertyBase;
import io.github.hiiragi283.material.api.registry.HTNonNullMap;

public abstract class HTMaterialEvent extends Event {

    private abstract static class NonNullMap<V> extends HTMaterialEvent {

        @NotNull
        public final HTNonNullMap<HTMaterialKey, V> registry;

        protected NonNullMap(@NotNull HTNonNullMap<HTMaterialKey, V> registry) {
            this.registry = registry;
        }
    }

    public abstract static class SimpleMap<V> extends HTMaterialEvent {

        @NotNull
        public final Map<HTMaterialKey, V> registry;

        private final V defaultValue;

        protected SimpleMap(@NotNull Map<HTMaterialKey, V> registry, V defaultValue) {
            this.registry = registry;
            this.defaultValue = defaultValue;
        }

        public final void put(@NotNull HTMaterialKey materialKey, V value) {
            registry.put(materialKey, value);
        }

        @NotNull
        public final V get(@NotNull HTMaterialKey materialKey) {
            return registry.getOrDefault(materialKey, defaultValue);
        }
    }

    public static final class Register extends HTMaterialEvent {

        @NotNull
        public final ImmutableBiMap.Builder<HTMaterialKey, Integer> registry;

        private Register(@NotNull ImmutableBiMap.Builder<HTMaterialKey, Integer> registry) {
            this.registry = registry;
        }
    }

    public static final class Property extends NonNullMap<HTMaterialPropertyMap.Builder> {

        private Property(HTNonNullMap<HTMaterialKey, HTMaterialPropertyMap.Builder> registry) {
            super(registry);
        }
    }

    public static final class Flag extends NonNullMap<HTMaterialFlagSet.Builder> {

        private Flag(HTNonNullMap<HTMaterialKey, HTMaterialFlagSet.Builder> registry) {
            super(registry);
        }
    }

    public static final class Color extends SimpleMap<ColorConvertible> {

        private Color(Map<HTMaterialKey, ColorConvertible> registry) {
            super(registry, ColorConvertible.EMPTY);
        }
    }

    public static final class Formula extends SimpleMap<FormulaConvertible> {

        private Formula(Map<HTMaterialKey, FormulaConvertible> registry) {
            super(registry, FormulaConvertible.EMPTY);
        }
    }

    public static final class Molar extends SimpleMap<MolarMassConvertible> {

        private Molar(Map<HTMaterialKey, MolarMassConvertible> registry) {
            super(registry, MolarMassConvertible.EMPTY);
        }
    }

    // Init //

    private static final ImmutableBiMap.Builder<HTMaterialKey, Integer> materialKeys = ImmutableBiMap.builder();
    private static final HTNonNullMap<HTMaterialKey, HTMaterialPropertyMap.Builder> propertyMap = HTNonNullMap
            .create(key -> new HTMaterialPropertyMap.Builder());
    private static final HTNonNullMap<HTMaterialKey, HTMaterialFlagSet.Builder> flagMap = HTNonNullMap
            .create(key -> new HTMaterialFlagSet.Builder());
    private static final Map<HTMaterialKey, ColorConvertible> colorMap = new HashMap<>();
    private static final Map<HTMaterialKey, FormulaConvertible> formulaMap = new HashMap<>();
    private static final Map<HTMaterialKey, MolarMassConvertible> molarMap = new HashMap<>();

    public static void init() {
        MinecraftForge.EVENT_BUS.post(new Register(materialKeys));
        MinecraftForge.EVENT_BUS.post(new Property(propertyMap));
        MinecraftForge.EVENT_BUS.post(new Flag(flagMap));
        MinecraftForge.EVENT_BUS.post(new Color(colorMap));
        MinecraftForge.EVENT_BUS.post(new Formula(formulaMap));
        MinecraftForge.EVENT_BUS.post(new Molar(molarMap));
        createMaterials();
        HTMaterial.getRegistry().values().forEach(HTMaterial::verify);
    }

    private static void createMaterials() {
        ImmutableBiMap<HTMaterialKey, Integer> keyMap = materialKeys.build();
        keyMap.entrySet().stream().sorted(Comparator.comparingInt(Map.Entry::getValue)).forEach(entry -> {
            HTMaterialKey key = entry.getKey();
            HTMaterialPropertyMap property = propertyMap.getOrCreate(key).build();
            HTMaterialFlagSet flag = flagMap.getOrCreate(key).build();
            java.awt.Color color = getColor(key, property).asColor();
            String formula = getFormula(key, property).asFormula();
            double molar = getMolar(key, property).asMolar();
            HTMaterial.create(key, entry.getValue(), property, flag, color, formula, molar);
        });
    }

    @NotNull
    private static ColorConvertible getColor(HTMaterialKey key, HTMaterialPropertyMap map) {
        ColorConvertible color = null;
        for (HTMaterialProperty<?> property : map.values()) {
            if (property instanceof HTComponentPropertyBase<?>component) {
                color = component;
            }
        }
        ColorConvertible color1;
        if ((color1 = colorMap.get(key)) != null) {
            color = color1;
        }
        return color == null ? ColorConvertible.EMPTY : color;
    }

    @NotNull
    private static FormulaConvertible getFormula(HTMaterialKey key, HTMaterialPropertyMap map) {
        FormulaConvertible formula = null;
        for (HTMaterialProperty<?> property : map.values()) {
            if (property instanceof HTComponentPropertyBase<?>component) {
                formula = component;
            }
        }
        FormulaConvertible formula1;
        if ((formula1 = formulaMap.get(key)) != null) {
            formula = formula1;
        }
        return formula == null ? FormulaConvertible.EMPTY : formula;
    }

    @NotNull
    private static MolarMassConvertible getMolar(HTMaterialKey key, HTMaterialPropertyMap map) {
        MolarMassConvertible molar = null;
        for (HTMaterialProperty<?> property : map.values()) {
            if (property instanceof HTComponentPropertyBase<?>component) {
                molar = component;
            }
        }
        MolarMassConvertible molar1;
        if ((molar1 = molarMap.get(key)) != null) {
            molar = molar1;
        }
        return molar == null ? MolarMassConvertible.EMPTY : molar;
    }
}
