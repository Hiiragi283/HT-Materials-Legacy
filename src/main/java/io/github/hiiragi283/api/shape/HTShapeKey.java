package io.github.hiiragi283.api.shape;

import java.util.Objects;

import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.github.bsideup.jabel.Desugar;

import io.github.hiiragi283.api.HTMaterialsAPI;
import io.github.hiiragi283.api.material.HTMaterialKey;

@Desugar
public record HTShapeKey(String name, String translationKey) {

    public HTShapeKey(String name) {
        this(name, "ht_shape." + name);
    }

    @Nullable
    public HTShape getShapeOrNull() {
        return HTMaterialsAPI.INSTANCE.shapeRegistry().getShape(this);
    }

    @NotNull
    public HTShape getShape() throws NullPointerException {
        return Objects.requireNonNull(getShapeOrNull(), "");
    }

    // Translation

    @SideOnly(Side.CLIENT)
    public String getTranslatedName(HTMaterialKey key) {
        return I18n.format(translationKey, key.getTranslatedName());
    }

    @NotNull
    public ITextComponent getTranslatedText(HTMaterialKey key) {
        return new TextComponentTranslation(translationKey, key.getTranslatedName());
    }
}
