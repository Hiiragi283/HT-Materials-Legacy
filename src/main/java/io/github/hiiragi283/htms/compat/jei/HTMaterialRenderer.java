package io.github.hiiragi283.htms.compat.jei;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import io.github.hiiragi283.htms.api.extension.HTColorUtil;
import io.github.hiiragi283.htms.api.material.HTMaterial;
import mezz.jei.api.ingredients.IIngredientRenderer;

public enum HTMaterialRenderer implements IIngredientRenderer<@NotNull HTMaterial> {

    INSTANCE;

    @Override
    public void render(@NotNull Minecraft minecraft, int xPosition, int yPosition, @Nullable HTMaterial ingredient) {
        GlStateManager.enableBlend();
        GlStateManager.enableAlpha();

        drawMaterial(minecraft, xPosition, yPosition, ingredient);
        GlStateManager.color(1, 1, 1, 1);

        GlStateManager.disableAlpha();
        GlStateManager.disableBlend();
    }

    private void drawMaterial(@NotNull Minecraft minecraft, final int xPosition, final int yPosition,
                              @Nullable HTMaterial material) {
        if (material == null) return;
        minecraft.renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
        HTColorUtil.setGLColor(material.color);
        drawTiledSprite(xPosition, yPosition, getTexture(minecraft));
    }

    private static @NotNull TextureAtlasSprite getTexture(@NotNull Minecraft minecraft) {
        TextureMap textureMap = minecraft.getTextureMapBlocks();
        ResourceLocation location = new ResourceLocation("blocks/concrete_white");
        TextureAtlasSprite sprite = textureMap.getTextureExtry(location.toString());
        return sprite == null ? textureMap.getMissingSprite() : sprite;
    }

    private static void drawTiledSprite(final int xPosition, final int yPosition, @NotNull TextureAtlasSprite sprite) {
        // TextureAtlasSpriteのx座標の左端と右端，y座標の下端と上端をDoubleに変換する
        double uMin = sprite.getMinU();
        double uMax = sprite.getMaxU();
        double vMin = sprite.getMinV();
        double vMax = sprite.getMaxV();
        // GUiは2次元なのでz座標は適当?
        double z = 100.0;
        // Tessellatorに設定を書き込んでいく
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder vertexBuffer = tessellator.getBuffer();
        vertexBuffer.begin(7, DefaultVertexFormats.POSITION_TEX);
        vertexBuffer.pos(xPosition, yPosition + 16, z).tex(uMin, vMax).endVertex(); // 左下
        vertexBuffer.pos(xPosition + 16, yPosition + 16, z).tex(uMax, vMax).endVertex(); // 右下
        vertexBuffer.pos(xPosition + 16, yPosition, z).tex(uMax, vMin).endVertex(); // 左上
        vertexBuffer.pos(xPosition, yPosition, z).tex(uMin, vMin).endVertex(); // 右上
        // いざ描画!!
        tessellator.draw();
    }

    @Override
    public @NotNull List<@NotNull String> getTooltip(@NotNull Minecraft minecraft, @NotNull HTMaterial ingredient,
                                                     @NotNull ITooltipFlag tooltipFlag) {
        List<@NotNull String> tooltips = new ArrayList<>();
        HTMaterial.TooltipContext context = new HTMaterial.TooltipContext(ingredient, null, ItemStack.EMPTY, tooltips);
        HTMaterial.addInformation(context);
        return tooltips;
    }
}
