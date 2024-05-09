package io.github.hiiragi283.htms.mixin;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;

import io.github.hiiragi283.htms.api.item.IItemProvider;

@Mixin(Block.class)
public abstract class MixinBlock implements IItemProvider {

    @SuppressWarnings({ "DataFlowIssue", "AddedMixinMembersNamePattern" })
    @Override
    public @NotNull Item asItem() {
        return Item.getItemFromBlock((Block) (Object) this);
    }
}