package io.github.hiiragi283.htms.api.material;

import java.util.Objects;
import java.util.Optional;

import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.github.bsideup.jabel.Desugar;
import com.google.common.base.CaseFormat;
import com.google.common.base.Supplier;

import io.github.hiiragi283.htms.api.HTMaterialsAPI;

@Desugar
public record HTMaterialKey(@NotNull String name) {

    // Material //

    public @Nullable HTMaterial getMaterialOrNull() {
        return HTMaterialsAPI.INSTANCE.getMaterialRegistry().get(this);
    }

    public @NotNull Optional<HTMaterial> getMaterialOptional() {
        return Optional.ofNullable(getMaterialOrNull());
    }

    public @NotNull HTMaterial getMaterialOrThrow() throws NullPointerException {
        return Objects.requireNonNull(getMaterialOrNull());
    }

    public @NotNull Supplier<HTMaterial> getMaterialLazy() {
        return this::getMaterialOrThrow;
    }

    // Ore Dict //

    public @NotNull String getOreDictName() {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, name);
    }

    // Translation //

    private @NotNull String getTranslationKey() {
        return "ht_material." + name();
    }

    @SideOnly(Side.CLIENT)
    public @NotNull String getTranslatedName() {
        return I18n.format(getTranslationKey());
    }

    @SideOnly(Side.CLIENT)
    public @NotNull ITextComponent getTranslatedText() {
        return new TextComponentTranslation(getTranslationKey());
    }

    // Object //

    @Override
    public String toString() {
        return name();
    }
}
