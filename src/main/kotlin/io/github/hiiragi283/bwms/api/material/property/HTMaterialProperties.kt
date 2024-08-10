package io.github.hiiragi283.bwms.api.material.property

import io.github.hiiragi283.bwms.api.BWMsAPI
import io.github.hiiragi283.bwms.api.extension.TypedResourceLocation
import io.github.hiiragi283.bwms.api.fluid.HTFluidPhase
import io.github.hiiragi283.bwms.api.material.HTMaterialType
import io.github.hiiragi283.bwms.api.material.composition.HTElement
import io.github.hiiragi283.bwms.api.shape.HTShapeKey
import useless.prismaticlibe.IColored
import java.awt.Color

object HTMaterialProperties {
	//    Static    //

	@JvmField
	val INDEX: TypedResourceLocation<Int> =
		TypedResourceLocation.of(BWMsAPI.MOD_ID, ("index"))

	@JvmField
	val TYPE: TypedResourceLocation<HTMaterialType> =
		TypedResourceLocation.of(BWMsAPI.MOD_ID, ("type"))

	@JvmField
	val COLOR: TypedResourceLocation<Color> =
		TypedResourceLocation.of(BWMsAPI.MOD_ID, ("color"))

	@JvmField
	val FORMULA: TypedResourceLocation<String> =
		TypedResourceLocation.of(BWMsAPI.MOD_ID, ("formula"))

	@JvmField
	val MOLAR: TypedResourceLocation<Double> =
		TypedResourceLocation.of(BWMsAPI.MOD_ID, ("molar"))

	@JvmField
	val COMPONENT: TypedResourceLocation<Map<HTElement, Int>> =
		TypedResourceLocation.of(BWMsAPI.MOD_ID, ("component"))

	@JvmField
	val BLOCK_SET: TypedResourceLocation<Set<HTShapeKey>> =
		TypedResourceLocation.of(BWMsAPI.MOD_ID, ("block_set"))

	@JvmField
	val FLUID_SET: TypedResourceLocation<Set<HTFluidPhase>> =
		TypedResourceLocation.of(BWMsAPI.MOD_ID, ("fluid_set"))

	@JvmField
	val ITEM_SET: TypedResourceLocation<Set<HTShapeKey>> =
		TypedResourceLocation.of(BWMsAPI.MOD_ID, ("item_set"))

	@JvmField
	val DEFAULT_ITEM_SHAPE: TypedResourceLocation<HTShapeKey> =
		TypedResourceLocation.of(BWMsAPI.MOD_ID, ("default_item_shape"))

	@JvmField
	val EXPLOSION: TypedResourceLocation<HTExplosionProperty> =
		TypedResourceLocation.of(BWMsAPI.MOD_ID, ("explosion"))

	/*@JvmField
	val DISPENSE_BEHAVIOR: TypedResourceLocation<HTMaterialDispenseBehavior> =
		TypedResourceLocation.of(("dispense_behavior"))

	@JvmField
	val STORAGE: TypedResourceLocation<HTMaterialStorageContent> =
		TypedResourceLocation.of(("storage"))

	@JvmField
	val ORE: TypedResourceLocation<HTMaterialOre> =
		TypedResourceLocation.of(("ore"))*/

	//    Dynamic    //

	@JvmStatic
	fun itemTexture(shapeKey: HTShapeKey): TypedResourceLocation<IColored> =
		TypedResourceLocation.of(BWMsAPI.MOD_ID, ("item_texture/$shapeKey"))

	@JvmStatic
	fun craftingRecipe(shapeKey: HTShapeKey): TypedResourceLocation<() -> Unit> =
		TypedResourceLocation.of(BWMsAPI.MOD_ID, ("crafting_recipe/${shapeKey}"))

	@JvmStatic
	fun furnaceRecipe(shapeKey: HTShapeKey): TypedResourceLocation<() -> Unit> =
		TypedResourceLocation.of(BWMsAPI.MOD_ID, ("furnace_recipe/${shapeKey}"))

	@JvmStatic
	fun blastingRecipe(shapeKey: HTShapeKey): TypedResourceLocation<() -> Unit> =
		TypedResourceLocation.of(BWMsAPI.MOD_ID, ("blasting_recipe/${shapeKey}"))

	@JvmStatic
	fun trommelRecipe(shapeKey: HTShapeKey): TypedResourceLocation<() -> Unit> =
		TypedResourceLocation.of(BWMsAPI.MOD_ID, ("trommel_recipe/${shapeKey}"))

}
