package io.github.hiiragi283.api.material

import com.google.common.collect.BiMap
import com.google.common.collect.ImmutableBiMap
import com.google.common.collect.ImmutableMap
import io.github.hiiragi283.api.material.property.HTMaterialProperties
import io.github.hiiragi283.api.property.HTPropertyHolder

class HTMaterialRegistry private constructor(
    private val keyToMaterial: Map<HTMaterialKey, HTPropertyHolder>,
    private val indexToMaterial: Map<Int, HTPropertyHolder>,
    private val keyToIndex: BiMap<HTMaterialKey, Int>
) {

    companion object {
        @JvmField
        val EMPTY = HTMaterialRegistry(emptyMap(), emptyMap(), ImmutableBiMap.of())

        @JvmStatic
        fun create(builderAction: Builder.() -> Unit): HTMaterialRegistry {
            val builderMap: MutableMap<HTMaterialKey, HTMaterialBuilder> = mutableMapOf()
            Builder(builderMap).builderAction()
            val keyToMaterial: ImmutableMap.Builder<HTMaterialKey, HTPropertyHolder> = ImmutableMap.builder()
            val indexToMaterial: ImmutableMap.Builder<Int, HTPropertyHolder> = ImmutableMap.builder()
            val keyToIndex: ImmutableBiMap.Builder<HTMaterialKey, Int> = ImmutableBiMap.builder()
            builderMap.forEach { (key: HTMaterialKey, builder: HTMaterialBuilder) ->
                val material: HTPropertyHolder = builder.build()
                // key -> material
                keyToMaterial.put(key, material)
                material[HTMaterialProperties.INDEX]?.let { index ->
                    // index -> material
                    indexToMaterial.put(index, material)
                    // key <-> index
                    keyToIndex.put(key, index)
                }
            }
            return HTMaterialRegistry(
                keyToMaterial.build(),
                indexToMaterial.build(),
                keyToIndex.build()
            )
        }
    }

    val keys: Set<HTMaterialKey>
        get() = keyToMaterial.keys

    val properties: Collection<HTPropertyHolder>
        get() = keyToMaterial.values

    val indexes: Set<Int>
        get() = indexToMaterial.keys

    operator fun contains(key: HTMaterialKey): Boolean = key in keyToMaterial

    operator fun contains(index: Int): Boolean = index in indexToMaterial

    operator fun get(key: HTMaterialKey): HTPropertyHolder? = keyToMaterial[key]

    operator fun get(index: Int): HTPropertyHolder? = indexToMaterial[index]

    fun getOrEmpty(key: HTMaterialKey): HTPropertyHolder = get(key) ?: HTPropertyHolder.EMPTY

    fun getOrEmpty(index: Int): HTPropertyHolder = get(index) ?: HTPropertyHolder.EMPTY

    fun getIndex(key: HTMaterialKey): Int? = keyToIndex[key]

    fun getKey(index: Int): HTMaterialKey? = keyToIndex.inverse()[index]

    fun forEach(action: (HTMaterialKey, HTPropertyHolder) -> Unit) {
        keyToMaterial.forEach(action)
    }

    fun forEachWithIndex(action: (HTMaterialKey, Int?, HTPropertyHolder) -> Unit) {
        forEach { key, material ->
            action(key, material[HTMaterialProperties.INDEX], material)
        }
    }

    //    Builder    //

    class Builder(private val map: MutableMap<HTMaterialKey, HTMaterialBuilder>) {
        fun create(key: HTMaterialKey, type: HTMaterialType): HTMaterialBuilder {
            check(key !in map) { "Material builder; ${key.name} is already created!" }
            return map
                .computeIfAbsent(key) { HTMaterialBuilder(it, type) }
                .apply { key.validated = true }
        }

        fun createGas(key: HTMaterialKey) = create(key, HTMaterialType.Fluid.GAS)

        fun createGem(key: HTMaterialKey, gemType: HTMaterialType.Gem) = create(key, gemType)

        fun createLiquid(key: HTMaterialKey) = create(key, HTMaterialType.Fluid.LIQUID)

        fun createMetal(key: HTMaterialKey, isShiny: Boolean = true) =
            create(key, HTMaterialType.Metal.fromBoolean(isShiny))

        fun createSolid(key: HTMaterialKey) = create(key, HTMaterialType.Solid)

        fun createStone(key: HTMaterialKey) = create(key, HTMaterialType.Solid)

        fun createWood(key: HTMaterialKey) = create(key, HTMaterialType.Wood)

        fun getBuilder(key: HTMaterialKey): HTMaterialBuilder? = map[key]
    }

}