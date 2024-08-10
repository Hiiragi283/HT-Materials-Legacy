package io.github.hiiragi283.bwms.api.material

import io.github.hiiragi283.bwms.api.BWMsAPI
import io.github.hiiragi283.bwms.api.HTLogger
import io.github.hiiragi283.bwms.api.extension.checkNotNull
import io.github.hiiragi283.bwms.api.property.HTPropertyHolder
import net.minecraft.client.lang.text.Text
import net.minecraft.core.lang.I18n
import java.util.concurrent.ConcurrentHashMap
import java.util.function.Supplier

class HTMaterialKey private constructor(val name: String) : Supplier<HTPropertyHolder> {
	companion object {
		private val INSTANCES: MutableMap<String, HTMaterialKey> = ConcurrentHashMap()

		@JvmStatic
		fun of(name: String): HTMaterialKey = INSTANCES.computeIfAbsent(name, ::HTMaterialKey)
	}

	// val oreDictName: String = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, name)

	//    Supplier    //

	var validated: Boolean = false
		internal set(value) {
			if (validated) return
			HTLogger.log { it.info("Material; $name validated!") }
			field = value
		}

	fun checkValidation(): HTMaterialKey = apply {
		check(validated) { "Material; $name is not registered!" }
	}

	private var cache: HTPropertyHolder? = null

	override fun get(): HTPropertyHolder {
		checkValidation()
		if (cache == null) {
			cache = BWMsAPI.instance.materialRegistry[this]
		}
		return cache.checkNotNull { "Material; $name is not registered!" }
	}

	//    ResourceLocation    //

	// fun getId(namespace: String = HTMaterialsAPI.MOD_ID): ResourceLocation = ResourceLocation(namespace, name)

	//    Translation    //

	val translationKey: String = "ht_material.$name"

	val translatedName: String
		get() = I18n.getInstance().translateKey(translationKey)

	val translatedText: Text
		get() = Text.text().trans(translationKey)

	//    Any    //

	override fun equals(other: Any?): Boolean = (other as? HTMaterialKey)?.name == this.name

	override fun hashCode(): Int = name.hashCode()

	override fun toString(): String = name
}
