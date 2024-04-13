package io.github.hiiragi283.htms.api.shape;

import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.jetbrains.annotations.NotNull;

import com.github.bsideup.jabel.Desugar;
import com.google.common.base.CaseFormat;

import io.github.hiiragi283.htms.api.material.HTMaterialKey;

@Desugar
public record HTShape(@NotNull String name) {

    // Ore Dict //

    public @NotNull String getOreDictPrefix() {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, name());
    }

    public @NotNull String getOreDict(@NotNull HTMaterialKey materialKey) {
        return getOreDictPrefix() + materialKey.getOreDictName();
    }

    public @NotNull String getOreDict(@NotNull String name) {
        return getOreDictPrefix() + CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, name);
    }

    // Translation //

    private @NotNull String getTranslationKey() {
        return "ht_shape." + name();
    }

    @SideOnly(Side.CLIENT)
    public @NotNull String getTranslatedName(@NotNull HTMaterialKey materialKey) {
        return getTranslatedName(materialKey.getTranslatedName());
    }

    @SideOnly(Side.CLIENT)
    public @NotNull String getTranslatedName(@NotNull String name) {
        return I18n.format(getTranslationKey(), name);
    }

    @SideOnly(Side.CLIENT)
    public @NotNull ITextComponent getTranslatedText(@NotNull HTMaterialKey materialKey) {
        return getTranslatedText(materialKey.getTranslatedName());
    }

    @SideOnly(Side.CLIENT)
    public @NotNull ITextComponent getTranslatedText(@NotNull String name) {
        return new TextComponentTranslation(getTranslationKey(), name);
    }

    // Object //

    @Override
    public String toString() {
        return name();
    }
}
