package io.github.hiiragi283.material.api.shape;

import com.github.bsideup.jabel.Desugar;
import io.github.hiiragi283.material.api.material.HTMaterialKey;
import io.github.hiiragi283.material.api.registry.HTObjectKey;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Desugar
public record HTShapeKey(String name) implements HTObjectKey<HTShape> {

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Class<HTShape> getObjClass() {
        return HTShape.class;
    }

    @NotNull
    public HTShape getShape() {
        return HTShape.getShape(this);
    }

    @Nullable
    public HTShape getShapeOrNull() {
        return HTShape.getShapeOrNull(this);
    }

    //    Translation    //

    @NotNull
    @SideOnly(Side.CLIENT)
    public String getTranslatedName(HTMaterialKey materialKey) {
        return I18n.format("ht_shape." + name, materialKey.getTranslatedName());
    }

    @NotNull
    public ITextComponent getTranslatedText(HTMaterialKey materialKey) {
        return new TextComponentTranslation("ht_material." + name, materialKey.getTranslatedName());
    }

}