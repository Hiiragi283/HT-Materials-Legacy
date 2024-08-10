package io.github.hiiragi283.api.extension

import java.awt.Color

//    Color    //

fun averageColor(vararg colors: Color): Color = averageColor(colors.associateWith { 1 })

fun averageColor(colors: Iterable<Color>): Color = averageColor(colors.associateWith { 1 })

fun averageColor(vararg pairs: Pair<Color, Int>): Color = averageColor(mapOf(*pairs))

fun averageColor(map: Map<Color, Int>): Color {
    var redSum = 0
    var greenSum = 0
    var blueSum = 0
    var weightSum = 0
    map.forEach { (color: Color, weight: Int) ->
        // RGB値にweightをかけた値を加算していく
        color.run {
            redSum += this.red * weight
            greenSum += this.green * weight
            blueSum += this.blue * weight
        }
        weightSum += weight
    }
    return if (weightSum > 0) {
        Color(redSum / weightSum, greenSum / weightSum, blueSum / weightSum)
    } else {
        Color.WHITE
    }
}

//    Formula    //

fun formatFormula(formulas: Iterable<String>): String = formatFormula(formulas.associateWith { 1 })

fun formatFormula(map: Map<String, Int>): String {
    val builder = StringBuilder()
    for ((formula: String, weight: Int) in map) {
        builder.append(formula)
        // 値が1の場合はパス
        if (weight == 1) continue
        // 化学式の下付き数字の桁数調整
        val subscript1: Char = '\u2080' + (weight % 10)
        val subscript10: Char = '\u2080' + (weight / 10)
        // 2桁目が0でない場合，下付き数字を2桁にする
        builder.append(
            StringBuilder().run {
                if (subscript10 != '\u2080') this.append(subscript10)
                this.append(subscript1)
            },
        )
    }
    return builder.toString()
}

fun validateFormula(formula: String?): String? = formula?.takeIf { it.isNotEmpty() && it.isNotBlank() }

//    Molar    //

fun calculateMolar(molars: Iterable<Double>): Double = calculateMolar(molars.associateWith { 1 })

fun calculateMolar(map: Map<Double, Int>): Double = map.map { (molar: Double, weight: Int) -> molar * weight }.sum()

fun validateMolar(molar: Double?): Double? = molar?.takeIf { it > 0.0 }?.let { "%.1f".format(it).toDouble() }
