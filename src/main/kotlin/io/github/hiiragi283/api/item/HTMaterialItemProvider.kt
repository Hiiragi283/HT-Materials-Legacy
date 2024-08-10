package io.github.hiiragi283.api.item

import io.github.hiiragi283.api.HTMaterialsAPI
import io.github.hiiragi283.api.material.HTMaterialKey
import io.github.hiiragi283.api.property.HTPropertyHolder
import io.github.hiiragi283.api.shape.HTShape
import io.github.hiiragi283.api.shape.HTShapeKey
import net.minecraft.item.ItemStack

interface HTMaterialItemProvider : ItemProvider {

    val shapeKey: HTShapeKey

    val shape: HTShape
        get() = shapeKey.get()

    val allowedKeys: List<HTMaterialKey>

    val allowedMaterials: List<HTPropertyHolder>
        get() = allowedKeys.mapNotNull(HTMaterialsAPI.instance.materialRegistry::get)

    val allowedIndexes: List<Int>
        get() = allowedKeys.mapNotNull(HTMaterialsAPI.instance.materialRegistry::getIndex)

    val allowedStacks: List<ItemStack>
        get() = allowedIndexes.map { asItemStack(1, it) }

    fun getMaterial(stack: ItemStack): HTPropertyHolder? = HTMaterialsAPI.instance.materialRegistry[stack.metadata]

    fun getMaterialKey(stack: ItemStack): HTMaterialKey? =
        HTMaterialsAPI.instance.materialRegistry.getKey(stack.metadata)

    fun getOreDict(stack: ItemStack): String? = getMaterialKey(stack)?.let(shapeKey::getOreDict)

}