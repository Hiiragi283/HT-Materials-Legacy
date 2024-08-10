package io.github.hiiragi283.mixin;

import io.github.hiiragi283.api.item.ItemProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@SuppressWarnings({"DataFlowIssue", "AddedMixinMembersNamePattern"})
@Mixin(Item.class)
public abstract class ItemMixin implements ItemProvider {

    @Unique
    private Item ht_materials$self() {
        return (Item) (Object) this;
    }

    @NotNull
    @Override
    public Item asItem() {
        return (Item) (Object) this;
    }

    @NotNull
    @Override
    public ItemStack asItemStack(int count, int meta) {
        return new ItemStack(ht_materials$self(), count, meta);
    }
}