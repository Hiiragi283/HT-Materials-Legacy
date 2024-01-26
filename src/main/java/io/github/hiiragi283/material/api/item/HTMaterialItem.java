package io.github.hiiragi283.material.api.item;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import io.github.hiiragi283.material.HTMaterialsMod;
import io.github.hiiragi283.material.api.shape.HTShapeKey;

public final class HTMaterialItem extends Item implements IMaterialItemProvider {

    public final HTShapeKey shapeKey;

    public HTMaterialItem(HTShapeKey shapeKey) {
        this.shapeKey = shapeKey;
        hasSubtypes = true;
        registry.putIfAbsent(shapeKey, this);
        setCreativeTab(HTMaterialsMod.CREATIVE_TABS);
        setRegistryName(HTMaterialsMod.getId(shapeKey.name()));
    }

    // Item //

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

    // IMaterialItemProvider //

    @NotNull
    @Override
    public HTShapeKey getShapeKey() {
        return shapeKey;
    }

    @Override
    public Item asItem() {
        return this;
    }

    // Registry //

    private final static Map<HTShapeKey, HTMaterialItem> registry = new LinkedHashMap<>();

    public static Collection<HTMaterialItem> getItems() {
        return registry.values();
    }

    @Nullable
    public static HTMaterialItem getItem(HTShapeKey shapeKey) {
        return registry.get(shapeKey);
    }
}
