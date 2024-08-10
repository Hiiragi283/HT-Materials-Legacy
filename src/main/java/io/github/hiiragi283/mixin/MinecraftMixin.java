package io.github.hiiragi283.mixin;

import io.github.hiiragi283.api.HTLogger;
import io.github.hiiragi283.api.extension.HTRuntimeResourcePack;
import kotlin.Unit;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IResourcePack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(Minecraft.class)
public abstract class MinecraftMixin {

    @Shadow
    @Final
    private List<IResourcePack> defaultResourcePacks;

    @Inject(method = "refreshResources", at = @At(value = "HEAD"))
    private void addInternalResourcePack(CallbackInfo ci) {
        if (!defaultResourcePacks.contains(HTRuntimeResourcePack.INSTANCE)) {
            defaultResourcePacks.add(HTRuntimeResourcePack.INSTANCE);
            HTLogger.log(logger -> {
                logger.info("Added HTRuntimeResourcePack");
                return Unit.INSTANCE;
            });
        }
    }
}