package io.github.hiiragi283.api.item;

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

import io.github.hiiragi283.api.util.HTBuilderUtil;

public class HTMetaItem {

    public static HTMetaItem EMPTY = new HTMetaItem(Items.AIR, 0);

    @NotNull
    public static Stream<HTMetaItem> fromState(IBlockState state, Consumer<HTMetaItem> consumer) {
        Block block = state.getBlock();
        int meta = block.getMetaFromState(state);
        return from(block, meta);
    }

    @NotNull
    public static Stream<HTMetaItem> fromStack(ItemStack stack) {
        return from(stack.getItem(), stack.getMetadata());
    }

    @NotNull
    public static Stream<@NotNull HTMetaItem> from(Object obj, int meta) {
        Item item;
        if (obj instanceof IItemProvider) {
            item = ((IItemProvider) obj).asItem();
            if (item != Items.AIR) {
                if (meta == OreDictionary.WILDCARD_VALUE) {
                    return HTBuilderUtil
                            .<ItemStack>buildNonNullList(list -> item.getSubItems(CreativeTabs.SEARCH, list))
                            .stream()
                            .map(stack -> new HTMetaItem(stack.getItem(), stack.getMetadata()));
                } else return Stream.of(new HTMetaItem(item, meta));
            }
        }
        return Stream.empty();
    }

    public final Item item;
    public final int meta;

    private HTMetaItem(Item item, int meta) {
        this.item = item;
        this.meta = meta;
    }

    public boolean isEmpty() {
        return Objects.equals(this, EMPTY) || item == Items.AIR;
    }

    public boolean isNotEmpty() {
        return !isEmpty();
    }

    @NotNull
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
        if (obj instanceof HTMetaItem other) {
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
