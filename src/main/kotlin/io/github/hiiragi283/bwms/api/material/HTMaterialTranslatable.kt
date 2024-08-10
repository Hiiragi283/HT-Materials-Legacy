package io.github.hiiragi283.bwms.api.material


import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.client.lang.text.Text
import net.minecraft.core.lang.I18n

interface HTMaterialTranslatable {
	val translationKey: String

	fun getTranslatedName(materialKey: HTMaterialKey): String =
		I18n.getInstance().translateKeyAndFormat(translationKey, materialKey.translatedName)

	@Environment(EnvType.CLIENT)
	fun getTranslatedText(materialKey: HTMaterialKey): Text =
		Text.text().trans(translationKey).lit(materialKey.translatedName)
}
