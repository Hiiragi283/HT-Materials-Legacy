package io.github.hiiragi283.material.api.shape;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

import com.github.bsideup.jabel.Desugar;

import io.github.hiiragi283.material.api.material.HTMaterial;
import io.github.hiiragi283.material.api.material.HTMaterialKey;
import io.github.hiiragi283.material.api.material.flag.HTMaterialFlag;
import io.github.hiiragi283.material.api.material.property.HTPropertyKey;

@Desugar
public record HTShapePredicate(
                               boolean disabled,
                               Collection<HTMaterialKey> blackList,
                               Collection<HTMaterialKey> whiteList,
                               Collection<HTMaterialFlag> requiredFlags,
                               Collection<HTPropertyKey<?>> requiredProperties)
        implements Predicate<HTMaterial> {

    @Override
    public boolean test(HTMaterial material) {
        var key = material.key();
        if (disabled) return false;
        else if (blackList.contains(key)) return false;
        else if (whiteList.contains(key)) return true;
        else
            return requiredFlags.stream().allMatch(material::hasFlag) &&
                    requiredProperties.stream().allMatch(material::hasProperty);
    }

    public static final class Builder {

        private final Set<HTMaterialKey> blackList = new HashSet<>();

        public Builder addBlackList(HTMaterialKey materialKey) {
            blackList.add(materialKey);
            return this;
        }

        public Builder removeBlackList(HTMaterialKey materialKey) {
            blackList.remove(materialKey);
            return this;
        }

        private final Set<HTMaterialKey> whiteList = new HashSet<>();

        public Builder addWhiteList(HTMaterialKey materialKey) {
            whiteList.add(materialKey);
            return this;
        }

        public Builder removeWhiteList(HTMaterialKey materialKey) {
            whiteList.remove(materialKey);
            return this;
        }

        private final Set<HTMaterialFlag> requiredFlags = new HashSet<>();

        public Builder addRequiredFlag(HTMaterialFlag flag) {
            requiredFlags.add(flag);
            return this;
        }

        public Builder removeRequiredFlag(HTMaterialFlag flag) {
            requiredFlags.remove(flag);
            return this;
        }

        private final Set<HTPropertyKey<?>> requiredProperties = new HashSet<>();

        public Builder addRequiredProperty(HTPropertyKey<?> key) {
            requiredProperties.add(key);
            return this;
        }

        public Builder removeRequiredProperty(HTPropertyKey<?> key) {
            requiredProperties.remove(key);
            return this;
        }

        public boolean disabled = true;

        public Builder setDisabled() {
            disabled = true;
            return this;
        }

        public Builder setEnabled() {
            disabled = false;
            return this;
        }

        public HTShapePredicate build() {
            return new HTShapePredicate(disabled, blackList, whiteList, requiredFlags, requiredProperties);
        }
    }
}
