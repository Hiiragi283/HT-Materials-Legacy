package io.github.hiiragi283.bwms.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import io.github.hiiragi283.bwms.api.BWMsAPI;
import io.github.hiiragi283.bwms.api.material.HTMaterialTooltipContext;
import io.github.hiiragi283.bwms.api.part.HTPart;
import net.minecraft.client.gui.GuiTooltip;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.inventory.slot.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = GuiTooltip.class, remap = false)
public abstract class GuiTooltipMixin {

	@Inject(
		method = "getTooltipText(Lnet/minecraft/core/item/ItemStack;ZLnet/minecraft/core/player/inventory/slot/Slot;)Ljava/lang/String;",
		at = @At(
			value = "INVOKE",
			target = "Ljava/lang/StringBuilder;append(C)Ljava/lang/StringBuilder;",
			ordinal = 6,
			shift = At.Shift.AFTER
		)
	)
	private void bwms$getTooltipText(ItemStack itemStack, boolean showDescription, Slot slot, CallbackInfoReturnable<String> cir, @Local StringBuilder text) {
		HTPart part = BWMsAPI.getInstance().getPartManager().get(itemStack);
		if (part != null) {
			new HTMaterialTooltipContext(part.getMaterialKey(), part.getMaterial(), part.getShapeKey(), itemStack)
				.appendTooltips(text);
		}
	}
}
