package io.github.hiiragi283.bwms.api.extension

import net.minecraft.core.entity.player.EntityPlayer
import net.minecraft.core.item.ItemStack
import net.minecraft.core.player.inventory.IInventory
import net.minecraft.core.player.inventory.InventorySorter

class HTInventory(private val title: String, size: Int, initStack: ItemStack) : IInventory {

	private val delegate: Array<ItemStack> = Array(size) { initStack }

	override fun getSizeInventory(): Int = delegate.size

	override fun getStackInSlot(i: Int): ItemStack? = delegate.getOrNull(i)?.takeIf(ItemStack::isNotEmpty)

	override fun decrStackSize(i: Int, j: Int): ItemStack? = delegate.getOrNull(i)?.let {
		if (it.stackSize <= j) {
			val stackIn = it
			delegate[i] = ItemStack.NO_ITEM
			stackIn
		} else {
			val stackIn = it.splitStack(j)
			if (it.stackSize <= 0) {
				delegate[i] = ItemStack.NO_ITEM
			}
			stackIn
		}
	}

	override fun setInventorySlotContents(i: Int, itemStack: ItemStack) {
		delegate[i] = itemStack
	}

	override fun getInvName(): String = title

	override fun getInventoryStackLimit(): Int = 64

	override fun onInventoryChanged() {

	}

	override fun canInteractWith(entityPlayer: EntityPlayer): Boolean = true

	override fun sortInventory() {
		InventorySorter.sortInventory(delegate)
	}
}
