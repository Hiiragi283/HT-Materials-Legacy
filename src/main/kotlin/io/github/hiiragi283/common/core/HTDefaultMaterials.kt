package io.github.hiiragi283.common.core

import io.github.hiiragi283.api.HTMaterialsAPI
import io.github.hiiragi283.api.HTMaterialsPlugin
import io.github.hiiragi283.api.extension.HTColor
import io.github.hiiragi283.api.extension.averageColor
import io.github.hiiragi283.api.material.HTMaterialKeys
import io.github.hiiragi283.api.material.HTMaterialRegistry
import io.github.hiiragi283.api.material.HTMaterialType
import io.github.hiiragi283.api.material.composition.HTElements
import io.github.hiiragi283.api.shape.HTShapeKeys
import io.github.hiiragi283.api.shape.HTShapeRegistry

internal object HTDefaultMaterials : HTMaterialsPlugin {
    override val modId: String = HTMaterialsAPI.MOD_ID
    override val priority: Int = -100

    override fun registerShape(builder: HTShapeRegistry.Builder) {
        builder.createItemShape(HTShapeKeys.DUST)
        builder.createItemShape(HTShapeKeys.GEAR)
        builder.createItemShape(HTShapeKeys.GEM)
            .addBlacklist(
                HTMaterialKeys.EMERALD,
                HTMaterialKeys.DIAMOND
            )
        builder.createItemShape(HTShapeKeys.INGOT)
            .addBlacklist(
                HTMaterialKeys.IRON
            )
        builder.createItemShape(HTShapeKeys.NUGGET)
            .addBlacklist(
                HTMaterialKeys.IRON
            )
        builder.createItemShape(HTShapeKeys.PLATE)
        builder.createItemShape(HTShapeKeys.ROD)
    }

    override fun registerMaterial(builder: HTMaterialRegistry.Builder) {
        // H
        builder.createGas(HTMaterialKeys.HYDROGEN)
            .index(1)
            .molecular(HTElements.H to 2)
        builder.createLiquid(HTMaterialKeys.WATER)
            .index(1100)
            .molecular(HTElements.H to 2, HTElements.O to 1)
            .color(HTColor.BLUE)
        // He 
        builder.createGas(HTMaterialKeys.HELIUM)
            .index(2)
            .molecular(HTElements.He to 1)
        // Li
        builder.createMetal(HTMaterialKeys.LITHIUM, false)
            .index(3)
            .molecular(HTElements.Li to 1)
        // Be
        builder.createMetal(HTMaterialKeys.BERYLLIUM, false)
            .index(4)
            .molecular(HTElements.Be to 1)
        builder.createGem(HTMaterialKeys.EMERALD, HTMaterialType.Gem.EMERALD)
            .index(1400)
            .molecular(
                HTElements.Be to 3,
                HTElements.Al to 2,
                HTElements.Si to 6,
                HTElements.O to 18,
            )
            .color(HTColor.GREEN)
        // B
        // C
        builder.createSolid(HTMaterialKeys.CARBON)
            .index(6)
            .molecular(HTElements.C to 1)
        builder.createSolid(HTMaterialKeys.ASHES)
            .index(1600)
            .mixture(HTElements.C)
            .color(HTColor.DARK_GRAY)
        builder.createSolid(HTMaterialKeys.CHARCOAL)
            .index(1601)
            .mixture(HTElements.C)
            .color(averageColor(HTColor.BLACK to 7, HTColor.YELLOW to 1))
        builder.createSolid(HTMaterialKeys.COAL)
            .index(1602)
            .mixture(HTElements.C)
        builder.createGem(HTMaterialKeys.COKE, HTMaterialType.Gem.COAL)
            .index(1603)
            .mixture(HTElements.C)
            .color(HTColor.DARK_GRAY)
        builder.createGem(HTMaterialKeys.DIAMOND, HTMaterialType.Gem.DIAMOND)
            .index(1604)
            .molecular(HTElements.C to 1)
            .color(HTColor.AQUA)
        builder.createLiquid(HTMaterialKeys.MILK)
            .index(1605)
            .mixture(HTElements.C, HTElements.H, HTElements.O)
        builder.createSolid(HTMaterialKeys.RUBBER)
            .index(1606)
            .polymer(HTElements.C to 5, HTElements.H to 6)
            .color(averageColor(HTColor.BLACK, HTColor.DARK_GRAY))
            .formula("CC(=C)C=C")
        builder.createWood(HTMaterialKeys.WOOD)
            .index(1607)
            .mixture(HTElements.C, HTElements.H, HTElements.O)
            .color(averageColor(HTColor.DARK_GRAY to 2, HTColor.RED to 1, HTColor.YELLOW to 1))
        // N
        builder.createGas(HTMaterialKeys.NITROGEN)
            .index(7)
            .molecular(HTElements.N to 2)
        builder.createGas(HTMaterialKeys.AMMONIA)
            .index(1700)
            .molecular(HTElements.N to 1, HTElements.H to 3)
        builder.createGem(HTMaterialKeys.NITER, HTMaterialType.Gem.LAPIS)
            .index(1701)
            .molecular(HTElements.H to 1, HTElements.NO3 to 1)
        builder.createLiquid(HTMaterialKeys.NITRIC_ACID)
            .index(1702)
            .molecular(HTElements.H to 1, HTElements.NO3 to 1)
        // O
        builder.createGas(HTMaterialKeys.OXYGEN)
            .index(8)
            .molecular(HTElements.O to 2)
        // F
        builder.createGas(HTMaterialKeys.FLUORINE)
            .index(9)
            .molecular(HTElements.F to 2)
        builder.createGem(HTMaterialKeys.FLUORITE, HTMaterialType.Gem.LAPIS)
            .index(1900)
            .molecular(HTElements.Ca to 1, HTElements.F to 2)
            .color(HTColor.GREEN)
        builder.createGas(HTMaterialKeys.HYDROGEN_FLUORIDE)
            .index(1901)
            .molecular(HTElements.H to 1, HTElements.F to 1)
            .color(averageColor(HTColor.GREEN, HTColor.AQUA))
        // Ne
        // Fe
        builder.createMetal(HTMaterialKeys.IRON)
            .index(26)
            .molecular(HTElements.Fe to 1)
    }

}