package hiiragi283.materials.api.mateial.property;

import hiiragi283.materials.api.mateial.HTMaterialKey;
import hiiragi283.materials.api.mateial.part.HTPart;
import net.minecraft.util.ResourceLocation;

import java.util.function.BiFunction;

public interface HTMaterialModelFunction extends BiFunction<HTPart, HTMaterialKey, ResourceLocation> {
}
