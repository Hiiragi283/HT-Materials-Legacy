package io.github.hiiragi283.material.api.part;

import com.github.bsideup.jabel.Desugar;
import io.github.hiiragi283.material.api.material.HTMaterial;
import io.github.hiiragi283.material.api.material.HTMaterialKey;
import io.github.hiiragi283.material.api.shape.HTShape;
import io.github.hiiragi283.material.api.shape.HTShapeKey;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Desugar
public record HTPart(HTMaterialKey materialKey, HTShapeKey shapeKey) {

    @NotNull
    public HTMaterial getMaterial() {
        return HTMaterial.getMaterial(materialKey);
    }

    @Nullable
    public HTMaterial getMaterialOrNull() {
        return HTMaterial.getMaterialOrNull(materialKey);
    }

    @NotNull
    public HTShape getShape() {
        return HTShape.getShape(shapeKey);
    }

    @Nullable
    public HTShape getShapeOrNull() {
        return HTShape.getShapeOrNull(shapeKey);
    }

}