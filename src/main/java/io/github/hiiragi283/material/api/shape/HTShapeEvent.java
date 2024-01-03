package io.github.hiiragi283.material.api.shape;

import io.github.hiiragi283.material.api.registry.HTNonNullMap;
import io.github.hiiragi283.material.api.registry.HTObjectKeySet;
import io.github.hiiragi283.material.util.HTUtils;
import net.minecraftforge.fml.common.eventhandler.Event;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.function.BiConsumer;

public abstract class HTShapeEvent extends Event {

    static HTObjectKeySet<HTShapeKey> register() {
        return HTUtils.wrapAndPostEvent(HTObjectKeySet.create(), Register::new);
    }

    static HTNonNullMap<HTShapeKey, HTShapePredicate.Builder> predicate() {
        return HTUtils.postAndReturnEvent(new Predicate(new HashMap<>()));
    }

    public static final class Register extends HTShapeEvent implements HTObjectKeySet<HTShapeKey> {

        private final HTObjectKeySet<HTShapeKey> set;

        private Register(HTObjectKeySet<HTShapeKey> set) {
            this.set = set;
        }

        //    HTObjectKeySet    //

        @Override
        public void add(HTShapeKey key) {
            set.add(key);
        }

        @NotNull
        @Override
        public Iterator<HTShapeKey> iterator() {
            return set.iterator();
        }

    }

    public static final class Predicate extends HTShapeEvent implements HTNonNullMap<HTShapeKey, HTShapePredicate.Builder> {

        private final Map<HTShapeKey, HTShapePredicate.Builder> map;

        private Predicate(Map<HTShapeKey, HTShapePredicate.Builder> map) {
            this.map = map;
        }

        //    HTNonNullMap    //

        @NotNull
        @Override
        public HTShapePredicate.Builder getOrCreate(HTShapeKey key) {
            return map.computeIfAbsent(key, key1 -> new HTShapePredicate.Builder());
        }

        @Override
        public void forEach(BiConsumer<HTShapeKey, HTShapePredicate.Builder> biConsumer) {
            map.forEach(biConsumer);
        }

    }

}