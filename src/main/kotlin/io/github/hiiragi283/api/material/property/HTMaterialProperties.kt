package io.github.hiiragi283.api.material.property

import io.github.hiiragi283.api.HTMaterialsAPI
import io.github.hiiragi283.api.extension.TypedResourceLocation
import io.github.hiiragi283.api.fluid.HTFluidPhase
import io.github.hiiragi283.api.material.HTMaterialType
import io.github.hiiragi283.api.material.composition.HTElement
import io.github.hiiragi283.api.shape.HTShapeKey
import java.awt.Color

object HTMaterialProperties {
    //    Static    //

    @JvmField
    val INDEX: TypedResourceLocation<Int> =
        TypedResourceLocation.of(HTMaterialsAPI.id("index"))

    @JvmField
    val TYPE: TypedResourceLocation<HTMaterialType> =
        TypedResourceLocation.of(HTMaterialsAPI.id("type"))

    @JvmField
    val COLOR: TypedResourceLocation<Color> =
        TypedResourceLocation.of(HTMaterialsAPI.id("color"))

    @JvmField
    val FORMULA: TypedResourceLocation<String> =
        TypedResourceLocation.of(HTMaterialsAPI.id("formula"))

    @JvmField
    val MOLAR: TypedResourceLocation<Double> =
        TypedResourceLocation.of(HTMaterialsAPI.id("molar"))

    @JvmField
    val COMPONENT: TypedResourceLocation<Map<HTElement, Int>> =
        TypedResourceLocation.of(HTMaterialsAPI.id("component"))

    @JvmField
    val BLOCK_SET: TypedResourceLocation<Set<HTShapeKey>> =
        TypedResourceLocation.of(HTMaterialsAPI.id("block_set"))

    @JvmField
    val FLUID_SET: TypedResourceLocation<Set<HTFluidPhase>> =
        TypedResourceLocation.of(HTMaterialsAPI.id("fluid_set"))

    @JvmField
    val ITEM_SET: TypedResourceLocation<Set<HTShapeKey>> =
        TypedResourceLocation.of(HTMaterialsAPI.id("item_set"))

    @JvmField
    val DEFAULT_ITEM_SHAPE: TypedResourceLocation<HTShapeKey> =
        TypedResourceLocation.of(HTMaterialsAPI.id("default_item_shape"))

    @JvmField
    val EXPLOSION: TypedResourceLocation<HTExplosionProperty> =
        TypedResourceLocation.of(HTMaterialsAPI.id("explosion"))

    @JvmField
    val DISPENSE_BEHAVIOR: TypedResourceLocation<HTMaterialDispenseBehavior> =
        TypedResourceLocation.of(HTMaterialsAPI.id("dispense_behavior"))

    /*@JvmField
    val STORAGE: TypedResourceLocation<HTMaterialStorageContent> =
        TypedResourceLocation.of(HTMaterialsAPI.id("storage"))

    @JvmField
    val ORE: TypedResourceLocation<HTMaterialOre> =
        TypedResourceLocation.of(HTMaterialsAPI.id("ore"))*/

    //    Dynamic    //

    /*@JvmStatic
    fun blockColor(shapeKey: HTShapeKey): TypedResourceLocation<IBlockColor> =
        TypedResourceLocation.of(HTMaterialsAPI.id("block_color/${shapeKey.name}"))

    @JvmStatic
    fun blockLayer(shapeKey: HTShapeKey): TypedResourceLocation<RenderLayer> =
        TypedResourceLocation.of(HTMaterialsAPI.id("block_layer/${shapeKey.name}"))

    @JvmStatic
    fun blockLoot(shapeKey: HTShapeKey): TypedResourceLocation<(Block) -> LootTable.Builder> =
        TypedResourceLocation.of(HTMaterialsAPI.id("block_loot/${shapeKey.name}"))

    @JvmStatic
    fun blockModel(shapeKey: HTShapeKey): TypedResourceLocation<(HTModelJsonBuilder, Block) -> Unit> =
        TypedResourceLocation.of(HTMaterialsAPI.id("block_model/${shapeKey.name}"))

    @JvmStatic
    fun blockState(shapeKey: HTShapeKey): TypedResourceLocation<(Block) -> BlockStateSupplier> =
        TypedResourceLocation.of(HTMaterialsAPI.id("blockstate/${shapeKey.name}"))

    @JvmStatic
    fun itemBlockModel(shapeKey: HTShapeKey): TypedResourceLocation<(HTModelJsonBuilder, Item) -> Unit> =
        TypedResourceLocation.of(HTMaterialsAPI.id("item_block_model/${shapeKey.name}"))

    @JvmStatic
    fun itemColor(shapeKey: HTShapeKey): TypedResourceLocation<IItemColor> =
        TypedResourceLocation.of(io.github.hiiragi283.api.HTMaterialsAPI.id("item_color/${shapeKey.name}"))

    @JvmStatic
    fun itemModel(shapeKey: HTShapeKey): TypedResourceLocation<Consumer<HTModelJsonBuilder>> =
        TypedResourceLocation.of(HTMaterialsAPI.getId("item_model/${shapeKey.name}"))*/
}