package io.github.hiiragi283.api.item

import io.github.hiiragi283.api.HTMaterialsAPI
import io.github.hiiragi283.api.material.HTMaterialKey
import io.github.hiiragi283.api.material.property.HTMaterialProperties
import io.github.hiiragi283.api.shape.HTShapeKey
import net.minecraft.block.BlockDispenser
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.dispenser.IBehaviorDispenseItem
import net.minecraft.dispenser.IBlockSource
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.NonNullList

class HTMaterialItem(
    override val shapeKey: HTShapeKey,
    override val allowedKeys: List<HTMaterialKey>
) : Item(), HTMaterialItemProvider {

    init {
        creativeTab = HTMaterialCreativeTabs
        hasSubtypes = true
        translationKey = shapeKey.translationKey
        registryName = HTMaterialsAPI.id(shapeKey.name)
        BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(this, DispenseBehavior)
    }

    /*@SideOnly(Side.CLIENT)
    override fun addInformation(stack: ItemStack, worldIn: World?, tooltip: MutableList<String>, flagIn: ITooltipFlag) {
        val key: HTMaterialKey = getMaterialKey(stack) ?: return
        val material: HTPropertyHolder = getMaterial(stack) ?: return
        HTMaterialTooltipContext(key, material, shapeKey, stack).appendTooltips(tooltip)
    }*/

    override fun getItemStackDisplayName(stack: ItemStack): String =
        getMaterialKey(stack)
            ?.let { shapeKey.getTranslatedName(it) }
            ?: super.getItemStackDisplayName(stack)

    override fun getSubItems(tab: CreativeTabs, items: NonNullList<ItemStack>) {
        if (!isInCreativeTab(tab)) return
        allowedStacks.forEach(items::add)
    }

    override fun asItem(): Item = this

    private object DispenseBehavior : IBehaviorDispenseItem {
        override fun dispense(source: IBlockSource, stack: ItemStack): ItemStack {
            HTMaterialsAPI.instance.partManager[stack]?.let { (materialKey: HTMaterialKey, shapeKey: HTShapeKey) ->
                materialKey.get()[HTMaterialProperties.DISPENSE_BEHAVIOR]?.let { behavior ->
                    return behavior.dispense(source, stack, shapeKey)
                }
            }
            return stack
        }
    }

}