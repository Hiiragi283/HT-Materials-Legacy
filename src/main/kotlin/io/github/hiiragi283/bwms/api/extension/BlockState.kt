package io.github.hiiragi283.bwms.api.extension

import net.minecraft.core.block.Block

sealed class BlockState {
	abstract val block: Block
	abstract val meta: Int

	companion object {
		@JvmStatic
		fun of(block: Block?, meta: Int): BlockState = when {
			block == null -> Empty
			block.id !in (0 until 16384) -> Empty
			meta !in (0..255) -> Empty
			else -> Impl(block, meta)
		}
	}

	data object Empty : BlockState() {
		override val block: Block
			get() = throw AssertionError("Empty")
		override val meta: Int
			get() = throw AssertionError("Empty")
	}

	private data class Impl(override val block: Block, override val meta: Int) : BlockState()
}
