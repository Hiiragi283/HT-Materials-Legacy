package hiiragi283.materials.common;

import hiiragi283.materials.api.HTMaterialsAPI;
import hiiragi283.materials.api.mateial.HTMaterialRegistry;
import org.jetbrains.annotations.NotNull;

public final class HTMaterialsAPIImpl implements HTMaterialsAPI {
    @Override
    public @NotNull HTMaterialRegistry getMaterialRegistry() {
        return HTMaterialRegistryImpl.INSTANCE;
    }
}
