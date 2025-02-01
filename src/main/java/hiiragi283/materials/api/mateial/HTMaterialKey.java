package hiiragi283.materials.api.mateial;

import com.google.common.base.CaseFormat;
import it.unimi.dsi.fastutil.objects.Object2ObjectLinkedOpenHashMap;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.TextComponentTranslation;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public final class HTMaterialKey {

    private static final Map<String, HTMaterialKey> instances = new Object2ObjectLinkedOpenHashMap<>();

    public static @NotNull HTMaterialKey getOrCreate(@NotNull final String name) {
        return instances.computeIfAbsent(name.toLowerCase(), HTMaterialKey::new);
    }

    public final @NotNull String name;
    public final @NotNull String translationKey;
    public final @NotNull String oreDictName;

    private HTMaterialKey(@NotNull String name) {
        this.name = name;
        this.translationKey = "material.ht_materials." + name;
        this.oreDictName = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, name);
    }

    public @NotNull String getTranslatedName() {
        return I18n.format(translationKey);
    }

    public @NotNull TextComponentTranslation getTranslatedText() {
        return new TextComponentTranslation(translationKey);
    }
}
