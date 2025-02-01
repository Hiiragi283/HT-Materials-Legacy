package hiiragi283.materials.api.mateial;

import hiiragi283.materials.api.property.HTPropertyHolder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.OptionalInt;
import java.util.Set;
import java.util.function.BiConsumer;

public interface HTMaterialRegistry {

    @NotNull Set<HTMaterialKey> getMaterials();

    //    Index    //

    @NotNull OptionalInt getIndex(@NotNull final HTMaterialKey materialKey);

    @Nullable HTMaterialKey getMaterialFromIndex(final int index);

    //    Property    //

    @NotNull HTPropertyHolder getPropertyHolder(@NotNull final HTMaterialKey materialKey);

    default @NotNull HTPropertyHolder getPropertyFromIndex(final int index) {
        var material = getMaterialFromIndex(index);
        if (material == null) return HTPropertyHolder.empty();
        return getPropertyHolder(material);
    }

    default void forEachProperties(BiConsumer<HTMaterialKey, HTPropertyHolder> consumer) {
        for (HTMaterialKey material : getMaterials()) {
            consumer.accept(material, getPropertyHolder(material));
        }
    }
}
