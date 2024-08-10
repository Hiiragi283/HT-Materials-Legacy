package io.github.hiiragi283.bwms.compat

import io.github.hiiragi283.bwms.api.BWMsAPI
import io.github.hiiragi283.bwms.api.BWMsPlugin
import io.github.hiiragi283.bwms.api.extension.HTColor
import io.github.hiiragi283.bwms.api.extension.averageColor
import io.github.hiiragi283.bwms.api.item.HTMaterialItemConvertible
import io.github.hiiragi283.bwms.api.material.HTMaterialKey
import io.github.hiiragi283.bwms.api.material.HTMaterialKeys
import io.github.hiiragi283.bwms.api.material.HTMaterialRegistry
import io.github.hiiragi283.bwms.api.material.HTMaterialType
import io.github.hiiragi283.bwms.api.material.composition.HTElements
import io.github.hiiragi283.bwms.api.part.HTPartManager
import io.github.hiiragi283.bwms.api.shape.HTShapeKeys
import io.github.hiiragi283.bwms.api.shape.HTShapeRegistry
import net.minecraft.core.item.ItemStack
import java.awt.Color

object HTDefaultMaterials : BWMsPlugin {
	override val modId: String = BWMsAPI.MOD_ID
	override val priority: Int = -100

	override fun registerShape(builder: HTShapeRegistry.Builder) {
		// Block
		builder.createBlockShape(HTShapeKeys.STORAGE_BLOCK)
		builder.createBlockShape(HTShapeKeys.SLAB)
		builder.createBlockShape(HTShapeKeys.STAIR)
		builder.createBlockShape(HTShapeKeys.BRICKS)
		builder.createBlockShape(HTShapeKeys.ORE)
		builder.createBlockShape(HTShapeKeys.ORE_BASALT)
		builder.createBlockShape(HTShapeKeys.ORE_LIMESTONE)
		builder.createBlockShape(HTShapeKeys.ORE_GRANITE)
		builder.createBlockShape(HTShapeKeys.ORE_GRAVEL)
		builder.createBlockShape(HTShapeKeys.ORE_SAND)
		builder.createBlockShape(HTShapeKeys.LOG)
		builder.createBlockShape(HTShapeKeys.PLANKS)
		builder.createBlockShape(HTShapeKeys.FENCE)
		builder.createBlockShape(HTShapeKeys.FENCE_GATE)
		builder.createBlockShape(HTShapeKeys.STONE)
		builder.createBlockShape(HTShapeKeys.COBBLESTONE)
		// Item
		builder.createItemShape(HTShapeKeys.DUST)
		builder.createItemShape(HTShapeKeys.GEAR)
		builder.createItemShape(HTShapeKeys.GEM)
			.addBlacklist(
				HTMaterialKeys.LAPIS,
				HTMaterialKeys.DIAMOND,
				HTMaterialKeys.QUARTZ,
				HTMaterialKeys.OLIVINE,
				HTMaterialKeys.FLINT
			)
		builder.createItemShape(HTShapeKeys.INGOT)
			.addBlacklist(
				HTMaterialKeys.IRON,
				HTMaterialKeys.GOLD,
				HTMaterialKeys.STEEL
			)
		// builder.createItemShape(HTShapeKeys.NUGGET)
		builder.createItemShape(HTShapeKeys.PLATE)
		builder.createItemShape(HTShapeKeys.ROD)
		builder.createItemShape(HTShapeKeys.SEED)
		builder.createItemShape(HTShapeKeys.CROP)
		builder.createItemShape(HTShapeKeys.RAW_MEAT)
		builder.createItemShape(HTShapeKeys.COOKED_MEAT)
	}

