package io.github.hiiragi283.material.api.shape;

import com.github.bsideup.jabel.Desugar;
import com.google.common.base.CaseFormat;
import crafttweaker.annotations.ZenRegister;
import io.github.hiiragi283.material.api.material.HTMaterialKey;
import io.github.hiiragi283.material.api.registry.HTObjectKey;
import io.github.hiiragi283.material.compat.crt.HTCrTPlugin;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenGetter;
import stanhebben.zenscript.annotations.ZenMethod;

@Desugar
@ZenClass(HTCrTPlugin.SHAPE_PREFIX + "HTShapeKey")
@ZenRegister
public record HTShapeKey(String name) implements HTObjectKey<HTShape> {

    @Override
    @ZenGetter("name")
    public String getName() {
        return name;
    }

    @Override
    public Class<HTShape> getObjClass() {
        return HTShape.class;
    }

    @NotNull
    @ZenGetter("shape")
    public HTShape getShape() {
        return HTShape.getShape(this.name);
    }

    @Nullable
    public HTShape getShapeOrNull() {
        return HTShape.getShapeOrNull(this.name);
    }

    //    Ore Dict    //

    @NotNull
    @ZenGetter("prefixName")
    public String getPrefixName() {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, name);
    }

    @NotNull
    public String getOreDict(HTMaterialKey materialKey) {
        return getPrefixName() + materialKey.getOreDictName();
    }

    //    Translation    //

    @NotNull
    @SideOnly(Side.CLIENT)
    @ZenMethod
    public String getTranslatedName(HTMaterialKey materialKey) {
        return I18n.format("ht_shape." + name, materialKey.getTranslatedName());
    }

    @NotNull
    public ITextComponent getTranslatedText(HTMaterialKey materialKey) {
        return new TextComponentTranslation("ht_material." + name, materialKey.getTranslatedName());
    }

}