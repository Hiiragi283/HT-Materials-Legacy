package io.github.hiiragi283.htms.api.item;

import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import io.github.hiiragi283.htms.api.HMConstants;
import io.github.hiiragi283.htms.api.HTMaterialsAPI;
import io.github.hiiragi283.htms.api.material.HTMaterial;
import io.github.hiiragi283.htms.api.material.HTMaterialKey;
import io.github.hiiragi283.htms.api.shape.HTShape;

public final class ItemShapedMaterial extends Item implements IMaterialItemProvider {

    @NotNull
    private final HTShape shape;

    @NotNull
    private final Set<@NotNull HTMaterialKey> validMaterials;

    public ItemShapedMaterial(@NotNull HTShape shape, @NotNull Stream<@NotNull HTMaterialKey> validMaterials) {
        this.shape = shape;
        this.validMaterials = validMaterials
                .sorted(Comparator.comparing(key -> key.getMaterialOrThrow().index()))
                .collect(Collectors.toCollection(LinkedHashSet::new));
        setCreativeTab(HTMaterialsAPI.INSTANCE.getCreativeTab());
        setHasSubtypes(true);
        setTranslationKey(HMConstants.MOD_ID + "." + shape.name());
        setRegistryName(HMConstants.MOD_ID, shape.name());
    }

    @SideOnly(Side.CLIENT)
    @Override
    public @NotNull String getItemStackDisplayName(@NotNull ItemStack stack) {
        HTMaterial material = getMaterial(stack);
        return material != null ? getShape().getTranslatedName(material.key()) : super.getItemStackDisplayName(stack);
    }

    @Override
    public void getSubItems(@NotNull CreativeTabs tab, @NotNull NonNullList<ItemStack> items) {
        if (!isInCreativeTab(tab)) return;
        getMaterialStacks().forEach(items::add);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(@NotNull ItemStack stack, @Nullable World worldIn, @NotNull List<String> tooltip,
                               @NotNull ITooltipFlag flagIn) {
        HTMaterial material = getMaterial(stack);
        if (material != null) {
            HTMaterial.TooltipContext context = new HTMaterial.TooltipContext(material, getShape(), stack, tooltip);
            HTMaterial.addInformation(context);
        }
    }

    // IMaterialItemProvider //

    @Override
    public @NotNull HTShape getShape() {
        return shape;
    }

    @Override
    public @NotNull Stream<HTMaterialKey> getMaterialKeys() {
        return validMaterials.stream();
    }

    @Override
    public @NotNull ItemStack materialToStack(@NotNull HTMaterial material) {
        return new ItemStack(this, 1, material.index());
    }

    @Override
    public @NotNull Item asItem() {
        return this;
    }
}
