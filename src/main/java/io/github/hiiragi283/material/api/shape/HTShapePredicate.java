package io.github.hiiragi283.material.api.shape;

import com.github.bsideup.jabel.Desugar;
import io.github.hiiragi283.material.api.material.HTMaterial;
import io.github.hiiragi283.material.api.material.HTMaterialKey;
import io.github.hiiragi283.material.api.material.flag.HTMaterialFlag;
import io.github.hiiragi283.material.api.material.property.HTPropertyKey;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

@Desugar
public record HTShapePredicate(
        boolean disabled,
        Collection<HTMaterialKey> blackList,
        Collection<HTMaterialKey> whiteList,
        Collection<HTMaterialFlag> requiredFlags,
        Collection<HTPropertyKey<?>> requiredProperties
) implements Predicate<HTMaterial> {

    @Override
    public boolean test(HTMaterial material) {
        var key = material.getKey();
        if (disabled) return false;
        else if (blackList.contains(key)) return false;
        else if (whiteList.contains(key)) return true;
        else
            return requiredFlags.stream().allMatch(material::hasFlag) && requiredProperties.stream().allMatch(material::hasProperty);
    }

    public static class Builder {

        public final Set<HTMaterialKey> blackList = new HashSet<>();

        public final Set<HTMaterialKey> whiteList = new HashSet<>();

        public final Set<HTMaterialFlag> requiredFlags = new HashSet<>();

        public final Set<HTPropertyKey<?>> requiredProperties = new HashSet<>();

        public boolean disabled = true;

        public HTShapePredicate build() {
            return new HTShapePredicate(disabled, blackList, whiteList, requiredFlags, requiredProperties);
        }

    }

}