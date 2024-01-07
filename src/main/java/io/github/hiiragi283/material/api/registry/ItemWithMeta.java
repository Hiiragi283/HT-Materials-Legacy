package io.github.hiiragi283.material.api.registry;

import com.github.bsideup.jabel.Desugar;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

@Desugar
public record ItemWithMeta(Item item, int meta) {

    public static ItemWithMeta EMPTY = new ItemWithMeta(Items.AIR, 0);

    public static ItemWithMeta of(ItemStack stack) {
        if (stack.isEmpty()) {
            return EMPTY;
        }
        return new ItemWithMeta(stack.getItem(), stack.getMetadata());
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

}