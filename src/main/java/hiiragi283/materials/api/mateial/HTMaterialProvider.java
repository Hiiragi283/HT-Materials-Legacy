package hiiragi283.materials.api.mateial;

import hiiragi283.materials.api.HTMaterialsAPI;
import hiiragi283.materials.api.mateial.part.HTPart;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface HTMaterialProvider {
    @NotNull HTPart getPart();

    default @Nullable HTMaterialKey getMaterialKey(int index) {
        return HTMaterialsAPI.INSTANCE.getMaterialRegistry().getMaterialFromIndex(index);
    }
}
