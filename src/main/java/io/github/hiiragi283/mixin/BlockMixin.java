package io.github.hiiragi283.mixin;

import io.github.hiiragi283.api.item.ItemProvider;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@SuppressWarnings({"AddedMixinMembersNamePattern", "DataFlowIssue"})
@Mixin(Block.class)
public abstract class BlockMixin implements ItemProvider {
    
    @Unique
    private Block ht_materials$self() {
        return (Block) (Object) this;
    }
    
    @NotNull
    @Override
    public Item asItem() {
        return Item.getItemFromBlock(ht_materials$self());
    }

    @NotNull
    @Override
    public ItemStack asItemStack(int count, int meta) {
        return new ItemStack(ht_materials$self(), count, meta);
    }
}