package io.github.hiiragi283.bwms.api.extension

import net.minecraft.core.item.IItemConvertible
import net.minecraft.core.item.Item
import net.minecraft.core.item.ItemStack
import java.util.function.Predicate

sealed class HTItemWithMeta : Predicate<ItemStack> {
	abstract val item: Item
	abstract val meta: Int

	val isEmpty: Boolean
		get() = this == Empty
	val isNotEmpty: Boolean
		get() = !isEmpty

	@JvmOverloads
	fun getStack(count: Int = 1): ItemStack = ItemStack(item, count, meta)

	override fun test(stack: ItemStack): Boolean = when {
		stack.isEmpty -> this == Empty
		stack.isWild -> this.item == stack.item
		else -> this.item == stack.item && this.meta == stack.metadata
	}

	companion object {
		@JvmStatic
		fun of(itemConvertible: IItemConvertible, meta: Int = 0): HTItemWithMeta =
			itemConvertible.asItem()?.let { Impl(it, meta) } ?: Empty

		@JvmStatic
		fun ofStack(stack: ItemStack): HTItemWithMeta {
			if (stack.isEmpty) return Empty
			val meta: Int = stack.metadata
			check(stack.isNotWild) {
				"Metadata of stack; $stack is out of range"
			}
			return stack.item?.let { Impl(it, meta) } ?: Empty
		}
	}

	data object Empty : HTItemWithMeta() {
		override val item: Item
			get() = throw AssertionError("Empty")
		override val meta: Int
			get() = throw AssertionError("Empty")
	}

	private data class Impl(override val item: Item, override val meta: Int) : HTItemWithMeta()

}
