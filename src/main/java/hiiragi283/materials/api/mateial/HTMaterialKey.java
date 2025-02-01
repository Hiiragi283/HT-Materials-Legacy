package hiiragi283.materials.api.mateial;

import java.util.Map;

import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentTranslation;

import org.jetbrains.annotations.NotNull;

import com.google.common.base.CaseFormat;

import hiiragi283.materials.api.HTMaterialsAPI;
import it.unimi.dsi.fastutil.objects.Object2ObjectLinkedOpenHashMap;

public final class HTMaterialKey {

  private static final Map<ResourceLocation, HTMaterialKey> instances =
      new Object2ObjectLinkedOpenHashMap<>();

  public static @NotNull HTMaterialKey getOrCreate(final @NotNull String name) {
    return getOrCreate(HTMaterialsAPI.id(name));
  }

  public static @NotNull HTMaterialKey getOrCreate(@NotNull final ResourceLocation location) {
    return instances.computeIfAbsent(location, HTMaterialKey::new);
  }

  public final @NotNull ResourceLocation location;
  public final @NotNull String translationKey;
  public final @NotNull String oreDictName;

  private HTMaterialKey(@NotNull ResourceLocation location) {
    this.location = location;
    this.translationKey = "material." + location.getNamespace() + '.' + location.getPath();
    this.oreDictName = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, location.getPath());
  }

  public @NotNull String getTranslatedName() {
    return I18n.format(translationKey);
  }

  public @NotNull TextComponentTranslation getTranslatedText() {
    return new TextComponentTranslation(translationKey);
  }
}
