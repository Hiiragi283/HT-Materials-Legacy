package io.github.hiiragi283.bwms.api.material

import io.github.hiiragi283.bwms.api.BWMsAPI
import io.github.hiiragi283.bwms.api.extension.HTColor
import io.github.hiiragi283.bwms.api.extension.TypedResourceLocation
import io.github.hiiragi283.bwms.api.extension.validateFormula
import io.github.hiiragi283.bwms.api.extension.validateMolar
import io.github.hiiragi283.bwms.api.fluid.HTFluidPhase
import io.github.hiiragi283.bwms.api.material.composition.HTElement
import io.github.hiiragi283.bwms.api.material.composition.HTMaterialComposition
import io.github.hiiragi283.bwms.api.material.property.HTMaterialFlags
import io.github.hiiragi283.bwms.api.material.property.HTMaterialProperties
import io.github.hiiragi283.bwms.api.property.HTPropertyHolder
import io.github.hiiragi283.bwms.api.shape.HTShapeKey
import io.github.hiiragi283.bwms.api.shape.HTShapeKeys
import java.awt.Color

class HTMaterialBuilder(
	private val materialKey: HTMaterialKey,
	private val materialType: HTMaterialType,
) : HTPropertyHolder.Mutable {
	private var index: Int? = null
	private var composition: HTMaterialComposition? = null
	private val properties: MutableMap<TypedResourceLocation<*>, Any> = mutableMapOf()
	private val blockSet: MutableSet<HTShapeKey> = HashSet(materialType.blockSet)
	private val fluidSet: MutableSet<HTFluidPhase> = HashSet(materialType.fluidSet)
	private val itemSet: MutableSet<HTShapeKey> = HashSet(materialType.itemSet)

	private fun getDefaultShape(): HTShapeKey? = when {
		HTShapeKeys.INGOT in itemSet && HTShapeKeys.GEM in itemSet ->
			throw IllegalStateException("Could not include both shape INGOT and GEM!")

		HTShapeKeys.INGOT in itemSet -> HTShapeKeys.INGOT
		HTShapeKeys.GEM in itemSet -> HTShapeKeys.GEM
		else -> null
	}

	private fun initComposition(composition: HTMaterialComposition?) {
		composition?.let {
			if (HTMaterialProperties.COLOR !in this) color(it.color)
			if (HTMaterialProperties.FORMULA !in this) formula(it.formula)
			if (HTMaterialProperties.MOLAR !in this) molar(it.molar)
			addProperty(HTMaterialProperties.COMPONENT, it.componentMap)
		}
	}

	internal fun build(): HTPropertyHolder = HTPropertyHolder.create(properties) {
		// Event
		// MinecraftForge.EVENT_BUS.post(BuildingEvent(this@HTMaterialBuilder))
		// Set properties
		getDefaultShape()?.let { addProperty(HTMaterialProperties.DEFAULT_ITEM_SHAPE, it) }
		addProperty(HTMaterialProperties.TYPE, materialType)
		addProperty(HTMaterialProperties.BLOCK_SET, blockSet)
		addProperty(HTMaterialProperties.FLUID_SET, fluidSet)
		addProperty(HTMaterialProperties.ITEM_SET, itemSet)
		initComposition(composition)
		index?.let { addProperty(HTMaterialProperties.INDEX, it) }
		// Validate basic property
		get(HTMaterialProperties.INDEX)?.let { index ->
			check(index in (1 until BWMsAPI.MAX_METADATA)) {
				"Invalid index: $index found at $materialKey!"
			}
		}
		if (getOrDefault(HTMaterialProperties.COLOR, HTColor.WHITE) == HTColor.WHITE) {
			remove(HTMaterialProperties.COLOR)
		}
		if (validateFormula(get(HTMaterialProperties.FORMULA)) == null) {
			remove(HTMaterialProperties.FORMULA)
		}
		if (validateMolar(get(HTMaterialProperties.MOLAR)) == null) {
			remove(HTMaterialProperties.MOLAR)
		}
	}

	//    Property    //

	fun <T : Any> addProperty(id: TypedResourceLocation<T>, value: T) = apply {
		set(id, value)
	}

	fun <T : Any> removeProperty(id: TypedResourceLocation<T>) = apply {
		remove(id)
	}

	override fun <T : Any> get(id: TypedResourceLocation<T>): T? = id.cast(properties[id])

	override fun contains(id: TypedResourceLocation<*>): Boolean = id in properties

	override fun forEachProperties(action: (TypedResourceLocation<*>, Any) -> Unit) {
		properties.forEach(action)
	}

	override fun <T : Any> set(id: TypedResourceLocation<T>, value: T) {
		properties[id] = value
	}

	override fun remove(id: TypedResourceLocation<*>) {
		properties.remove(id)
	}

	//    Property - Flag    //

	fun addFlag(modid: String, path: String) =
		addProperty(HTMaterialFlags.getOrCreateFlag(modid, path), Unit)

	fun removeFlag(modid: String, path: String) =
		removeProperty(HTMaterialFlags.getOrCreateFlag(modid, path))

	//    Property - Composition    //

	fun mixture(vararg elements: HTElement) = composition(HTMaterialComposition.mixture(*elements))

	fun molecular(vararg pairs: Pair<HTElement, Int>) = composition(HTMaterialComposition.molecular(*pairs))

	fun polymer(vararg pairs: Pair<HTElement, Int>) = composition(HTMaterialComposition.polymer(*pairs))

	fun composition(composition: HTMaterialComposition) = apply {
		this.composition = composition
	}

	fun color(color: Color) = addProperty(HTMaterialProperties.COLOR, color)

	fun formula(formula: String) = formula
		.let(::validateFormula)
		?.let { addProperty(HTMaterialProperties.FORMULA, it) }
		?: this

	fun molar(molar: Double) = molar
		.let(::validateMolar)
		?.let { addProperty(HTMaterialProperties.MOLAR, it) }
		?: this

	fun index(index: Int) = apply {
		this.index = index
	}

	//    Property - Content    //

	fun addBlock(shapeKey: HTShapeKey) = apply { blockSet.add(shapeKey) }

	fun addFluid(phase: HTFluidPhase) = apply { fluidSet.add(phase) }

	fun addItem(shapeKey: HTShapeKey) = apply { itemSet.add(shapeKey) }

	//    Event    //

	// class BuildingEvent(val builder: HTMaterialBuilder) : Event()
}
