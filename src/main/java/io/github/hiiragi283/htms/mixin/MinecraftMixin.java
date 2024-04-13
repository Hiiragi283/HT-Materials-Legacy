package io.github.hiiragi283.htms.mixin;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IResourcePack;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import io.github.hiiragi283.htms.api.HTMaterialsAPI;
import io.github.hiiragi283.htms.api.resource.HTRuntimeResourcePack;

@Mixin(Minecraft.class)
public class MinecraftMixin {

    @Shadow
    @Final
    private List<IResourcePack> defaultResourcePacks;

    @Inject(method = "refreshResources", at = @At(value = "HEAD"))
    private void addInternalResourcePack(CallbackInfo ci) {
        if (!defaultResourcePacks.contains(HTRuntimeResourcePack.INSTANCE)) {
            defaultResourcePacks.add(HTRuntimeResourcePack.INSTANCE);
            HTMaterialsAPI.LOGGER.info("Added HTRuntimeResourcePack");
        }
    }
}
