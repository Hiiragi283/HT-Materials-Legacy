package io.github.hiiragi283.api.item;

import java.util.stream.Stream;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

import org.jetbrains.annotations.NotNull;

import io.github.hiiragi283.api.HTMaterialsAPI;
import io.github.hiiragi283.api.material.HTMaterial;
import io.github.hiiragi283.api.material.flag.HTMaterialFlag;
import io.github.hiiragi283.api.shape.HTShapeKey;

public final class HTMaterialItem extends Item implements IMaterialItemProvider {

    public final HTShapeKey shapeKey;
    private final HTMaterialFlag materialFlag;

    public HTMaterialItem(HTShapeKey shapeKey, HTMaterialFlag materialFlag) {
        this.shapeKey = shapeKey;
        this.materialFlag = materialFlag;
        hasSubtypes = true;
        setCreativeTab(HTMaterialsAPI.INSTANCE.creativeTab());
        setRegistryName(HTMaterialsAPI.location(shapeKey.name()));
    }

    // Item

    @Override
    public int getMetadata(int damage) {
        return damage > OreDictionary.WILDCARD_VALUE ? OreDictionary.WILDCARD_VALUE : Math.max(damage, 0);
    }

    @NotNull
    @Override
    public String getItemStackDisplayName(@NotNull ItemStack stack) {
        return getShapeKey().getTranslatedName(getMaterialKey(stack));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(@NotNull CreativeTabs tab, @NotNull NonNullList<ItemStack> items) {
        if (isInCreativeTab(tab)) {
            getMaterialStacks().forEach(items::add);
        }
    }

    // IMaterialItemProvider

    @NotNull
    @Override
    public HTShapeKey getShapeKey() {
        return shapeKey;
    }

    @NotNull
    @Override
    public Stream<HTMaterial> getValidMaterials() {
        return HTMaterialsAPI.INSTANCE.materialRegistry().getValues().stream()
                .filter(material -> material.hasFlag(materialFlag));
    }

    @Override
    public Item asItem() {
        return this;
    }
}
