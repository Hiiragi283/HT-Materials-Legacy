package io.github.hiiragi283.htms.api.part;

import java.util.Objects;

import org.jetbrains.annotations.NotNull;

import com.github.bsideup.jabel.Desugar;

import io.github.hiiragi283.htms.api.material.HTMaterial;
import io.github.hiiragi283.htms.api.material.HTMaterialKey;
import io.github.hiiragi283.htms.api.shape.HTShape;

@Desugar
public record HTPart(@NotNull HTShape shape, @NotNull HTMaterialKey materialKey) {

    public HTPart(@NotNull HTShape shape, @NotNull HTMaterial material) {
        this(shape, material.key());
    }

    public boolean isMaterialEquals(HTMaterialKey materialKey) {
        return Objects.equals(this.materialKey, materialKey);
    }

    public boolean isShapeEquals(HTShape shape) {
        return Objects.equals(this.shape, shape);
    }
}
