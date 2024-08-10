package io.github.hiiragi283.bwms.api.extension

import net.minecraft.core.block.Block
import net.minecraft.core.block.entity.TileEntity
import net.minecraft.core.block.material.Material
import net.minecraft.core.world.WorldSource

object DummyWorld : WorldSource {
	override fun getBlockId(i: Int, j: Int, k: Int): Int = 0

	override fun getBlock(i: Int, j: Int, k: Int): Block? = null

	override fun getBlockTileEntity(i: Int, j: Int, k: Int): TileEntity? = null

	override fun getBrightness(i: Int, j: Int, k: Int, l: Int): Float = 0.0f

	override fun getLightBrightness(i: Int, j: Int, k: Int): Float = 0.0f

	override fun getBlockMetadata(i: Int, j: Int, k: Int): Int = 0

	override fun getBlockMaterial(i: Int, j: Int, k: Int): Material = Material.air

	override fun isBlockOpaqueCube(i: Int, j: Int, k: Int): Boolean = false

	override fun isBlockNormalCube(i: Int, j: Int, k: Int): Boolean = false
}
