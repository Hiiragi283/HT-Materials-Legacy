package io.github.hiiragi283.api.extension

import net.minecraft.block.Block
import net.minecraft.init.Items
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import java.util.function.Predicate

class ItemWithMeta private constructor(val item: Item, val meta: Int) : Predicate<ItemStack> {

    @JvmOverloads
    fun getStack(count: Int = 1): ItemStack = ItemStack(item, count, meta)

    override fun test(stack: ItemStack): Boolean = when {
        stack.isEmpty -> this == EMPTY
        stack.isWild -> this.item == stack.item
        else -> this.item == stack.item && this.meta == stack.metadata
    }

    companion object {
        @JvmField
        val EMPTY = ItemWithMeta(Items.AIR, 0)

        @JvmStatic
        fun ofBlock(block: Block, meta: Int = 0): ItemWithMeta = ofItem(Item.getItemFromBlock(block), meta)

        @JvmStatic
        fun ofItem(item: Item, meta: Int = 0): ItemWithMeta = ofStack(ItemStack(item, 1, meta))

        @JvmStatic
        fun ofStack(stack: ItemStack): ItemWithMeta {
            if (stack.isEmpty) return EMPTY
            val item: Item = stack.item
            val meta: Int = stack.metadata
            check(stack.isNotWild) {
                "Could not accept wildcard metadata!"
            }
            return ItemWithMeta(item, meta)
        }

        @JvmStatic
        fun ofStacks(stack: ItemStack): List<ItemWithMeta> {
            if (stack.isEmpty) return emptyList()
            val item: Item = stack.item
            val meta: Int = stack.metadata
            return if (stack.isWild) {
                item.createSubItems().flatMap { stackIn ->
                    check(stack.isWild) { "Current stack is wild!; ${stackIn.parseString()}" }
                    ofStacks(stackIn)
                }
            } else {
                listOf(ItemWithMeta(item, meta))
            }
        }
    }

    //    Any    //

    override fun equals(other: Any?): Boolean = when (other) {
        null -> false
        is ItemWithMeta -> item == other.item && other.meta == meta
        else -> false
    }

    override fun hashCode(): Int = 31 * item.hashCode() + meta

    override fun toString(): String = "${item.registryName}:$meta"

}