package io.github.hiiragi283.material.mixin;

import net.minecraft.item.Item;

import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;

import io.github.hiiragi283.material.api.item.IItemProvider;

@Mixin(Item.class)
@SuppressWarnings({ "AddedMixinMembersNamePattern", "DataFlowIssue" })
public abstract class ItemMixin implements IItemProvider {

    @NotNull
    @Override
    public Item asItem() {
        return ((Item) (Object) this);
    }
}
