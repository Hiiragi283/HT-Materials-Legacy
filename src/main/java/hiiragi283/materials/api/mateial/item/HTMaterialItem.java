package hiiragi283.materials.api.mateial.item;

import java.util.stream.Stream;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import hiiragi283.materials.api.HTMaterialsAPI;
import hiiragi283.materials.api.mateial.DefaultMaterialProperties;
import hiiragi283.materials.api.mateial.HTMaterialKey;
import hiiragi283.materials.api.mateial.HTMaterialRegistry;
import hiiragi283.materials.api.mateial.part.HTPart;
import hiiragi283.materials.api.mateial.property.HTMaterialGroup;

public interface HTMaterialItem {
  @NotNull
  HTPart getPart();

  @NotNull
  Item asItem();

  default @NotNull HTMaterialRegistry getRegistry() {
    return HTMaterialsAPI.INSTANCE.getMaterialRegistry();
  }

  default Stream<HTMaterialKey> getValidMaterials() {
    return getRegistry().getMaterials().stream().filter(this::isInGroup);
  }

  default boolean isInGroup(@NotNull final HTMaterialKey material) {
    return getRegistry()
        .getPropertyHolder(material)
        .getOptional(DefaultMaterialProperties.GROUP)
        .map((HTMaterialGroup group) -> group.contains(getPart()))
        .orElse(false);
  }

  default @NotNull ItemStack getStackFromMaterial(final HTMaterialKey material, final int count) {
    var index = getRegistry().getIndex(material);
    if (index.isPresent()) {
      return new ItemStack(asItem(), count, index.getAsInt());
    } else {
      return ItemStack.EMPTY;
    }
  }

  default @NotNull ItemStack getStackFromMaterial(final HTMaterialKey material) {
    return getStackFromMaterial(material, 1);
  }

  default @Nullable HTMaterialKey getMaterialKey(final int index) {
    return getRegistry().getMaterialFromIndex(index);
  }
}
