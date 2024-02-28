package io.github.hiiragi283.mixin;

import net.minecraft.item.Item;

import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;

import io.github.hiiragi283.api.item.IItemProvider;

@SuppressWarnings({ "AddedMixinMembersNamePattern", "DataFlowIssue" })
@Mixin(Item.class)
public abstract class ItemMixin implements IItemProvider {

    @NotNull
    @Override
    public Item asItem() {
        return ((Item) (Object) this);
    }
}
