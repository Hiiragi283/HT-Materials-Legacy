package io.github.hiiragi283.bwms.compat

import io.github.hiiragi283.bwms.api.BWMsPlugin
import io.github.hiiragi283.bwms.api.material.HTMaterialKeys
import io.github.hiiragi283.bwms.api.part.HTPartManager
import io.github.hiiragi283.bwms.api.shape.HTShapeKeys
import net.minecraft.core.block.Block
import net.minecraft.core.item.Item

object HTBTACompat : BWMsPlugin {
	override val modId: String = "minecraft"
	override val priority: Int = -100

	override fun bindItemWithPart(builder: HTPartManager.Builder) {
		builder.add(Block.stone, 0, HTMaterialKeys.STONE, HTShapeKeys.STONE)
		builder.add(Block.basalt, 0, HTMaterialKeys.BASALT, HTShapeKeys.STONE)
		builder.add(Block.limestone, 0, HTMaterialKeys.LIMESTONE, HTShapeKeys.STONE)
		builder.add(Block.granite, 0, HTMaterialKeys.GRANITE, HTShapeKeys.STONE)
		builder.add(Block.marble, 0, HTMaterialKeys.MARBLE, HTShapeKeys.STONE)
		builder.add(Block.slate, 0, HTMaterialKeys.SLATE, HTShapeKeys.STONE)
		builder.add(Block.permafrost, 0, HTMaterialKeys.PERMAFROST, HTShapeKeys.STONE)

		builder.add(Block.cobbleStone, 0, HTMaterialKeys.STONE, HTShapeKeys.COBBLESTONE)
		builder.add(Block.cobbleStoneMossy, 0, HTMaterialKeys.STONE, HTShapeKeys.COBBLESTONE)
		builder.add(Block.cobbleBasalt, 0, HTMaterialKeys.BASALT, HTShapeKeys.COBBLESTONE)
		builder.add(Block.cobbleLimestone, 0, HTMaterialKeys.LIMESTONE, HTShapeKeys.COBBLESTONE)
		builder.add(Block.cobbleGranite, 0, HTMaterialKeys.GRANITE, HTShapeKeys.COBBLESTONE)
		builder.add(Block.cobblePermafrost, 0, HTMaterialKeys.PERMAFROST, HTShapeKeys.COBBLESTONE)

		builder.add(Block.sandstone, 0, HTMaterialKeys.SANDSTONE, HTShapeKeys.STONE)

		builder.addAll("minecraft:planks", HTMaterialKeys.WOOD, HTShapeKeys.PLANKS)
		builder.add(Block.fencePlanksOak, 0, HTMaterialKeys.WOOD, HTShapeKeys.FENCE)
		builder.addAll(Block.fencePlanksOakPainted, 15, HTMaterialKeys.WOOD, HTShapeKeys.FENCE)
		builder.add(Block.fencegatePlanksOak, 0, HTMaterialKeys.WOOD, HTShapeKeys.FENCE_GATE)
		builder.addAll(Block.fencegatePlanksOakPainted, 256, HTMaterialKeys.WOOD, HTShapeKeys.FENCE_GATE)

		builder.addAll("minecraft:wools", HTMaterialKeys.WOOL, HTShapeKeys.STORAGE_BLOCK)

		builder.add(Block.brickClay, 0, HTMaterialKeys.TERRACOTTA, HTShapeKeys.BRICKS)
		builder.add(Block.brickStonePolished, 0, HTMaterialKeys.STONE, HTShapeKeys.BRICKS)
		builder.add(Block.brickStonePolishedMossy, 0, HTMaterialKeys.STONE, HTShapeKeys.BRICKS)
		builder.add(Block.brickSandstone, 0, HTMaterialKeys.SANDSTONE, HTShapeKeys.BRICKS)
		builder.add(Block.brickGold, 0, HTMaterialKeys.STONE, HTShapeKeys.BRICKS)
		builder.add(Block.brickLapis, 0, HTMaterialKeys.LAPIS, HTShapeKeys.BRICKS)
		builder.add(Block.brickBasalt, 0, HTMaterialKeys.BASALT, HTShapeKeys.BRICKS)
		builder.add(Block.brickLimestone, 0, HTMaterialKeys.LIMESTONE, HTShapeKeys.BRICKS)
		builder.add(Block.brickGranite, 0, HTMaterialKeys.GRANITE, HTShapeKeys.BRICKS)
		builder.add(Block.brickMarble, 0, HTMaterialKeys.MARBLE, HTShapeKeys.BRICKS)
		builder.add(Block.brickSlate, 0, HTMaterialKeys.SLATE, HTShapeKeys.BRICKS)
		builder.add(Block.brickStone, 0, HTMaterialKeys.STONE, HTShapeKeys.BRICKS)
		builder.add(Block.brickPermafrost, 0, HTMaterialKeys.PERMAFROST, HTShapeKeys.BRICKS)
		builder.add(Block.brickIron, 0, HTMaterialKeys.IRON, HTShapeKeys.BRICKS)

		// slabs

		// stairs

		builder.add(Block.obsidian, 0, HTMaterialKeys.OBSIDIAN, HTShapeKeys.STONE)
		builder.add(Block.glass, 0, HTMaterialKeys.GLASS, HTShapeKeys.STORAGE_BLOCK)
		builder.addAll("minecraft:grasses", HTMaterialKeys.DIRT, HTShapeKeys.STORAGE_BLOCK)
		builder.addAll("minecraft:dirt", HTMaterialKeys.DIRT, HTShapeKeys.STORAGE_BLOCK)
		builder.addAll("minecraft:trommel_dirt", HTMaterialKeys.DIRT, HTShapeKeys.STORAGE_BLOCK)
		builder.add(Block.mud, 0, HTMaterialKeys.MUD, HTShapeKeys.STORAGE_BLOCK)
		builder.add(Block.mudBaked, 0, HTMaterialKeys.MUD, HTShapeKeys.STORAGE_BLOCK)
		builder.add(Block.spongeDry, 0, HTMaterialKeys.SPONGE, HTShapeKeys.STORAGE_BLOCK)
		builder.add(Block.spongeDry, 0, HTMaterialKeys.SPONGE, HTShapeKeys.STORAGE_BLOCK)

		builder.add(Block.sand, 0, HTMaterialKeys.SAND, HTShapeKeys.STORAGE_BLOCK)
		builder.add(Block.gravel, 0, HTMaterialKeys.GRAVEL, HTShapeKeys.STORAGE_BLOCK)
		builder.add(Block.bedrock, 0, HTMaterialKeys.BEDROCK, HTShapeKeys.STONE)
		builder.addAll("minecraft:logs", HTMaterialKeys.WOOD, HTShapeKeys.LOG)

		builder.add(Block.oreCoalStone, 0, HTMaterialKeys.COAL, HTShapeKeys.ORE)
		builder.add(Block.oreCoalBasalt, 0, HTMaterialKeys.COAL, HTShapeKeys.ORE_BASALT)
		builder.add(Block.oreCoalLimestone, 0, HTMaterialKeys.COAL, HTShapeKeys.ORE_LIMESTONE)
		builder.add(Block.oreCoalGranite, 0, HTMaterialKeys.COAL, HTShapeKeys.ORE_GRANITE)

		builder.add(Block.oreIronStone, 0, HTMaterialKeys.IRON, HTShapeKeys.ORE)
		builder.add(Block.oreIronBasalt, 0, HTMaterialKeys.IRON, HTShapeKeys.ORE_BASALT)
		builder.add(Block.oreIronLimestone, 0, HTMaterialKeys.IRON, HTShapeKeys.ORE_LIMESTONE)
		builder.add(Block.oreIronGranite, 0, HTMaterialKeys.IRON, HTShapeKeys.ORE_GRANITE)

		builder.add(Block.oreGoldStone, 0, HTMaterialKeys.GOLD, HTShapeKeys.ORE)
		builder.add(Block.oreGoldBasalt, 0, HTMaterialKeys.GOLD, HTShapeKeys.ORE_BASALT)
		builder.add(Block.oreGoldLimestone, 0, HTMaterialKeys.GOLD, HTShapeKeys.ORE_LIMESTONE)
		builder.add(Block.oreGoldGranite, 0, HTMaterialKeys.GOLD, HTShapeKeys.ORE_GRANITE)

		builder.add(Block.oreLapisStone, 0, HTMaterialKeys.LAPIS, HTShapeKeys.ORE)
		builder.add(Block.oreLapisBasalt, 0, HTMaterialKeys.LAPIS, HTShapeKeys.ORE_BASALT)
		builder.add(Block.oreLapisLimestone, 0, HTMaterialKeys.LAPIS, HTShapeKeys.ORE_LIMESTONE)
		builder.add(Block.oreLapisGranite, 0, HTMaterialKeys.LAPIS, HTShapeKeys.ORE_GRANITE)

		builder.add(Block.oreRedstoneStone, 0, HTMaterialKeys.REDSTONE, HTShapeKeys.ORE)
		builder.add(Block.oreRedstoneBasalt, 0, HTMaterialKeys.REDSTONE, HTShapeKeys.ORE_BASALT)
		builder.add(Block.oreRedstoneLimestone, 0, HTMaterialKeys.REDSTONE, HTShapeKeys.ORE_LIMESTONE)
		builder.add(Block.oreRedstoneGranite, 0, HTMaterialKeys.REDSTONE, HTShapeKeys.ORE_GRANITE)

		builder.add(Block.oreDiamondStone, 0, HTMaterialKeys.DIAMOND, HTShapeKeys.ORE)
		builder.add(Block.oreDiamondBasalt, 0, HTMaterialKeys.DIAMOND, HTShapeKeys.ORE_BASALT)
		builder.add(Block.oreDiamondLimestone, 0, HTMaterialKeys.DIAMOND, HTShapeKeys.ORE_LIMESTONE)
		builder.add(Block.oreDiamondGranite, 0, HTMaterialKeys.DIAMOND, HTShapeKeys.ORE_GRANITE)

		builder.add(Block.oreNethercoalNetherrack, 0, HTMaterialKeys.NETHER_COAL, HTShapeKeys.ORE)

		builder.add(Block.blockCoal, 0, HTMaterialKeys.COAL, HTShapeKeys.STORAGE_BLOCK)
		builder.add(Block.blockIron, 0, HTMaterialKeys.IRON, HTShapeKeys.STORAGE_BLOCK)
		builder.add(Block.blockGold, 0, HTMaterialKeys.GOLD, HTShapeKeys.STORAGE_BLOCK)
		builder.add(Block.blockLapis, 0, HTMaterialKeys.LAPIS, HTShapeKeys.STORAGE_BLOCK)
		builder.add(Block.blockRedstone, 0, HTMaterialKeys.REDSTONE, HTShapeKeys.STORAGE_BLOCK)
		builder.add(Block.blockDiamond, 0, HTMaterialKeys.DIAMOND, HTShapeKeys.STORAGE_BLOCK)
		builder.add(Block.blockNetherCoal, 0, HTMaterialKeys.NETHER_COAL, HTShapeKeys.STORAGE_BLOCK)
		builder.add(Block.blockSteel, 0, HTMaterialKeys.COAL, HTShapeKeys.STORAGE_BLOCK)
		builder.add(Block.blockQuartz, 0, HTMaterialKeys.QUARTZ, HTShapeKeys.STORAGE_BLOCK)
		builder.add(Block.blockOlivine, 0, HTMaterialKeys.OLIVINE, HTShapeKeys.STORAGE_BLOCK)
		builder.add(Block.blockCharcoal, 0, HTMaterialKeys.CHARCOAL, HTShapeKeys.STORAGE_BLOCK)

		builder.add(Block.ice, 0, HTMaterialKeys.ICE, HTShapeKeys.STORAGE_BLOCK)
		builder.add(Block.blockSnow, 0, HTMaterialKeys.SNOW, HTShapeKeys.STORAGE_BLOCK)
		builder.add(Block.blockClay, 0, HTMaterialKeys.CLAY, HTShapeKeys.STORAGE_BLOCK)
		builder.add(Block.pumpkin, 0, HTMaterialKeys.PUMPKIN, HTShapeKeys.STORAGE_BLOCK)
		builder.add(Block.pumpkinCarvedIdle, 0, HTMaterialKeys.PUMPKIN, HTShapeKeys.STORAGE_BLOCK)
		builder.add(Block.pumpkinCarvedActive, 0, HTMaterialKeys.PUMPKIN, HTShapeKeys.STORAGE_BLOCK)
		builder.add(Block.soulsand, 0, HTMaterialKeys.SOUL_SAND, HTShapeKeys.STORAGE_BLOCK)
		builder.add(Block.glowstone, 0, HTMaterialKeys.GLOWSTONE, HTShapeKeys.STORAGE_BLOCK)

		builder.add(Block.stonePolished, 0, HTMaterialKeys.STONE, HTShapeKeys.STONE)
		builder.add(Block.granitePolished, 0, HTMaterialKeys.GRANITE, HTShapeKeys.STONE)
		builder.add(Block.limestonePolished, 0, HTMaterialKeys.LIMESTONE, HTShapeKeys.STONE)
		builder.add(Block.basaltPolished, 0, HTMaterialKeys.BASALT, HTShapeKeys.STONE)
		builder.add(Block.slatePolished, 0, HTMaterialKeys.SLATE, HTShapeKeys.STONE)

		builder.add(Item.foodApple, 0, HTMaterialKeys.APPLE, HTShapeKeys.CROP)
		builder.add(Item.coal, 0, HTMaterialKeys.COAL, HTShapeKeys.GEM)
		builder.add(Item.coal, 1, HTMaterialKeys.CHARCOAL, HTShapeKeys.GEM)
		builder.add(Item.diamond, 0, HTMaterialKeys.COAL, HTShapeKeys.GEM)
		builder.add(Item.ingotIron, 0, HTMaterialKeys.IRON, HTShapeKeys.INGOT)
		builder.add(Item.ingotGold, 0, HTMaterialKeys.GOLD, HTShapeKeys.INGOT)
		builder.add(Item.sulphur, 0, HTMaterialKeys.GUNPOWDER, HTShapeKeys.DUST)
		builder.add(Item.seedsWheat, 0, HTMaterialKeys.WHEAT, HTShapeKeys.SEED)
		builder.add(Item.wheat, 0, HTMaterialKeys.WHEAT, HTShapeKeys.CROP)
		builder.add(Item.flint, 0, HTMaterialKeys.FLINT, HTShapeKeys.GEM)
		builder.add(Item.foodPorkchopRaw, 0, HTMaterialKeys.PORK, HTShapeKeys.RAW_MEAT)
		builder.add(Item.foodPorkchopCooked, 0, HTMaterialKeys.PORK, HTShapeKeys.COOKED_MEAT)
		builder.add(Item.dustRedstone, 0, HTMaterialKeys.REDSTONE, HTShapeKeys.DUST)
		builder.add(Item.ammoSnowball, 0, HTMaterialKeys.SNOW, HTShapeKeys.GEM)
		builder.add(Item.brickClay, 0, HTMaterialKeys.TERRACOTTA, HTShapeKeys.INGOT)
		builder.add(Item.clay, 0, HTMaterialKeys.CLAY, HTShapeKeys.GEM)
		builder.add(Item.paper, 0, HTMaterialKeys.PAPER, HTShapeKeys.PLATE)
		builder.add(Item.slimeball, 0, HTMaterialKeys.SLIME, HTShapeKeys.GEM)
		builder.add(Item.dustGlowstone, 0, HTMaterialKeys.GLOWSTONE, HTShapeKeys.DUST)
		builder.add(Item.foodFishRaw, 0, HTMaterialKeys.FISH, HTShapeKeys.RAW_MEAT)
		builder.add(Item.foodFishCooked, 0, HTMaterialKeys.FISH, HTShapeKeys.COOKED_MEAT)
		builder.add(Item.dye, 3, HTMaterialKeys.CACAO, HTShapeKeys.SEED)
		builder.add(Item.dye, 4, HTMaterialKeys.LAPIS, HTShapeKeys.GEM)
		builder.add(Item.dye, 15, HTMaterialKeys.BONE, HTShapeKeys.DUST)
		builder.add(Item.bone, 0, HTMaterialKeys.BONE, HTShapeKeys.ROD)
		builder.add(Item.dustSugar, 0, HTMaterialKeys.SUGAR, HTShapeKeys.DUST)
		builder.add(Item.nethercoal, 0, HTMaterialKeys.NETHER_COAL, HTShapeKeys.GEM)
		builder.add(Item.ingotSteel, 0, HTMaterialKeys.STEEL, HTShapeKeys.INGOT)

		builder.add(Item.oreRawIron, 0, HTMaterialKeys.IRON, HTShapeKeys.RAW_CHUNK)
		builder.add(Item.oreRawGold, 0, HTMaterialKeys.GOLD, HTShapeKeys.RAW_CHUNK)
		builder.add(Item.quartz, 0, HTMaterialKeys.QUARTZ, HTShapeKeys.GEM)
		builder.add(Item.olivine, 0, HTMaterialKeys.OLIVINE, HTShapeKeys.GEM)
		builder.add(Item.cherry, 0, HTMaterialKeys.CHERRY, HTShapeKeys.CROP)
		builder.add(Item.seedsPumpkin, 0, HTMaterialKeys.PUMPKIN, HTShapeKeys.SEED)
	}
}
