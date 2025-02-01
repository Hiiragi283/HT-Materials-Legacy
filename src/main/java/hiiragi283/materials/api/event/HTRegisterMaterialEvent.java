package hiiragi283.materials.api.event;

import com.google.common.base.Preconditions;
import hiiragi283.materials.api.mateial.HTMaterialKey;
import net.minecraftforge.fml.common.eventhandler.Event;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class HTRegisterMaterialEvent extends Event {

    private final @NotNull Map<Integer, HTMaterialKey> materials;

    public HTRegisterMaterialEvent(@NotNull Map<Integer, HTMaterialKey> materials) {
        this.materials = materials;
    }

    public void register(@NotNull final HTMaterialKey material, final int index) {
        Preconditions.checkArgument(index > 0, "index must be greater than 0");
        Preconditions.checkArgument(materials.get(index) == null, "The index: " + index + " has already bound to " + material);
        materials.put(index, material);
    }
}
