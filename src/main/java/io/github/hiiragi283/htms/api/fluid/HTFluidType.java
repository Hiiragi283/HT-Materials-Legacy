package io.github.hiiragi283.htms.api.fluid;

import net.minecraft.util.ResourceLocation;

import org.jetbrains.annotations.NotNull;

import io.github.hiiragi283.htms.api.material.HTMaterialKey;

public enum HTFluidType {

    MIXTURE {

        @Override
        public @NotNull ResourceLocation getTexture(@NotNull HTMaterialKey materialKey) {
            return new ResourceLocation("block/concrete_powder_white");
        }
    },
    PURE,
    SOLUTION,
    ;

    public @NotNull String getName(@NotNull HTMaterialKey materialKey) {
        return materialKey.name();
    }

    public @NotNull ResourceLocation getTexture(@NotNull HTMaterialKey materialKey) {
        return new ResourceLocation("block/mushroom_block_skin_stem");
    }
}
