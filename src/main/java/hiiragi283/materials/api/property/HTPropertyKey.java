package hiiragi283.materials.api.property;

import com.github.bsideup.jabel.Desugar;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.Nullable;

@Desugar
public record HTPropertyKey<T>(ResourceLocation location, Class<T> clazz) {

    public @Nullable T castOrNull(@Nullable Object obj) {
        if (obj == null) return null;
        if (clazz.isInstance(obj)) return clazz.cast(obj);
        return null;
    }
}
