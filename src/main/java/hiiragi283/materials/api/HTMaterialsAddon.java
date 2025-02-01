package hiiragi283.materials.api;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

import net.minecraft.util.ResourceLocation;

import hiiragi283.materials.api.mateial.HTMaterialKey;
import hiiragi283.materials.api.mateial.part.HTPart;
import hiiragi283.materials.api.property.HTPropertyHolderBuilder;

public interface HTMaterialsAddon {

  int getPriority();

  String getModId();

  default ResourceLocation getId(String path) {
    return new ResourceLocation(getModId(), path);
  }

  default void registerPart(Consumer<HTPart> register) {}

  default void registerMaterial(BiConsumer<HTMaterialKey, Integer> register) {}

  default void setupProperties(Function<HTMaterialKey, HTPropertyHolderBuilder> register) {}
}
