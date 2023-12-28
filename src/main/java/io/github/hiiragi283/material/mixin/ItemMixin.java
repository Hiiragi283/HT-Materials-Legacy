package io.github.hiiragi283.material.mixin;

import io.github.hiiragi283.material.api.item.IItemConvertible;
import net.minecraft.item.Item;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Item.class)
@SuppressWarnings({"AddedMixinMembersNamePattern", "DataFlowIssue"})
public abstract class ItemMixin implements IItemConvertible {

    @NotNull
    @Override
    public Item asItem() {
        return ((Item) (Object) this);
    }

}