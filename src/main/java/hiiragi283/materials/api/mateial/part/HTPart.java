package hiiragi283.materials.api.mateial.part;

import hiiragi283.materials.api.mateial.HTMaterialKey;
import net.minecraft.util.text.TextComponentTranslation;
import org.jetbrains.annotations.NotNull;

public abstract class HTPart {

    public final @NotNull String name;

    protected HTPart(@NotNull String name) {
        this.name = name;
    }

    public abstract @NotNull String createOreDict(@NotNull final HTMaterialKey materialKey);

    public abstract @NotNull String createTranslatedName(@NotNull final HTMaterialKey materialKey);

    public abstract @NotNull TextComponentTranslation createTranslatedText(@NotNull final HTMaterialKey materialKey);
}
