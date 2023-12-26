package io.github.hiiragi283.material.api.item;

import io.github.hiiragi283.material.HMReference;
import io.github.hiiragi283.material.api.shape.HTShape;
import io.github.hiiragi283.material.api.shape.HTShapeKey;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class HTItemMaterial extends Item implements MaterialItemConvertible {

    public final HTShape shape;

    public HTItemMaterial(HTShapeKey shapeKey) {
        this.shape = shapeKey.getShape();
        hasSubtypes = true;
        registry.putIfAbsent(shapeKey, this);
        setRegistryName(HMReference.MOD_ID, shapeKey.name());
    }

    //    Item    //

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
            getMaterialStacks(this).forEach(items::add);
        }
    }

    //    MaterialItemConvertible    //

    @NotNull
    @Override
    public Item asItem() {
        return this;
    }

    @NotNull
    @Override
    public HTShapeKey getShapeKey() {
        return shape.key();
    }

    //    Registry    //

    private final static Map<HTShapeKey, HTItemMaterial> registry = new HashMap<>();

    public static Collection<HTItemMaterial> getItems() {
        return registry.values();
    }

    @Nullable
    public static HTItemMaterial getItem(HTShapeKey shapeKey) {
        return registry.get(shapeKey);
    }

}