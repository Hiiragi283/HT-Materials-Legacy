package io.github.hiiragi283.htms.api.material;

import net.minecraft.client.resources.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import io.github.hiiragi283.htms.api.shape.HTShape;
import io.github.hiiragi283.htms.api.shape.HTShapes;

public interface HTMaterialType {

    @NotNull
    String getBlockTexPath();

    @Nullable
    HTShape getDefaultShape();

    @NotNull
    String getTranslationKey();

    @SideOnly(Side.CLIENT)
    default @NotNull String getTranslatedName() {
        return I18n.format(getTranslationKey());
    }

    default @NotNull String getTexturePath(HTShape shape) {
        return "item/" + shape.name();
    }

    @Nullable
    default String getOverlayPath(HTShape shape) {
        return getTexturePath(shape) + "_overlay";
    }

    enum Undefined implements HTMaterialType {

        INSTANCE;

        @Override
        public @NotNull String getBlockTexPath() {
            return "solid";
        }

        @Override
        public @Nullable HTShape getDefaultShape() {
            return null;
        }

        @Override
        public @NotNull String getTranslationKey() {
            return "ht_material.type.undefined";
        }
    }

    enum Gem implements HTMaterialType {

        AMETHYST(true),
        COAL(false),
        DIAMOND(true),
        EMERALD(true),
        FLINT(false),
        LAPIS(false),
        QUARTZ(true),
        RUBY(true),
        ;

        private final boolean hasOverlay;

        Gem(boolean hasOverlay) {
            this.hasOverlay = hasOverlay;
        }

        @Override
        public @NotNull String getBlockTexPath() {
            return "gem";
        }

        @Override
        public @NotNull HTShape getDefaultShape() {
            return HTShapes.GEM;
        }

        @Override
        public @NotNull String getTranslationKey() {
            return "ht_material.type." + name().toLowerCase();
        }

        @Override
        public @NotNull String getTexturePath(HTShape shape) {
            return shape == HTShapes.GEM ? "item/gem_" + name().toLowerCase() :
                    HTMaterialType.super.getTexturePath(shape);
        }

        @Override
        public @Nullable String getOverlayPath(HTShape shape) {
            return hasOverlay ? HTMaterialType.super.getOverlayPath(shape) : null;
        }
    }

    enum Metal implements HTMaterialType {

        DULL("dull") {

            @Override
            public @Nullable String getOverlayPath(HTShape shape) {
                return null;
            }
        },
        SHINY("shiny"),
        ;

        private final String blockTexPath;

        Metal(String blockTexPath) {
            this.blockTexPath = blockTexPath;
        }

        @Override
        public @NotNull String getBlockTexPath() {
            return blockTexPath;
        }

        @Override
        public @NotNull HTShape getDefaultShape() {
            return HTShapes.INGOT;
        }

        @Override
        public @NotNull String getTranslationKey() {
            return "ht_material.type." + blockTexPath;
        }
    }

    enum Stone implements HTMaterialType {

        INSTANCE;

        @Override
        public @NotNull String getBlockTexPath() {
            return "stone";
        }

        @Override
        public @Nullable HTShape getDefaultShape() {
            return null;
        }

        @Override
        public @NotNull String getTranslationKey() {
            return "ht_material.type.stone";
        }
    }

    enum Wood implements HTMaterialType {

        INSTANCE;

        @Override
        public @NotNull String getBlockTexPath() {
            return "wood";
        }

        @Override
        public @Nullable HTShape getDefaultShape() {
            return null;
        }

        @Override
        public @NotNull String getTranslationKey() {
            return "ht_material.type.wood";
        }
    }
}
