package hiiragi283.materials.integration;

import java.awt.*;
import java.util.function.BiConsumer;
import java.util.function.Function;

import hiiragi283.materials.api.HTMaterialsAddon;
import hiiragi283.materials.api.mateial.DefaultMaterialProperties;
import hiiragi283.materials.api.mateial.HTMaterialKey;
import hiiragi283.materials.api.mateial.part.DefaultParts;
import hiiragi283.materials.api.mateial.property.HTMaterialGroup;
import hiiragi283.materials.api.property.HTPropertyHolderBuilder;

public enum HMIntegrationEIO implements HTMaterialsAddon {
  INSTANCE;

  public static final HTMaterialKey ELECTRICAL_STEEL =
      HTMaterialKey.getOrCreate(INSTANCE.getId("electrical_steel"));

  public static final HTMaterialKey ENERGETIC_ALLOY =
      HTMaterialKey.getOrCreate(INSTANCE.getId("energetic_alloy"));

  public static final HTMaterialKey VIBRANT_ALLOY =
      HTMaterialKey.getOrCreate(INSTANCE.getId("vibrant_alloy"));

  public static final HTMaterialKey REDSTONE_ALLOY =
      HTMaterialKey.getOrCreate(INSTANCE.getId("redstone_alloy"));

  public static final HTMaterialKey CONDUCTIVE_IRON =
      HTMaterialKey.getOrCreate(INSTANCE.getId("conductive_iron"));

  public static final HTMaterialKey PULSATING_IRON =
      HTMaterialKey.getOrCreate(INSTANCE.getId("pulsating_iron"));

  public static final HTMaterialKey DARK_STEEL =
      HTMaterialKey.getOrCreate(INSTANCE.getId("dark_steel"));

  public static final HTMaterialKey SOULARIUM =
      HTMaterialKey.getOrCreate(INSTANCE.getId("soularium"));

  public static final HTMaterialKey END_STEEL =
      HTMaterialKey.getOrCreate(INSTANCE.getId("end_steel"));

  public static final HTMaterialGroup ENDER_IO_METAL =
      new HTMaterialGroup(
          DefaultParts.DUST, DefaultParts.GEAR, DefaultParts.PLATE, DefaultParts.ROD);

  @Override
  public int getPriority() {
    return 0;
  }

  @Override
  public String getModId() {
    return "enderio";
  }

  @Override
  public void registerMaterial(BiConsumer<HTMaterialKey, Integer> register) {
    register.accept(ELECTRICAL_STEEL, 20100);
    register.accept(ENERGETIC_ALLOY, 20101);
    register.accept(VIBRANT_ALLOY, 20102);
    register.accept(REDSTONE_ALLOY, 20103);
    register.accept(CONDUCTIVE_IRON, 20104);
    register.accept(PULSATING_IRON, 20105);
    register.accept(DARK_STEEL, 20106);
    register.accept(SOULARIUM, 20107);
    register.accept(END_STEEL, 20108);
  }

  @Override
  public void setupProperties(Function<HTMaterialKey, HTPropertyHolderBuilder> register) {
    register
        .apply(ELECTRICAL_STEEL)
        .put(DefaultMaterialProperties.COLOR, new Color(0xb4b4b4))
        .put(DefaultMaterialProperties.GROUP, ENDER_IO_METAL);

    register
        .apply(ENERGETIC_ALLOY)
        .put(DefaultMaterialProperties.COLOR, new Color(0xed7614))
        .put(DefaultMaterialProperties.GROUP, ENDER_IO_METAL);

    register
        .apply(VIBRANT_ALLOY)
        .put(DefaultMaterialProperties.COLOR, new Color(0x99e65f))
        .put(DefaultMaterialProperties.GROUP, ENDER_IO_METAL);

    register
        .apply(REDSTONE_ALLOY)
        .put(DefaultMaterialProperties.COLOR, new Color(0xc42430))
        .put(DefaultMaterialProperties.GROUP, ENDER_IO_METAL);

    register
        .apply(CONDUCTIVE_IRON)
        .put(DefaultMaterialProperties.COLOR, new Color(0xf68187))
        .put(DefaultMaterialProperties.GROUP, ENDER_IO_METAL);

    register
        .apply(PULSATING_IRON)
        .put(DefaultMaterialProperties.COLOR, new Color(0x99ff9b))
        .put(DefaultMaterialProperties.GROUP, ENDER_IO_METAL);

    register
        .apply(DARK_STEEL)
        .put(DefaultMaterialProperties.COLOR, new Color(0x272727))
        .put(DefaultMaterialProperties.GROUP, ENDER_IO_METAL);

    register
        .apply(SOULARIUM)
        .put(DefaultMaterialProperties.COLOR, new Color(0x372d2d))
        .put(DefaultMaterialProperties.GROUP, ENDER_IO_METAL);

    register
        .apply(END_STEEL)
        .put(DefaultMaterialProperties.COLOR, new Color(0xf1ff9f))
        .put(DefaultMaterialProperties.GROUP, ENDER_IO_METAL);
  }
}
