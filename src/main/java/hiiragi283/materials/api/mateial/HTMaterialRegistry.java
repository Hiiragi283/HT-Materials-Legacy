package hiiragi283.materials.api.mateial;

import java.util.OptionalInt;
import java.util.Set;
import java.util.stream.Stream;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.mojang.realmsclient.util.Pair;

import hiiragi283.materials.api.property.HTPropertyHolder;

public interface HTMaterialRegistry {

  @NotNull
  Set<HTMaterialKey> getMaterials();

  //    Index    //

  @NotNull
  OptionalInt getIndex(@NotNull final HTMaterialKey materialKey);

  @Nullable
  HTMaterialKey getMaterialFromIndex(final int index);

  @NotNull
  Stream<Pair<HTMaterialKey, Integer>> getIndexedMaterials();

  //    Property    //

  @NotNull
  HTPropertyHolder getPropertyHolder(@NotNull final HTMaterialKey materialKey);

  default @NotNull HTPropertyHolder getPropertyFromIndex(final int index) {
    var material = getMaterialFromIndex(index);
    if (material == null) return HTPropertyHolder.empty();
    return getPropertyHolder(material);
  }
}
