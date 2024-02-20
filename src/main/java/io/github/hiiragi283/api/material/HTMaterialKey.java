package io.github.hiiragi283.api.material;

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

@Desugar
public record HTMaterialKey(String name, String translationKey) {

    public HTMaterialKey(String name) {
        this(name, "ht_material." + name);
    }

    public static final HTMaterialKey EMPTY = new HTMaterialKey("");

    @Nullable
    public HTMaterial getMaterialOrNull() {
        return HTMaterialsAPI.INSTANCE.materialRegistry().getMaterial(this);
    }

    @NotNull
    public HTMaterial getMaterial() throws NullPointerException {
        return Objects.requireNonNull(getMaterialOrNull(), "");
    }

    // Translation

    @SideOnly(Side.CLIENT)
    public String getTranslatedName() {
        return I18n.format(translationKey);
    }

    @NotNull
    public ITextComponent getTranslatedText() {
        return new TextComponentTranslation(translationKey);
    }
}
