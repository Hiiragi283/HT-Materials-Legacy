package io.github.hiiragi283.api.material;

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

public final class HTMaterialKey {

    public static final HTMaterialKey EMPTY = new HTMaterialKey("");
    private final String name;
    private final String translationKey;

    public HTMaterialKey(String name) {
        this.name = name;
        this.translationKey = "ht_material." + name;
    }

    private HTMaterial cache;

    @Nullable
    public HTMaterial getMaterialOrNull() {
        if (cache == null) {
            cache = HTMaterialsAPI.INSTANCE.materialRegistry().getMaterial(this);
        }
        return cache;
    }

    @NotNull
    public HTMaterial getMaterial() throws NullPointerException {
        return Preconditions.checkNotNull(getMaterialOrNull(), "Material; %s is not registered!", name);
    }

    // Ore Dictionary

    @NotNull
    public String getOreDictName() {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, name);
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
        var that = (HTMaterialKey) obj;
        return Objects.equals(this.name, that.name) &&
                Objects.equals(this.translationKey, that.translationKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, translationKey);
    }

    @Override
    public String toString() {
        return "HTMaterialKey[" +
                "name=" + name + ", " +
                "translationKey=" + translationKey + ']';
    }
}
