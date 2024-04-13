package io.github.hiiragi283.htms.api.item;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import io.github.hiiragi283.htms.api.HTMaterialsAPI;
import io.github.hiiragi283.htms.api.material.HTMaterial;
import io.github.hiiragi283.htms.api.material.HTMaterialKey;
import io.github.hiiragi283.htms.api.part.HTPart;
import io.github.hiiragi283.htms.api.shape.HTShape;

public interface IMaterialItemProvider extends IItemProvider {

    default @Nullable HTMaterial getMaterial(@NotNull ItemStack stack) {
        return HTMaterialsAPI.INSTANCE.getMaterialRegistry().get(stack.getMetadata());
    }

    default @Nullable HTMaterialKey getMaterialKey(@NotNull ItemStack stack) {
        HTMaterial material = getMaterial(stack);
        return material == null ? null : material.key();
    }

    @NotNull
    HTShape getShape();

    @NotNull
    Stream<@NotNull HTMaterialKey> getMaterialKeys();

    @NotNull
    ItemStack materialToStack(@NotNull HTMaterial material);

    default @NotNull Stream<@NotNull HTMaterial> getMaterials() {
        return getMaterialKeys().map(HTMaterialKey::getMaterialOrThrow);
    }

    default @NotNull IntStream getMaterialIndexes() {
        return getMaterials().mapToInt(HTMaterial::index);
    }

    default @NotNull Stream<@NotNull ItemStack> getMaterialStacks() {
        return getMaterials().map(this::materialToStack).filter(stack -> !stack.isEmpty());
    }

    default @Nullable HTPart getPart(@NotNull ItemStack stack) {
        HTMaterialKey materialKey = getMaterialKey(stack);
        return materialKey == null ? null : new HTPart(getShape(), materialKey);
    }

    default @Nullable String getOreDict(@NotNull ItemStack stack) {
        HTMaterialKey materialKey = getMaterialKey(stack);
        return materialKey == null ? null : getShape().getOreDict(materialKey);
    }

    @SideOnly(Side.CLIENT)
    default int getItemColor(ItemStack stack, int tintIndex) {
        HTMaterial material = getMaterial(stack);
        return material == null ? -1 : tintIndex == 0 ? material.color.getRGB() : -1;
    }
}
