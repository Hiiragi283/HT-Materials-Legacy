package hiiragi283.materials.common;

import java.awt.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

import net.minecraft.item.EnumRarity;

import hiiragi283.materials.api.HMReferences;
import hiiragi283.materials.api.HTMaterialsAddon;
import hiiragi283.materials.api.mateial.DefaultMaterialProperties;
import hiiragi283.materials.api.mateial.DefaultMaterials;
import hiiragi283.materials.api.mateial.HTMaterialKey;
import hiiragi283.materials.api.mateial.part.DefaultParts;
import hiiragi283.materials.api.mateial.part.HTPart;
import hiiragi283.materials.api.mateial.property.HTMaterialGroup;
import hiiragi283.materials.api.property.HTPropertyHolderBuilder;

enum HMIntegrationCommon implements HTMaterialsAddon {
  INSTANCE;

  @Override
  public int getPriority() {
    return -100;
  }

  @Override
  public String getModId() {
    return HMReferences.MOD_ID;
  }

  @Override
  public void registerPart(Consumer<HTPart> register) {
    register.accept(DefaultParts.DUST);
    register.accept(DefaultParts.GEAR);
    register.accept(DefaultParts.INGOT);
    register.accept(DefaultParts.NUGGET);
    register.accept(DefaultParts.PLATE);
    register.accept(DefaultParts.ROD);
  }

  @Override
  public void registerMaterial(BiConsumer<HTMaterialKey, Integer> register) {
    // Periodic Table
    register.accept(DefaultMaterials.CARBON, 6);

    register.accept(DefaultMaterials.ALUMINUM, 13);
    register.accept(DefaultMaterials.SILICON, 14);
    register.accept(DefaultMaterials.SULFUR, 16);

    register.accept(DefaultMaterials.IRON, 26);
    register.accept(DefaultMaterials.COBALT, 27);
    register.accept(DefaultMaterials.NICKEL, 28);
    register.accept(DefaultMaterials.COPPER, 29);
    register.accept(DefaultMaterials.ZINC, 30);

    register.accept(DefaultMaterials.SILVER, 47);
    register.accept(DefaultMaterials.TIN, 50);

    register.accept(DefaultMaterials.OSMIUM, 76);
    register.accept(DefaultMaterials.IRIDIUM, 77);
    register.accept(DefaultMaterials.PLATINUM, 78);
    register.accept(DefaultMaterials.GOLD, 79);
    register.accept(DefaultMaterials.LEAD, 82);

    // Carbon
    register.accept(DefaultMaterials.COAL, 600);
    register.accept(DefaultMaterials.CHARCOAL, 601);
    // Silicon
    register.accept(DefaultMaterials.QUARTZ, 1400);
    register.accept(DefaultMaterials.GLASS, 1401);
    // Iron
    register.accept(DefaultMaterials.STEEL, 2600);
    // Copper
    register.accept(DefaultMaterials.BRASS, 2900);
    register.accept(DefaultMaterials.BRONZE, 2901);
    // Gold
    register.accept(DefaultMaterials.ELECTRUM, 7900);
  }

