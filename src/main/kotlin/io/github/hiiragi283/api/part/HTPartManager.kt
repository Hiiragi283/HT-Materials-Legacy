package io.github.hiiragi283.api.part

import com.google.common.collect.ImmutableMap
import com.google.common.collect.ImmutableTable
import com.google.common.collect.Table
import io.github.hiiragi283.api.HTMaterialsAPI
import io.github.hiiragi283.api.extension.ItemWithMeta
import io.github.hiiragi283.api.material.HTMaterialKey
import io.github.hiiragi283.api.shape.HTShapeKey
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent
import net.minecraftforge.oredict.OreDictionary

class HTPartManager(
    private val itemToShaped: Map<ItemWithMeta, HTPart>,
    private val shapedToItems: Table<HTMaterialKey, HTShapeKey, Set<ItemWithMeta>>,
) {
    companion object {
        @JvmField
        val EMPTY = HTPartManager(mapOf(), ImmutableTable.of())

        @JvmStatic
        fun create(event: FMLLoadCompleteEvent): HTPartManager {
            val itemToShaped: ImmutableMap.Builder<ItemWithMeta, HTPart> = ImmutableMap.builder()
            val shapedToItems: ImmutableTable.Builder<HTMaterialKey, HTShapeKey, Set<ItemWithMeta>> =
                ImmutableTable.builder()
            HTMaterialsAPI.instance.forEachPart { part: HTPart ->
                val items: List<ItemWithMeta> = OreDictionary.getOres(part.oreDictName)
                    .flatMap(ItemWithMeta.Companion::ofStacks)
                items.forEach { itemWithMeta: ItemWithMeta -> itemToShaped.put(itemWithMeta, part) }
                shapedToItems.put(part.materialKey, part.shapeKey, items.toSet())
            }
            return HTPartManager(itemToShaped.build(), shapedToItems.build())
        }
    }

    //    Item -> HTShapedMaterial    //

    operator fun get(stack: ItemStack): HTPart? = get(ItemWithMeta.ofStack(stack))

    operator fun get(item: Item): HTPart? = get(ItemWithMeta.ofItem(item))

    operator fun get(itemWithMeta: ItemWithMeta): HTPart? = itemToShaped[itemWithMeta]

    operator fun contains(stack: ItemStack): Boolean = contains(ItemWithMeta.ofStack(stack))

    operator fun contains(item: Item): Boolean = contains(ItemWithMeta.ofItem(item))

    operator fun contains(itemWithMeta: ItemWithMeta): Boolean = itemWithMeta in itemToShaped

    fun forEach(action: (ItemWithMeta, HTPart) -> Unit) {
        itemToShaped.forEach(action)
    }

    //    HTShapedMaterial -> Fluid    //

    fun convert(stack: ItemStack): ItemStack = convert(stack.item)?.getStack(stack.count) ?: stack

    fun convert(item: Item): ItemWithMeta? = convert(ItemWithMeta.ofItem(item))

    fun convert(itemWithMeta: ItemWithMeta): ItemWithMeta? = itemWithMeta
        .let(::get)
        ?.let(::getOrNull)

    fun convertItems(stack: ItemStack): Set<ItemWithMeta> = ItemWithMeta.ofStacks(stack)
        .mapNotNull(::get)
        .flatMap(::get)
        .toSet()

    fun convertItems(item: Item): Set<ItemWithMeta> = convertItems(ItemWithMeta.ofItem(item))

    fun convertItems(itemWithMeta: ItemWithMeta): Set<ItemWithMeta> = itemWithMeta
        .let(::get)
        ?.let(::get)
        ?: emptySet()

    fun getOrEmpty(part: HTPart): ItemWithMeta = getOrEmpty(part.materialKey, part.shapeKey)

    fun getOrEmpty(materialKey: HTMaterialKey, shapeKey: HTShapeKey): ItemWithMeta =
        getOrNull(materialKey, shapeKey) ?: ItemWithMeta.EMPTY

    fun getOrNull(part: HTPart): ItemWithMeta? = getOrNull(part.materialKey, part.shapeKey)

    fun getOrNull(materialKey: HTMaterialKey, shapeKey: HTShapeKey): ItemWithMeta? {
        val items: Set<ItemWithMeta> = shapedToItems[materialKey, shapeKey]
        return items.firstOrNull { it.item.registryName?.namespace == "minecraft" }
            ?: items.firstOrNull { it.item.registryName?.namespace == HTMaterialsAPI.MOD_ID }
            ?: items.firstOrNull()
    }

    //    HTShapedMaterial -> Set<Fluid>    //

    operator fun get(part: HTPart): Set<ItemWithMeta> = get(part.materialKey, part.shapeKey)

    operator fun get(materialKey: HTMaterialKey, shapeKey: HTShapeKey): Set<ItemWithMeta> =
        shapedToItems.get(materialKey, shapeKey) ?: emptySet()

    operator fun contains(part: HTPart): Boolean = contains(part.materialKey, part.shapeKey)

    fun contains(materialKey: HTMaterialKey, shapeKey: HTShapeKey): Boolean =
        shapedToItems.contains(materialKey, shapeKey)
}