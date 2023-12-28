package io.github.hiiragi283.material.mixin;

import net.minecraftforge.oredict.OreDictionary;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(OreDictionary.class)
public class OreDictionaryMixin {

    @Shadow(remap = false)
    private static boolean hasInit;

    @Inject(method = "initVanillaEntries", at = @At("HEAD"), remap = false)
    private void ht_materials$init(CallbackInfo ci) {
        if (hasInit) {
            hasInit = false;
        }
    }

}