  @Override
  public void setupProperties(Function<HTMaterialKey, HTPropertyHolderBuilder> register) {
    // Periodic Table
    register
        .apply(DefaultMaterials.CARBON)
        .put(DefaultMaterialProperties.COLOR, new Color(0x131313))
        .put(DefaultMaterialProperties.GROUP, HTMaterialGroup.DUST);

    register
        .apply(DefaultMaterials.ALUMINUM)
        .put(DefaultMaterialProperties.COLOR, new Color(0xc7cfdd))
        .put(DefaultMaterialProperties.GROUP, HTMaterialGroup.METAL);

    register
        .apply(DefaultMaterials.SILICON)
        .put(DefaultMaterialProperties.COLOR, new Color(0x1a1932))
        .put(DefaultMaterialProperties.GROUP, HTMaterialGroup.METAL);

    register
        .apply(DefaultMaterials.SULFUR)
        .put(DefaultMaterialProperties.COLOR, new Color(0xffff3f))
        .put(DefaultMaterialProperties.GROUP, HTMaterialGroup.DUST);

    register
        .apply(DefaultMaterials.IRON)
        .put(DefaultMaterialProperties.COLOR, new Color(0xb4b4b4))
        .put(DefaultMaterialProperties.GROUP, HTMaterialGroup.METAL_VANILLA);

    register
        .apply(DefaultMaterials.COBALT)
        .put(DefaultMaterialProperties.COLOR, new Color(0x0955a8))
        .put(DefaultMaterialProperties.GROUP, HTMaterialGroup.METAL);

    register
        .apply(DefaultMaterials.NICKEL)
        .put(DefaultMaterialProperties.COLOR, new Color(0xf9e6cf))
        .put(DefaultMaterialProperties.GROUP, HTMaterialGroup.METAL);

    register
        .apply(DefaultMaterials.COPPER)
        .put(DefaultMaterialProperties.COLOR, new Color(0xc15a36))
        .put(DefaultMaterialProperties.GROUP, HTMaterialGroup.METAL);

    register
        .apply(DefaultMaterials.ZINC)
        .put(DefaultMaterialProperties.COLOR, new Color(0xd0ddbb))
        .put(DefaultMaterialProperties.GROUP, HTMaterialGroup.METAL);

    register
        .apply(DefaultMaterials.SILVER)
        .put(DefaultMaterialProperties.COLOR, new Color(0xc4d2d5))
        .put(DefaultMaterialProperties.GROUP, HTMaterialGroup.METAL);

    register
        .apply(DefaultMaterials.TIN)
        .put(DefaultMaterialProperties.GROUP, HTMaterialGroup.METAL);

    register
        .apply(DefaultMaterials.OSMIUM)
        .put(DefaultMaterialProperties.COLOR, new Color(0x92a1b9))
        .put(DefaultMaterialProperties.GROUP, HTMaterialGroup.METAL)
        .put(DefaultMaterialProperties.RARITY, EnumRarity.RARE);

    register
        .apply(DefaultMaterials.IRIDIUM)
        .put(DefaultMaterialProperties.COLOR, new Color(0xc5cae9))
        .put(DefaultMaterialProperties.GROUP, HTMaterialGroup.METAL)
        .put(DefaultMaterialProperties.RARITY, EnumRarity.RARE);

    register
        .apply(DefaultMaterials.PLATINUM)
        .put(DefaultMaterialProperties.COLOR, new Color(0x94fdff))
        .put(DefaultMaterialProperties.GROUP, HTMaterialGroup.METAL)
        .put(DefaultMaterialProperties.RARITY, EnumRarity.RARE);

    register
        .apply(DefaultMaterials.GOLD)
        .put(DefaultMaterialProperties.COLOR, new Color(0xffff00))
        .put(DefaultMaterialProperties.GROUP, HTMaterialGroup.METAL_VANILLA);

    register
        .apply(DefaultMaterials.LEAD)
        .put(DefaultMaterialProperties.COLOR, new Color(0x424c6e))
        .put(DefaultMaterialProperties.GROUP, HTMaterialGroup.METAL);

    // Carbon
    register
        .apply(DefaultMaterials.COAL)
        .put(DefaultMaterialProperties.BURN_TIME, 1600)
        .put(DefaultMaterialProperties.COLOR, new Color(0x1b1b1b))
        .put(DefaultMaterialProperties.GROUP, HTMaterialGroup.DUST);

    register
        .apply(DefaultMaterials.CHARCOAL)
        .put(DefaultMaterialProperties.BURN_TIME, 1600)
        .put(DefaultMaterialProperties.COLOR, new Color(0x1f1d1a))
        .put(DefaultMaterialProperties.GROUP, HTMaterialGroup.DUST);
    // Silicon
    register
        .apply(DefaultMaterials.QUARTZ)
        .put(DefaultMaterialProperties.COLOR, new Color(0xbab0aa))
        .put(DefaultMaterialProperties.GROUP, HTMaterialGroup.DUST);

    register
        .apply(DefaultMaterials.GLASS)
        .put(DefaultMaterialProperties.GROUP, HTMaterialGroup.DUST);
    // Iron
    register
        .apply(DefaultMaterials.STEEL)
        .put(DefaultMaterialProperties.COLOR, new Color(0x5d5d5d))
        .put(DefaultMaterialProperties.GROUP, HTMaterialGroup.METAL);

    // Copper
    register
        .apply(DefaultMaterials.BRASS)
        .put(DefaultMaterialProperties.COLOR, new Color(0xffa214))
        .put(DefaultMaterialProperties.GROUP, HTMaterialGroup.METAL);

    register
        .apply(DefaultMaterials.BRONZE)
        .put(DefaultMaterialProperties.COLOR, new Color(0xff5000))
        .put(DefaultMaterialProperties.GROUP, HTMaterialGroup.METAL);
    // Gold
    register
        .apply(DefaultMaterials.ELECTRUM)
        .put(DefaultMaterialProperties.COLOR, new Color(0xffeb57))
        .put(DefaultMaterialProperties.GROUP, HTMaterialGroup.METAL);
  }
}
