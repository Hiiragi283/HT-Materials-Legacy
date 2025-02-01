package hiiragi283.materials.api.mateial;

import hiiragi283.materials.api.HTMaterialsAPI;
import hiiragi283.materials.api.mateial.property.HTMaterialModelFunction;
import hiiragi283.materials.api.property.HTPropertyKey;

import java.awt.*;

public final class DefaultMaterialProperties {
    private DefaultMaterialProperties() {
    }

    public static final HTPropertyKey<Color> COLOR = new HTPropertyKey<>(HTMaterialsAPI.id("color"), Color.class);

    public static final HTPropertyKey<HTMaterialModelFunction> MODEL = new HTPropertyKey<>(HTMaterialsAPI.id("model"), HTMaterialModelFunction.class);
}
