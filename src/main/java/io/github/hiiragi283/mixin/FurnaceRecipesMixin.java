package io.github.hiiragi283.mixin;

import io.github.hiiragi283.api.extension.ItemWithMeta;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.fml.common.FMLLog;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Mixin(value = FurnaceRecipes.class, remap = false)
public abstract class FurnaceRecipesMixin {

    @Unique
    private final Map<ItemWithMeta, ItemStack> ht_materials$smeltingMap = new HashMap<>();

    @Unique
    private final Map<ItemWithMeta, Float> ht_materials$expMap = new HashMap<>();

    @Shadow
    public abstract ItemStack getSmeltingResult(ItemStack itemStack);

    @Inject(method = "addSmeltingRecipe", at = @At("HEAD"), cancellable = true)
    private void ht_materials$addSmeltingRecipe(ItemStack input, ItemStack stack, float experience, CallbackInfo ci) {
        if (getSmeltingResult(input) != ItemStack.EMPTY) {
            FMLLog.log.info("Ignored smelting recipe with conflicting input: {} = {}", input, stack);
            ci.cancel();
        }
        ItemWithMeta.ofStacks(input).forEach(item -> {
            ht_materials$smeltingMap.put(item, stack);
            ht_materials$expMap.put(item, experience);
        });
        ci.cancel();
    }

    @Inject(method = "getSmeltingResult", at = @At("HEAD"), cancellable = true)
    private void ht_materials$getSmeltingResult(ItemStack stack, CallbackInfoReturnable<ItemStack> cir) {
        for (Map.Entry<ItemWithMeta, ItemStack> entry : ht_materials$smeltingMap.entrySet()) {
            if (entry.getKey().test(stack)) {
                cir.setReturnValue(entry.getValue());
            }
        }
    }

    @Inject(method = "getSmeltingList", at = @At("HEAD"), cancellable = true)
    private void ht_materials$getSmeltingList(CallbackInfoReturnable<Map<ItemStack, ItemStack>> cir) {
        cir.setReturnValue(ht_materials$smeltingMap.entrySet().stream()
                .collect(
                        Collectors.toMap(
                                entry -> entry.getKey().getStack(),
                                Map.Entry::getValue,
                                (entry1, entry2) -> entry1
                        )
                )
        );
    }

    @Inject(method = "getSmeltingExperience", at = @At("HEAD"), cancellable = true)
    private void ht_materials$getSmeltingExperience(ItemStack stack, CallbackInfoReturnable<Float> cir) {
        float itemExp = stack.getItem().getSmeltingExperience(stack);
        if (itemExp != -1) {
            cir.setReturnValue(itemExp);
        }

        for (Map.Entry<ItemWithMeta, Float> entry : ht_materials$expMap.entrySet()) {
            if (entry.getKey().test(stack)) {
                cir.setReturnValue(entry.getValue());
            }
        }
    }

}