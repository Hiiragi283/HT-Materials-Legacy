package io.github.hiiragi283.api.shape

import com.google.common.base.CaseFormat
import io.github.hiiragi283.api.HTLogger
import io.github.hiiragi283.api.HTMaterialsAPI
import io.github.hiiragi283.api.extension.checkNotNull
import io.github.hiiragi283.api.material.HTMaterialKey
import io.github.hiiragi283.api.material.HTMaterialTranslatable
import java.util.concurrent.ConcurrentHashMap
import java.util.function.Supplier

class HTShapeKey private constructor(val name: String) : Supplier<HTShape>, HTMaterialTranslatable {
    companion object {
        private val INSTANCES: MutableMap<String, HTShapeKey> = ConcurrentHashMap()

        @JvmStatic
        fun of(name: String): HTShapeKey = INSTANCES.computeIfAbsent(name, ::HTShapeKey)
    }

    //    Supplier    //

    var validated: Boolean = false
        internal set(value) {
            if (validated) return
            HTLogger.log { it.info("Shape; $name validated!") }
            field = value
        }

    fun checkValidation(): HTShapeKey = apply {
        check(validated) { "Shape; $name is not registered!" }
    }

    private var cache: HTShape? = null

    override fun get(): HTShape {
        checkValidation()
        if (cache == null) {
            cache = HTMaterialsAPI.instance.shapeRegistry[this]
        }
        return cache.checkNotNull { "Shape; $name is not registered!" }
    }

    //    HTMaterialOreDictProvider    //

    val oreDictPrefix: String = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, name)

    fun getOreDict(materialKey: HTMaterialKey): String = getOreDict(materialKey.oreDictName)

    fun getOreDict(name: String): String = oreDictPrefix + name

    //    HTMaterialTranslatable    //

    override val translationKey: String = "ht_shape.$name"

    //    Any    //

    override fun equals(other: Any?): Boolean = (other as? HTShapeKey)?.name == this.name

    override fun hashCode(): Int = name.hashCode()

    override fun toString(): String = name
}