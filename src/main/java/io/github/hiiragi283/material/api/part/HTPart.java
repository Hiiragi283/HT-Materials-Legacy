package io.github.hiiragi283.material.api.part;

import com.github.bsideup.jabel.Desugar;
import com.google.common.base.CaseFormat;
import io.github.hiiragi283.material.util.HTUtils;
import io.github.hiiragi283.material.api.material.HTMaterial;
import io.github.hiiragi283.material.api.material.HTMaterialKey;
import io.github.hiiragi283.material.api.shape.HTShape;
import io.github.hiiragi283.material.api.shape.HTShapeKey;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

@Desugar
public record HTPart(HTMaterialKey materialKey, HTShapeKey shapeKey) {

    @Nullable
    public static HTPart of(String oreDict) {
        List<String> split = Arrays.asList(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, oreDict).split("_"));
        for (int i = 0; i < split.size(); i++) {
            String shapeName = HTUtils.joinToString("_", split.subList(0, i + 1).stream());
            HTShape shape = HTShape.getShapeOrNull(shapeName);
            if (shape == null) continue;
            String materialName = HTUtils.joinToString("_", split.subList(i + 1, split.size()).stream());
            HTMaterial material = HTMaterial.getMaterialOrNull(materialName);
            if (material == null) continue;
            return new HTPart(material.getKey(), shape.key());
        }
        return null;
    }

    @NotNull
    public HTMaterial getMaterial() {
        return HTMaterial.getMaterial(materialKey.name());
    }

    @Nullable
    public HTMaterial getMaterialOrNull() {
        return HTMaterial.getMaterialOrNull(materialKey.name());
    }

    @NotNull
    public HTShape getShape() {
        return HTShape.getShape(shapeKey.name());
    }

    @Nullable
    public HTShape getShapeOrNull() {
        return HTShape.getShapeOrNull(shapeKey.name());
    }

}