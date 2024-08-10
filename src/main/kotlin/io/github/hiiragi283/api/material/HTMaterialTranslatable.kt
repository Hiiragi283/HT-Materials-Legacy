package io.github.hiiragi283.api.material

import net.minecraft.client.resources.I18n
import net.minecraft.util.text.TextComponentTranslation
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

interface HTMaterialTranslatable {
    val translationKey: String

    @SideOnly(Side.CLIENT)
    fun getTranslatedName(materialKey: HTMaterialKey): String = I18n.format(translationKey, materialKey.translatedName)

    fun getTranslatedText(materialKey: HTMaterialKey): TextComponentTranslation =
        TextComponentTranslation(translationKey, materialKey.translatedName)
}