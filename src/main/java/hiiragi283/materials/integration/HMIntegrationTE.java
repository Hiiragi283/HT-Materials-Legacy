package hiiragi283.materials.integration;

import java.awt.*;
import java.util.function.BiConsumer;
import java.util.function.Function;

import net.minecraft.item.EnumRarity;

import hiiragi283.materials.api.HTMaterialsAddon;
import hiiragi283.materials.api.mateial.DefaultMaterialProperties;
import hiiragi283.materials.api.mateial.HTMaterialKey;
import hiiragi283.materials.api.mateial.part.DefaultParts;
import hiiragi283.materials.api.mateial.property.HTMaterialGroup;
import hiiragi283.materials.api.property.HTPropertyHolderBuilder;

public enum HMIntegrationTE implements HTMaterialsAddon {
  INSTANCE;

  public static final HTMaterialKey SIGNALUM =
      HTMaterialKey.getOrCreate(INSTANCE.getId("signalum"));

  public static final HTMaterialKey LUMIUM = HTMaterialKey.getOrCreate(INSTANCE.getId("lumium"));

  public static final HTMaterialKey ENDERIUM =
      HTMaterialKey.getOrCreate(INSTANCE.getId("enderium"));

  public static final HTMaterialGroup TE_METAL = new HTMaterialGroup(DefaultParts.ROD);

  @Override
  public int getPriority() {
    return 0;
  }

  @Override
  public String getModId() {
    return "thermalfoundation";
  }

  @Override
  public void registerMaterial(BiConsumer<HTMaterialKey, Integer> register) {
    register.accept(SIGNALUM, 20000);
    register.accept(LUMIUM, 20001);
    register.accept(ENDERIUM, 20002);
  }

  @Override
  public void setupProperties(Function<HTMaterialKey, HTPropertyHolderBuilder> register) {
    register
        .apply(SIGNALUM)
        .put(DefaultMaterialProperties.COLOR, new Color(0xc64524))
        .put(DefaultMaterialProperties.GROUP, TE_METAL)
        .put(DefaultMaterialProperties.RARITY, EnumRarity.UNCOMMON);

    register
        .apply(LUMIUM)
        .put(DefaultMaterialProperties.COLOR, new Color(0xffeb57))
        .put(DefaultMaterialProperties.GROUP, TE_METAL)
        .put(DefaultMaterialProperties.RARITY, EnumRarity.UNCOMMON);

    register
        .apply(ENDERIUM)
        .put(DefaultMaterialProperties.COLOR, new Color(0x134c4c))
        .put(DefaultMaterialProperties.GROUP, TE_METAL)
        .put(DefaultMaterialProperties.RARITY, EnumRarity.RARE);
  }
}
