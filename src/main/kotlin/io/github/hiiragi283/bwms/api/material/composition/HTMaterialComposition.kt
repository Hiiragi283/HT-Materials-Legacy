package io.github.hiiragi283.bwms.api.material.composition

import io.github.hiiragi283.bwms.api.extension.HTColor
import io.github.hiiragi283.bwms.api.extension.averageColor
import io.github.hiiragi283.bwms.api.extension.calculateMolar
import io.github.hiiragi283.bwms.api.extension.formatFormula
import java.awt.Color

data class HTMaterialComposition(
	val componentMap: Map<HTElement, Int>,
	val color: Color = averageColor(componentMap.mapKeys { it.key.color }),
	val formula: String = formatFormula(componentMap.mapKeys { it.key.formula }),
	val molar: Double = calculateMolar(componentMap.mapKeys { it.key.molar }),
) {
	companion object {
		//    Molecular    //

		@JvmStatic
		fun molecular(vararg pairs: Pair<HTElement, Int>): HTMaterialComposition = molecular(mapOf(*pairs))

		@JvmStatic
		fun molecular(map: Map<HTElement, Int>): HTMaterialComposition = HTMaterialComposition(map)

		//    Hydrate    //

		@JvmStatic
		fun hydrate(vararg pairs: Pair<HTElement, Int>, waterCount: Int): HTMaterialComposition =
			hydrate(molecular(*pairs), waterCount)

		@JvmStatic
		fun hydrate(unhydrate: HTMaterialComposition, waterCount: Int): HTMaterialComposition = HTMaterialComposition(
			buildMap {
				putAll(unhydrate.componentMap)
				put(HTElements.WATER, waterCount)
			},
			HTColor.WHITE,
			"${unhydrate.formula}-${waterCount}H₂O",
			unhydrate.molar + waterCount * 18.0,
		)

		//    Mixture    //

		@JvmStatic
		fun mixture(vararg providers: HTElement): HTMaterialComposition = mixture(providers.toList())

		@JvmStatic
		fun mixture(elements: Iterable<HTElement>): HTMaterialComposition = HTMaterialComposition(
			elements.associateWith { 1 },
			averageColor(elements.map(HTElement::color)),
			"(${elements.joinToString(", ", transform = HTElement::formula)})",
			0.0,
		)

		//    Polymer    //

		@JvmStatic
		fun polymer(vararg pairs: Pair<HTElement, Int>): HTMaterialComposition = polymer(molecular(*pairs))

		@JvmStatic
		fun polymer(monomar: HTMaterialComposition): HTMaterialComposition = HTMaterialComposition(
			monomar.componentMap,
			monomar.color,
			"(${monomar.formula})n",
			0.0,
		)
	}

	/*private data object Empty : HTMaterialComposition() {
		override val componentMap: Map<HTElement, Int> = emptyMap()
		override val color: Color = HTColor.WHITE
		override val formula: String = ""
		override val molar: Double = 0.0
	}

	private data class Immutable(
		override var componentMap: Map<HTElement, Int>,
		override var color: Color = averageColor(componentMap.mapKeys { it.key.color }),
		override var formula: String = formatFormula(componentMap.mapKeys { it.key.formula }),
		override var molar: Double = calculateMolar(componentMap.mapKeys { it.key.molar }),
	) : HTMaterialComposition()*/
}
