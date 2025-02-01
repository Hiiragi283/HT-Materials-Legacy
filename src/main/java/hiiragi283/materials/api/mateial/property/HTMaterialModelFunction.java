package hiiragi283.materials.api.mateial.property;

import java.util.function.BiFunction;

import net.minecraft.util.ResourceLocation;

import hiiragi283.materials.api.mateial.HTMaterialKey;
import hiiragi283.materials.api.mateial.part.HTPart;

public interface HTMaterialModelFunction
    extends BiFunction<HTPart, HTMaterialKey, ResourceLocation> {}
