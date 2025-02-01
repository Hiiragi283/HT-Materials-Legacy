package hiiragi283.materials.api.event;

import hiiragi283.materials.api.mateial.HTMaterialKey;
import hiiragi283.materials.api.property.HTPropertyHolderBuilder;
import net.minecraftforge.fml.common.eventhandler.Event;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class HTSetupPropertiesEvent extends Event {

    private final @NotNull Map<HTMaterialKey, HTPropertyHolderBuilder> properties;

    public HTSetupPropertiesEvent(@NotNull Map<HTMaterialKey, HTPropertyHolderBuilder> properties) {
        this.properties = properties;
    }

    public @NotNull HTPropertyHolderBuilder getOrCreate(@NotNull final HTMaterialKey key) {
        return properties.computeIfAbsent(key, (HTMaterialKey k) -> new HTPropertyHolderBuilder());
    }
}
