package io.github.hiiragi283.material.mixin;

import io.github.hiiragi283.material.api.item.ItemConvertible;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;

@SuppressWarnings({"DataFlowIssue", "AddedMixinMembersNamePattern"})
@Mixin(Block.class)
public abstract class BlockMixin implements ItemConvertible {

    @NotNull
    @Override
    public Item asItem() {
        return Item.getItemFromBlock((Block) (Object) this);
    }

}