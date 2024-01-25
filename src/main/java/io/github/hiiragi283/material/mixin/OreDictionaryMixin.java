package io.github.hiiragi283.material.mixin;

import javax.annotation.Nonnull;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.oredict.OreDictionary;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import io.github.hiiragi283.material.api.ExtendedOreDictionary;

@Mixin(OreDictionary.class)
public abstract class OreDictionaryMixin {

    /**
     * @author Hiiragi283
     * @reason use collections in ExtendedOreDictionary
     */
    @Overwrite(remap = false)
    public static int getOreID(String name) {
        return ExtendedOreDictionary.getOreID(name);
    }

    /**
     * @author Hiiragi283
     * @reason use collections in ExtendedOreDictionary
     */
    @Overwrite(remap = false)
    public static String getOreName(int id) {
        return ExtendedOreDictionary.getOreName(id);
    }

    /**
     * @author Hiiragi283
     * @reason use ItemWithMeta instead of ItemStack
     */
    @Overwrite(remap = false)
    public static int[] getOreIDs(@Nonnull ItemStack stack) {
        return ExtendedOreDictionary.getOreIDs(stack);
    }

    /**
     * @author Hiiragi283
     * @reason use collections in ExtendedOreDictionary
     */
    @Overwrite(remap = false)
    public static NonNullList<ItemStack> getOres(String name) {
        return ExtendedOreDictionary.getOres(name);
    }

    /**
     * @author Hiiragi283
     * @reason use collections in ExtendedOreDictionary
     */
    @Overwrite(remap = false)
    public static NonNullList<ItemStack> getOres(String name, boolean alwaysCreateEntry) {
        return ExtendedOreDictionary.getOres(name, alwaysCreateEntry);
    }

    /**
     * @author Hiiragi283
     * @reason use collections in ExtendedOreDictionary
     */
    @Overwrite(remap = false)
    public static boolean doesOreNameExist(String name) {
        return ExtendedOreDictionary.doesOreNameExist(name);
    }

    /**
     * @author Hiiragi283
     * @reason use collections in ExtendedOreDictionary
     */
    @Overwrite(remap = false)
    public static String[] getOreNames() {
        return ExtendedOreDictionary.getOreNames();
    }

    /**
     * @author Hiiragi283
     * @reason use ItemWithMeta instead of ItemStack
     */
    @Overwrite(remap = false)
    public static void registerOre(String name, Item ore) {
        ExtendedOreDictionary.registerOre(name, ore);
    }

    /**
     * @author Hiiragi283
     * @reason use ItemWithMeta instead of ItemStack
     */
    @Overwrite(remap = false)
    public static void registerOre(String name, Block ore) {
        ExtendedOreDictionary.registerOre(name, ore);
    }

    /**
     * @author Hiiragi283
     * @reason use ItemWithMeta instead of ItemStack
     */
    @Overwrite(remap = false)
    public static void registerOre(String name, ItemStack ore) {
        ExtendedOreDictionary.registerOre(name, ore);
    }

    /**
     * @author Hiiragi283
     * @reason use collections in ExtendedOreDictionary
     */
    @Overwrite(remap = false)
    public static void rebakeMap() {
        ExtendedOreDictionary.rebakeMap();
    }
}
