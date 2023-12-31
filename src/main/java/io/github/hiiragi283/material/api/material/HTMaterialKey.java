package io.github.hiiragi283.material.api.material;

import com.github.bsideup.jabel.Desugar;
import com.google.common.base.CaseFormat;
import io.github.hiiragi283.material.HMReference;
import io.github.hiiragi283.material.api.registry.HTObjectKey;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Desugar
public record HTMaterialKey(String name, int index) implements HTObjectKey<HTMaterial> {

    public static final HTMaterialKey EMPTY = new HTMaterialKey("", 0);

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Class<HTMaterial> getObjClass() {
        return HTMaterial.class;
    }

    @NotNull
    public HTMaterial getMaterial() {
        return HTMaterial.getMaterial(this.name);
    }

    @Nullable
    public HTMaterial getMaterialOrNull() {
        return HTMaterial.getMaterialOrNull(this.name);
    }

    //    Ore Dict    //

    @NotNull
    public String getOreDictName() {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, name);
    }

    //    ResourceLocation    //

    @NotNull
    public ResourceLocation getLocation(String path) {
        return getLocation(HMReference.MOD_ID, path);
    }

    @NotNull
    public ResourceLocation getLocation(String namespace, String path) {
        return new ResourceLocation(namespace, path);
    }

    //    Translation    //

    @NotNull
    public String getTranslationKey() {
        return "ht_material." + name;
    }

    @NotNull
    @SideOnly(Side.CLIENT)
    public String getTranslatedName() {
        return I18n.format(getTranslationKey());
    }

    @NotNull
    public ITextComponent getTranslatedText() {
        return new TextComponentTranslation(getTranslationKey() + name);
    }

}