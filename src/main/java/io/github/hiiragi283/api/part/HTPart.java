package io.github.hiiragi283.api.part;

import org.jetbrains.annotations.NotNull;

import com.github.bsideup.jabel.Desugar;

import io.github.hiiragi283.api.material.HTMaterial;
import io.github.hiiragi283.api.material.HTMaterialKey;
import io.github.hiiragi283.api.shape.HTShape;
import io.github.hiiragi283.api.shape.HTShapeKey;

@Desugar
public record HTPart(HTShapeKey shapeKey, HTMaterialKey materialKey) {

    public HTPart(HTShape shape, HTMaterial material) {
        this(shape.key(), material.key());
    }

    @NotNull
    public HTShape getShape() throws NullPointerException {
        return shapeKey.getShape();
    }

    @NotNull
    public HTMaterial getMaterial() throws NullPointerException {
        return materialKey.getMaterial();
    }
}
