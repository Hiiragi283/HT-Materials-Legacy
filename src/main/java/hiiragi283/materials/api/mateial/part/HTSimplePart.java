package hiiragi283.materials.api.mateial.part;

import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.TextComponentTranslation;

import org.jetbrains.annotations.NotNull;

import com.google.common.base.CaseFormat;

import hiiragi283.materials.api.mateial.HTMaterialKey;
import hiiragi283.materials.api.mateial.item.HTMaterialItem;
import hiiragi283.materials.api.mateial.item.ItemPartMaterial;

public class HTSimplePart extends HTPart {

  private final @NotNull String oreDictPrefix;
  public final @NotNull String translationKey;

  protected HTSimplePart(@NotNull String name) {
    super(name);
    this.oreDictPrefix = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, name);
    this.translationKey = "part.ht_materials." + name;
  }

  @Override
  public @NotNull HTMaterialItem createItem() {
    return new ItemPartMaterial(this);
  }

  @Override
  public @NotNull String createOreDict(@NotNull HTMaterialKey materialKey) {
    return oreDictPrefix + materialKey.oreDictName;
  }

  @Override
  public @NotNull String createTranslatedName(@NotNull HTMaterialKey materialKey) {
    return I18n.format(translationKey, materialKey.getTranslatedName());
  }

  @Override
  public @NotNull TextComponentTranslation createTranslatedText(
      @NotNull HTMaterialKey materialKey) {
    return new TextComponentTranslation(translationKey, materialKey.getTranslatedText());
  }
}
