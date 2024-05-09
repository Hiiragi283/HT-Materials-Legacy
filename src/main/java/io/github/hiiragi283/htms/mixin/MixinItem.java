package io.github.hiiragi283.htms.mixin;

import net.minecraft.item.Item;

import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;

import io.github.hiiragi283.htms.api.item.IItemProvider;

@Mixin(Item.class)
public abstract class MixinItem implements IItemProvider {

    @SuppressWarnings({ "AddedMixinMembersNamePattern", "DataFlowIssue" })
    @Override
    public @NotNull Item asItem() {
        return (Item) (Object) this;
    }
}