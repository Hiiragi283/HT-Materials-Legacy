package io.github.hiiragi283.material.api.registry;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Stream;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.oredict.OreDictionary;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import io.github.hiiragi283.material.api.item.IItemProvider;
import io.github.hiiragi283.material.util.HTBuilderUtils;

public class ItemWithMeta {

    public static ItemWithMeta EMPTY = new ItemWithMeta(Items.AIR, 0);

    public static Stream<ItemWithMeta> fromState(IBlockState state, Consumer<ItemWithMeta> consumer) {
        Block block = state.getBlock();
        int meta = block.getMetaFromState(state);
        return from(block, meta);
    }

    public static Stream<ItemWithMeta> fromStack(ItemStack stack) {
        return from(stack.getItem(), stack.getMetadata());
    }

    public static Stream<ItemWithMeta> from(Object obj, int meta) {
        Item item;
        if (obj instanceof IItemProvider) {
            item = ((IItemProvider) obj).asItem();
            if (item != Items.AIR) {
                if (meta == OreDictionary.WILDCARD_VALUE) {
                    return HTBuilderUtils
                            .<ItemStack>buildNonNullList(list -> item.getSubItems(CreativeTabs.SEARCH, list))
                            .stream()
                            .map(stack -> new ItemWithMeta(stack.getItem(), stack.getMetadata()));
                } else return Stream.of(new ItemWithMeta(item, meta));
            }
        }
        return Stream.empty();
    }

    public final Item item;
    public final int meta;

    private ItemWithMeta(Item item, int meta) {
        this.item = item;
        this.meta = meta;
    }

    public boolean isEmpty() {
        return Objects.equals(this, EMPTY) || item == Items.AIR;
    }

    public boolean isNotEmpty() {
        return !isEmpty();
    }

    public ItemStack toStack() {
        return toStack(1);
    }

    @NotNull
    public ItemStack toStack(int count) {
        return new ItemStack(item, count, meta);
    }

    @NotNull
    public ItemStack toStack(int count, @Nullable NBTTagCompound tagCompound) {
        var stack = toStack(count);
        stack.setTagCompound(tagCompound);
        return stack;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj instanceof ItemWithMeta other) {
            return other.item == this.item && other.meta == this.meta;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = item.hashCode();
        hash = 31 * hash + meta;
        return hash;
    }

    @Override
    public String toString() {
        return item.getRegistryName() + ":" + meta;
    }
}
