package io.github.hiiragi283.api.material

import com.google.common.base.CaseFormat
import io.github.hiiragi283.api.HTLogger
import io.github.hiiragi283.api.HTMaterialsAPI
import io.github.hiiragi283.api.extension.checkNotNull
import io.github.hiiragi283.api.property.HTPropertyHolder
import net.minecraft.client.resources.I18n
import net.minecraft.util.ResourceLocation
import net.minecraft.util.text.TextComponentTranslation
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import java.util.concurrent.ConcurrentHashMap
import java.util.function.Supplier

class HTMaterialKey private constructor(val name: String) : Supplier<HTPropertyHolder> {
    companion object {
        private val INSTANCES: MutableMap<String, HTMaterialKey> = ConcurrentHashMap()

        @JvmStatic
        fun of(name: String): HTMaterialKey = INSTANCES.computeIfAbsent(name, ::HTMaterialKey)
    }

    val oreDictName: String = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, name)

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
            cache = HTMaterialsAPI.instance.materialRegistry[this]
        }
        return cache.checkNotNull { "Material; $name is not registered!" }
    }

    //    ResourceLocation    //

    fun getId(namespace: String = HTMaterialsAPI.MOD_ID): ResourceLocation = ResourceLocation(namespace, name)

    //    Translation    //

    val translationKey: String = "ht_material.$name"

    val translatedName: String
        @SideOnly(Side.CLIENT)
        get() = I18n.format(translationKey)

    val translatedText: TextComponentTranslation
        get() = TextComponentTranslation(translationKey)

    //    Any    //

    override fun equals(other: Any?): Boolean = (other as? HTMaterialKey)?.name == this.name

    override fun hashCode(): Int = name.hashCode()

    override fun toString(): String = name
}