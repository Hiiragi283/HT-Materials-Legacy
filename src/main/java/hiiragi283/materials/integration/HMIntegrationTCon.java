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

public enum HMIntegrationTCon implements HTMaterialsAddon {
  INSTANCE;

  public static final HTMaterialKey ARDITE = HTMaterialKey.getOrCreate(INSTANCE.getId("ardite"));

  public static final HTMaterialKey MANYULLYN =
      HTMaterialKey.getOrCreate(INSTANCE.getId("manyullyn"));

  public static final HTMaterialKey KNIGHTSLIME =
      HTMaterialKey.getOrCreate(INSTANCE.getId("knightslime"));

  public static final HTMaterialKey PIGIRON = HTMaterialKey.getOrCreate(INSTANCE.getId("pigiron"));

  public static final HTMaterialKey ALUBRASS =
      HTMaterialKey.getOrCreate(INSTANCE.getId("alubrass"));

  public static final HTMaterialGroup TCON_METAL =
      new HTMaterialGroup(
          DefaultParts.DUST, DefaultParts.GEAR, DefaultParts.PLATE, DefaultParts.ROD);

  @Override
  public int getPriority() {
    return 0;
  }

  @Override
  public String getModId() {
    return "tconstruct";
  }

  @Override
  public void registerMaterial(BiConsumer<HTMaterialKey, Integer> register) {
    register.accept(ARDITE, 20200);
    register.accept(MANYULLYN, 20201);
    register.accept(KNIGHTSLIME, 20202);
    register.accept(PIGIRON, 20203);
    register.accept(ALUBRASS, 20204);
  }

  @Override
  public void setupProperties(Function<HTMaterialKey, HTPropertyHolderBuilder> register) {
    register
        .apply(ARDITE)
        .put(DefaultMaterialProperties.COLOR, new Color(0xc64524))
        .put(DefaultMaterialProperties.GROUP, TCON_METAL);

    register
        .apply(MANYULLYN)
        .put(DefaultMaterialProperties.COLOR, new Color(0x8C31C8))
        .put(DefaultMaterialProperties.GROUP, TCON_METAL);

    register
        .apply(KNIGHTSLIME)
        .put(DefaultMaterialProperties.COLOR, new Color(0xB160EA))
        .put(DefaultMaterialProperties.GROUP, TCON_METAL);

    register
        .apply(PIGIRON)
        .put(DefaultMaterialProperties.COLOR, new Color(0xF19A9F))
        .put(DefaultMaterialProperties.GROUP, TCON_METAL);

    register
        .apply(ALUBRASS)
        .put(DefaultMaterialProperties.COLOR, new Color(0xEDCE50))
        .put(DefaultMaterialProperties.GROUP, TCON_METAL);
  }
}
