package hiiragi283.materials.api.mateial;

import java.awt.*;

import net.minecraftforge.common.IRarity;

import hiiragi283.materials.api.HTMaterialsAPI;
import hiiragi283.materials.api.mateial.property.HTMaterialGroup;
import hiiragi283.materials.api.mateial.property.HTMaterialModelFunction;
import hiiragi283.materials.api.property.HTPropertyKey;

public final class DefaultMaterialProperties {
  private DefaultMaterialProperties() {}

  public static final HTPropertyKey<Integer> BURN_TIME =
      new HTPropertyKey<>(HTMaterialsAPI.id("burn_time"), Integer.class);

  public static final HTPropertyKey<Color> COLOR =
      new HTPropertyKey<>(HTMaterialsAPI.id("color"), Color.class);

  public static final HTPropertyKey<HTMaterialGroup> GROUP =
      new HTPropertyKey<>(HTMaterialsAPI.id("group"), HTMaterialGroup.class);

  public static final HTPropertyKey<HTMaterialModelFunction> MODEL =
      new HTPropertyKey<>(HTMaterialsAPI.id("model"), HTMaterialModelFunction.class);

  public static final HTPropertyKey<IRarity> RARITY =
      new HTPropertyKey<>(HTMaterialsAPI.id("rarity"), IRarity.class);
}
