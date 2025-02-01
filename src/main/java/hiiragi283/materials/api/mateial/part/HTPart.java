package hiiragi283.materials.api.mateial.part;

import net.minecraft.util.text.TextComponentTranslation;

import org.jetbrains.annotations.NotNull;

import hiiragi283.materials.api.mateial.HTMaterialKey;
import hiiragi283.materials.api.mateial.item.HTMaterialItem;

public abstract class HTPart {

  public final @NotNull String name;

  protected HTPart(@NotNull String name) {
    this.name = name;
  }

  public abstract @NotNull HTMaterialItem createItem();

  public abstract @NotNull String createOreDict(@NotNull final HTMaterialKey materialKey);

  public abstract @NotNull String createTranslatedName(@NotNull final HTMaterialKey materialKey);

  public abstract @NotNull TextComponentTranslation createTranslatedText(
      @NotNull final HTMaterialKey materialKey);
}
