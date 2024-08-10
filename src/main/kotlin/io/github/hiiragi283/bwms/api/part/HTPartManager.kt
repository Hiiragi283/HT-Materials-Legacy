package io.github.hiiragi283.bwms.api.part

import com.google.common.collect.HashBasedTable
import com.google.common.collect.Table
import io.github.hiiragi283.bwms.api.extension.HTItemWithMeta
import io.github.hiiragi283.bwms.api.extension.forEach
import io.github.hiiragi283.bwms.api.material.HTMaterialKey
import io.github.hiiragi283.bwms.api.shape.HTShapeKey
import net.minecraft.core.data.registry.Registries
import net.minecraft.core.item.IItemConvertible
import net.minecraft.core.item.Item
import net.minecraft.core.item.ItemStack

class HTPartManager(private val itemToShaped: Map<HTItemWithMeta, HTPart>) {
	companion object {
		@JvmField
		val EMPTY = HTPartManager(mapOf())

		@JvmStatic
		fun create(builderAction: Builder.() -> Unit): HTPartManager {
			val itemToShaped: MutableMap<HTItemWithMeta, HTPart> = mutableMapOf()
			val shapedToItems: Table<HTMaterialKey, HTShapeKey, MutableSet<HTItemWithMeta>> = HashBasedTable.create()
			Builder(itemToShaped, shapedToItems).builderAction()
			shapedToItems.forEach { materialKey: HTMaterialKey, shapeKey: HTShapeKey, set: Set<HTItemWithMeta> ->
				Registries.ITEM_GROUPS.register("${materialKey}:${shapeKey}", set.map(HTItemWithMeta::getStack))
			}
			return HTPartManager(itemToShaped)
		}
	}

	//    Item -> HTPart    //

	operator fun get(stack: ItemStack): HTPart? = get(HTItemWithMeta.ofStack(stack))

	operator fun get(item: Item): HTPart? = get(HTItemWithMeta.of(item))

	operator fun get(itemWithMeta: HTItemWithMeta): HTPart? = itemToShaped[itemWithMeta]

	operator fun contains(stack: ItemStack): Boolean = contains(HTItemWithMeta.ofStack(stack))

	operator fun contains(item: Item): Boolean = contains(HTItemWithMeta.of(item))

	operator fun contains(itemWithMeta: HTItemWithMeta): Boolean = itemWithMeta in itemToShaped

	fun forEach(action: (HTItemWithMeta, HTPart) -> Unit) {
		itemToShaped.forEach(action)
	}

	//    HTPart -> HTItemWithMeta    //

	fun convert(stack: ItemStack): ItemStack = convert(stack.item)?.apply { stackSize = stack.stackSize } ?: stack

	fun convert(item: Item): ItemStack? = convert(HTItemWithMeta.of(item))

	fun convert(itemWithMeta: HTItemWithMeta): ItemStack? = itemWithMeta
		.let(::get)
		?.let(::getFirstOrNull)

	fun convertItems(stack: ItemStack): List<ItemStack> = HTItemWithMeta.ofStack(stack)
		.let(::get)
		?.let(::getOrEmpty)
		?: emptyList()

	fun convertItems(item: Item): List<ItemStack> = convertItems(HTItemWithMeta.of(item))

	fun convertItems(itemWithMeta: HTItemWithMeta): List<ItemStack> = itemWithMeta
		.let(::get)
		?.let(::get)
		?: emptyList()

	fun getFirstOrNull(part: HTPart): ItemStack? = getFirstOrNull(part.materialKey, part.shapeKey)

	fun getFirstOrNull(materialKey: HTMaterialKey, shapeKey: HTShapeKey): ItemStack? =
		get(materialKey, shapeKey)?.firstOrNull()

	//    HTPart -> List<ItemStack>    //

	operator fun get(part: HTPart): List<ItemStack>? = Registries.ITEM_GROUPS.getItem(part.locationName)

	operator fun get(materialKey: HTMaterialKey, shapeKey: HTShapeKey): List<ItemStack>? =
		get(HTPart(materialKey, shapeKey))

	fun getOrEmpty(part: HTPart): List<ItemStack> = get(part) ?: emptyList()

	fun getOrEmpty(materialKey: HTMaterialKey, shapeKey: HTShapeKey): List<ItemStack> =
		get(materialKey, shapeKey) ?: emptyList()

	operator fun contains(part: HTPart): Boolean = get(part) != null

	fun contains(materialKey: HTMaterialKey, shapeKey: HTShapeKey): Boolean = contains(HTPart(materialKey, shapeKey))

	//    Builder    //

	class Builder(
		private val itemToShaped: MutableMap<HTItemWithMeta, HTPart>,
		private val shapedToItems: Table<HTMaterialKey, HTShapeKey, MutableSet<HTItemWithMeta>>
	) {

		fun addAll(itemGroup: String, materialKey: HTMaterialKey, shapeKey: HTShapeKey) {
			Registries.ITEM_GROUPS.getItem(itemGroup)?.forEach { add(it, materialKey, shapeKey) }
		}

		fun addAll(itemConvertible: IItemConvertible, maxMeta: Int, materialKey: HTMaterialKey, shapeKey: HTShapeKey) {
			(0..maxMeta).forEach { meta ->
				add(itemConvertible, meta, materialKey, shapeKey)
			}
		}

		fun add(itemConvertible: IItemConvertible, meta: Int, materialKey: HTMaterialKey, shapeKey: HTShapeKey) {
			add(HTItemWithMeta.of(itemConvertible, meta), HTPart(materialKey, shapeKey))
		}

		fun add(stack: ItemStack, materialKey: HTMaterialKey, shapeKey: HTShapeKey) {
			add(stack, HTPart(materialKey, shapeKey))
		}

		fun add(stack: ItemStack, part: HTPart) {
			add(HTItemWithMeta.ofStack(stack), part)
		}

		fun add(itemWithMeta: HTItemWithMeta, part: HTPart) {
			check(itemWithMeta.isNotEmpty)
			itemToShaped[itemWithMeta] = part
			var itemSet: MutableSet<HTItemWithMeta>? = shapedToItems.get(part.materialKey, part.shapeKey)
			if (itemSet == null) {
				val emptySet: MutableSet<HTItemWithMeta> = mutableSetOf()
				shapedToItems.put(part.materialKey, part.shapeKey, emptySet)
				itemSet = emptySet
			}
			itemSet.add(itemWithMeta)
		}

	}
}
