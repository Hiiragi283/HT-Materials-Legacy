package io.github.hiiragi283.api.shape;

import java.util.Objects;

import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.google.common.base.CaseFormat;
import com.google.common.base.Preconditions;

import io.github.hiiragi283.api.HTMaterialsAPI;
import io.github.hiiragi283.api.material.HTMaterialKey;

public final class HTShapeKey {

    private final String name;
    private final String translationKey;

    public HTShapeKey(String name) {
        this.name = name;
        this.translationKey = "ht_shape." + name;
    }

    private HTShape cache;

    @Nullable
    public HTShape getShapeOrNull() {
        if (cache == null) {
            cache = HTMaterialsAPI.INSTANCE.shapeRegistry().getShape(this);
        }
        return cache;
    }

    @NotNull
    public HTShape getShape() throws NullPointerException {
        return Preconditions.checkNotNull(getShapeOrNull(), "Shape; %s is not registered!", name);
    }

    // Ore Dictionary

    @NotNull
    public String getOreDictPrefix() {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, name);
    }

    @NotNull
    public String getOreDict(@NotNull HTMaterialKey materialKey) {
        return getOreDictPrefix() + materialKey.getOreDictName();
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

    public String name() {
        return name;
    }

    public String translationKey() {
        return translationKey;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (HTShapeKey) obj;
        return Objects.equals(this.name, that.name) &&
                Objects.equals(this.translationKey, that.translationKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, translationKey);
    }

    @Override
    public String toString() {
        return "HTShapeKey[" +
                "name=" + name + ", " +
                "translationKey=" + translationKey + ']';
    }
}