	override fun registerMaterial(builder: HTMaterialRegistry.Builder) {
		// H
		builder.createGas(HTMaterialKeys.HYDROGEN)
			.index(1)
			.molecular(HTElements.H to 2)
		builder.createLiquid(HTMaterialKeys.WATER)
			.index(10100)
			.molecular(HTElements.WATER to 1)
			.color(HTColor.BLUE)
		builder.createSolid(HTMaterialKeys.SNOW)
			.index(10101)
			.molecular(HTElements.WATER to 1)
			.color(HTColor.WHITE)
		builder.createSolid(HTMaterialKeys.ICE)
			.index(10102)
			.molecular(HTElements.WATER to 1)
			.color(averageColor(HTColor.AQUA, HTColor.WHITE))
		// He
		// Li
		// Be
		// B
		// C
		builder.createSolid(HTMaterialKeys.CARBON)
			.index(6)
			.color(averageColor(HTColor.BLACK, HTColor.GRAY))
		builder.createSolid(HTMaterialKeys.CHARCOAL)
			.index(10600)
			.mixture(HTElements.C)
			.color(averageColor(HTColor.BLACK to 7, HTColor.YELLOW to 1))
		builder.createMetal(HTMaterialKeys.COAL)
			.index(10601)
			.mixture(HTElements.C)
		builder.createGem(HTMaterialKeys.DIAMOND, HTMaterialType.Gem.DIAMOND)
			.index(10602)
			.molecular(HTElements.C to 1)
			.color(HTColor.AQUA)
		builder.createGem(HTMaterialKeys.NETHER_COAL, HTMaterialType.Gem.COAL)
			.index(10603)
			.color(averageColor(HTColor.BLACK, HTColor.DARK_RED))
		builder.createSolid(HTMaterialKeys.PAPER)
			.index(10604)
			.mixture(HTElements.C, HTElements.H, HTElements.O)
		builder.createSolid(HTMaterialKeys.SUGAR)
			.index(10605)
			.molecular(HTElements.C to 6, HTElements.H to 12, HTElements.O to 6)
			.color(HTColor.WHITE)
		builder.createWood(HTMaterialKeys.WOOD)
			.index(10606)
			.mixture(HTElements.SiO2)
			.color(averageColor(HTColor.DARK_GRAY to 2, HTColor.RED to 1, HTColor.YELLOW to 1))
		builder.createSolid(HTMaterialKeys.WOOL)
			.index(10607)
			.mixture(HTElements.C, HTElements.H, HTElements.N, HTElements.O)
		// N
		builder.createGas(HTMaterialKeys.NITROGEN)
			.index(7)
			.molecular(HTElements.N to 2)
		// O
		// F
		// Ne
		// Na
		// Mg
		builder.createMetal(HTMaterialKeys.MAGNESIUM, false)
			.index(12)
			.molecular(HTElements.Mg to 1)
		builder.createGem(HTMaterialKeys.OLIVINE, HTMaterialType.Gem.QUARTZ)
			.index(11200)
			.molecular(HTElements.Mg to 2, HTElements.SiO2 to 1)
			.color(HTColor.GREEN)
		// Al
		builder.createMetal(HTMaterialKeys.ALUMINIUM, false)
			.index(13)
			.molecular(HTElements.Al to 1)
		builder.createSolid(HTMaterialKeys.CLAY)
			.index(11300)
			.mixture(HTElements.Al2O3, HTElements.SiO2)
			.color(Color(0xa4a8b8))
		builder.createGem(HTMaterialKeys.LAPIS, HTMaterialType.Gem.LAPIS)
			.index(11301)
			.color(HTColor.BLUE)
		builder.createSolid(HTMaterialKeys.TERRACOTTA)
			.index(11302)
			.mixture(HTElements.Al2O3, HTElements.SiO2)
		// Si
		builder.createMetal(HTMaterialKeys.SILICON, true)
			.index(14)
			.molecular(HTElements.Si to 1)
		builder.createGem(HTMaterialKeys.FLINT, HTMaterialType.Gem.FLINT)
			.index(11400)
			.mixture(HTElements.SiO2)
			.color(averageColor(HTColor.BLACK, HTColor.GRAY))
		builder.createSolid(HTMaterialKeys.GLASS)
			.index(11401)
			.mixture(HTElements.SiO2)
			.color(HTColor.WHITE)
		builder.createSolid(HTMaterialKeys.GRAVEL)
			.index(11402)
			.mixture(HTElements.SiO2)
			.color(HTColor.DARK_GRAY)
		builder.createLiquid(HTMaterialKeys.LAVA)
			.index(11403)
			.mixture(HTElements.SiO2)
			.color(averageColor(HTColor.RED, HTColor.GOLD))
		builder.createGem(HTMaterialKeys.QUARTZ, HTMaterialType.Gem.QUARTZ)
			.index(11404)
			.molecular(HTElements.SiO2 to 1)
			.color(averageColor(HTColor.RED, HTColor.WHITE))
		builder.createSolid(HTMaterialKeys.SAND)
			.index(11405)
			.mixture(HTElements.SiO2)
			.color(averageColor(HTColor.GOLD to 1, HTColor.YELLOW to 1, HTColor.WHITE to 4))
		builder.createSolid(HTMaterialKeys.SOUL_SAND)
			.index(11406)
			.mixture(HTElements.SiO2)
		// Si - Stone
		builder.createStone(HTMaterialKeys.STONE)
			.index(11407)
			.mixture(HTElements.SiO2)
			.color(HTColor.DARK_GRAY)
		builder.createStone(HTMaterialKeys.BASALT)
			.index(11408)
			.mixture(HTElements.SiO2)
			.color(averageColor(HTColor.BLACK, HTColor.GRAY))
		builder.createStone(HTMaterialKeys.LIMESTONE)
			.index(11409)
			.molecular(HTElements.Ca to 1, HTElements.CO3 to 1)
			.color(averageColor(HTColor.GRAY, HTColor.YELLOW, HTColor.WHITE))
		builder.createStone(HTMaterialKeys.GRANITE)
			.index(11410)
			.mixture(HTElements.SiO2)
			.color(averageColor(HTColor.GOLD, HTColor.WHITE))
		builder.createStone(HTMaterialKeys.MARBLE)
			.index(11411)
			.molecular(HTElements.Ca to 1, HTElements.CO3 to 1)
			.color(HTColor.WHITE)
		builder.createStone(HTMaterialKeys.SLATE)
			.index(11412)
			.mixture(HTElements.SiO2)
			.color(averageColor(HTColor.DARK_GRAY to 3, HTColor.BLUE to 1))
		builder.createStone(HTMaterialKeys.PERMAFROST)
			.index(11413)
			.color(averageColor(HTColor.AQUA to 1, HTColor.WHITE to 3))
		builder.createStone(HTMaterialKeys.SANDSTONE)
			.index(11414)
			.mixture(HTElements.SiO2)
			.color(averageColor(HTColor.GOLD to 1, HTColor.YELLOW to 1, HTColor.WHITE to 4))
		builder.createStone(HTMaterialKeys.OBSIDIAN)
			.index(11415)
			.color(
				averageColor(
					HTColor.BLACK to 4,
					HTColor.DARK_BLUE to 2,
					HTColor.DARK_RED to 1,
					HTColor.WHITE to 1,
				),
			)
		builder.createStone(HTMaterialKeys.BEDROCK)
			.index(11416)
			.color(averageColor(HTColor.BLACK, HTColor.DARK_GRAY))
		builder.createStone(HTMaterialKeys.NETHERRACK)
			.index(11417)
			.mixture(HTElements.SiO2, HTElements.S, HTElements.P)
		// P
		// S
		builder.createSolid(HTMaterialKeys.SULFUR)
			.index(16)
			.molecular(HTElements.S to 8)
		builder.createSolid(HTMaterialKeys.GUNPOWDER)
			.index(11600)
			.mixture(HTElements.C, HTElements.N, HTElements.S)
			.color(HTColor.DARK_GRAY)
		// Cl
		// Kr
		// K
		// Ca
		builder.createSolid(HTMaterialKeys.BONE)
			.index(12000)
			.molecular(HTElements.Ca to 5, HTElements.PO4.bracket() to 3, HTElements.OH to 1)
			.color(HTColor.WHITE)
		// Fe
		builder.createMetal(HTMaterialKeys.IRON)
			.index(26)
			.molecular(HTElements.F to 1)
		builder.createMetal(HTMaterialKeys.STEEL, true)
			.index(12600)
			.mixture(HTElements.Fe, HTElements.C)
			.color(HTColor.DARK_GRAY)
		// Au
		builder.createMetal(HTMaterialKeys.GOLD)
			.index(79)
			.molecular(HTElements.Au to 1)

		// Foods
		builder.createCrop(HTMaterialKeys.APPLE)
			.index(1000)
			.color(HTColor.RED)
		builder.createCrop(HTMaterialKeys.CACAO)
			.index(1001)
		builder.createCrop(HTMaterialKeys.CHERRY)
			.index(1002)
			.color(HTColor.RED)
		builder.createMeat(HTMaterialKeys.FISH)
			.index(1003)
			.color(HTColor.AQUA)
		builder.createMeat(HTMaterialKeys.PORK)
			.index(1004)
			.color(averageColor(HTColor.RED, HTColor.WHITE))
		builder.createCrop(HTMaterialKeys.PUMPKIN)
			.index(1005)
			.color(averageColor(HTColor.RED, HTColor.YELLOW))
		builder.createCrop(HTMaterialKeys.WHEAT)
			.index(1006)
			.color(HTColor.YELLOW)
			.addItem(HTShapeKeys.DUST)
		// Other
		builder.createSolid(HTMaterialKeys.DIRT)
			.index(9000)
		builder.createSolid(HTMaterialKeys.GLOWSTONE)
			.index(9001)
			.color(averageColor(HTColor.GOLD, HTColor.YELLOW))
		builder.createSolid(HTMaterialKeys.MUD)
			.index(9002)
		builder.createSolid(HTMaterialKeys.SPONGE)
			.index(9003)
			.color(HTColor.YELLOW)
		builder.createSolid(HTMaterialKeys.REDSTONE)
			.index(9004)
			.color(averageColor(HTColor.DARK_RED, HTColor.RED))
		builder.createSolid(HTMaterialKeys.SLIME)
			.index(9005)
			.color(averageColor(HTColor.GREEN, HTColor.WHITE))
	}

	override fun bindItemWithPart(builder: HTPartManager.Builder) {
		BWMsAPI.instance.materialItemMap.values.forEach { item: HTMaterialItemConvertible ->
			item.entryList.forEach { entry ->
				val stack = ItemStack(item, 1, entry.index)
				item.getMaterialKey(stack)?.let { key: HTMaterialKey ->
					builder.add(item, stack.metadata, key, item.shapeKey)
				}
			}
		}
	}
}
