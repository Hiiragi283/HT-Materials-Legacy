package io.github.hiiragi283.material.api.material;

import io.github.hiiragi283.material.api.material.flag.HTMaterialFlagSet;
import io.github.hiiragi283.material.api.material.property.HTMaterialPropertyMap;
import io.github.hiiragi283.material.api.registry.HTNonNullMap;
import io.github.hiiragi283.material.api.registry.HTObjectKeySet;
import io.github.hiiragi283.material.util.HTUtils;
import net.minecraftforge.fml.common.eventhandler.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.function.BiConsumer;

public abstract class HTMaterialEvent extends Event {

    static HTObjectKeySet<HTMaterialKey> register() {
        return HTUtils.wrapAndPostEvent(HTObjectKeySet.create(), Register::new);
    }

    static HTNonNullMap<HTMaterialKey, HTMaterialPropertyMap.Builder> property() {
        return HTUtils.postAndReturnEvent(new Property(new HashMap<>()));
    }

    static HTNonNullMap<HTMaterialKey, HTMaterialFlagSet.Builder> flag() {
        return HTUtils.postAndReturnEvent(new Flag(new HashMap<>()));
    }

    static Map<HTMaterialKey, ColorConvertible> color() {
        return HTUtils.wrapAndPostEvent(new HashMap<>(), Color::new);
    }

    static Map<HTMaterialKey, FormulaConvertible> formula() {
        return HTUtils.wrapAndPostEvent(new HashMap<>(), Formula::new);
    }

    static Map<HTMaterialKey, MolarMassConvertible> molar() {
        return HTUtils.wrapAndPostEvent(new HashMap<>(), Molar::new);
    }

    public static final class Register extends HTMaterialEvent implements HTObjectKeySet<HTMaterialKey> {

        private final HTObjectKeySet<HTMaterialKey> set;

        private Register(HTObjectKeySet<HTMaterialKey> set) {
            this.set = set;
        }

        //    HTObjectKeySet    //

        @Override
        public void add(HTMaterialKey key) {
            set.add(key);
        }

        @NotNull
        @Override
        public Iterator<HTMaterialKey> iterator() {
            return set.iterator();
        }

    }

    public static final class Property extends HTMaterialEvent implements HTNonNullMap<HTMaterialKey, HTMaterialPropertyMap.Builder> {

        private final Map<HTMaterialKey, HTMaterialPropertyMap.Builder> map;

        private Property(Map<HTMaterialKey, HTMaterialPropertyMap.Builder> map) {
            this.map = map;
        }

        //    HTNonNullMap    //

        @NotNull
        @Override
        public HTMaterialPropertyMap.Builder getOrCreate(HTMaterialKey key) {
            return map.computeIfAbsent(key, key1 -> new HTMaterialPropertyMap.Builder());
        }

        @Override
        public void forEach(BiConsumer<HTMaterialKey, HTMaterialPropertyMap.Builder> biConsumer) {
            map.forEach(biConsumer);
        }

    }

    public static final class Flag extends HTMaterialEvent implements HTNonNullMap<HTMaterialKey, HTMaterialFlagSet.Builder> {

        private final Map<HTMaterialKey, HTMaterialFlagSet.Builder> map;

        private Flag(Map<HTMaterialKey, HTMaterialFlagSet.Builder> map) {
            this.map = map;
        }

        //    HTNonNullMap    //

        @NotNull
        @Override
        public HTMaterialFlagSet.Builder getOrCreate(HTMaterialKey key) {
            return map.computeIfAbsent(key, key1 -> new HTMaterialFlagSet.Builder());
        }

        @Override
        public void forEach(BiConsumer<HTMaterialKey, HTMaterialFlagSet.Builder> biConsumer) {
            map.forEach(biConsumer);
        }

    }

    public static final class Color extends HTMaterialEvent {

        private final Map<HTMaterialKey, ColorConvertible> map;

        private Color(Map<HTMaterialKey, ColorConvertible> map) {
            this.map = map;
        }

        @Nullable
        public ColorConvertible put(HTMaterialKey materialKey, ColorConvertible color) {
            return map.put(materialKey, color);
        }

        @NotNull
        public ColorConvertible get(HTMaterialKey materialKey) {
            return map.getOrDefault(materialKey, ColorConvertible.EMPTY);
        }

    }

    public static final class Formula extends HTMaterialEvent {

        private final Map<HTMaterialKey, FormulaConvertible> map;

        private Formula(Map<HTMaterialKey, FormulaConvertible> map) {
            this.map = map;
        }

        @Nullable
        public FormulaConvertible put(HTMaterialKey materialKey, FormulaConvertible color) {
            return map.put(materialKey, color);
        }

        @NotNull
        public FormulaConvertible get(HTMaterialKey materialKey) {
            return map.getOrDefault(materialKey, FormulaConvertible.EMPTY);
        }

    }

    public static final class Molar extends HTMaterialEvent {

        private final Map<HTMaterialKey, MolarMassConvertible> map;

        private Molar(Map<HTMaterialKey, MolarMassConvertible> map) {
            this.map = map;
        }

        @Nullable
        public MolarMassConvertible put(HTMaterialKey materialKey, MolarMassConvertible color) {
            return map.put(materialKey, color);
        }

        @NotNull
        public MolarMassConvertible get(HTMaterialKey materialKey) {
            return map.getOrDefault(materialKey, MolarMassConvertible.EMPTY);
        }

    }

}