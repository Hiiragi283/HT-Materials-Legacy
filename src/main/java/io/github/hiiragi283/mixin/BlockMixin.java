package io.github.hiiragi283.mixin;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;

import io.github.hiiragi283.api.item.IItemProvider;

@SuppressWarnings({ "DataFlowIssue", "AddedMixinMembersNamePattern" })
@Mixin(Block.class)
public abstract class BlockMixin implements IItemProvider {

    @NotNull
    @Override
    public Item asItem() {
        return Item.getItemFromBlock((Block) (Object) this);
    }
}
