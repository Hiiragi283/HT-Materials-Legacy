package io.github.hiiragi283.bwms.api.extension

import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject

//    JsonObject    //

fun buildJson(builderAction: JsonObject.() -> Unit): JsonObject = JsonObject().apply(builderAction)

// fun JsonObject.addProperty(property: String, value: ResourceLocation) = addProperty(property, value.toString())

fun <V : Any> Map<String, V>.toJsonObject(mapping: (V) -> JsonElement): JsonObject = buildJson {
	this@toJsonObject.mapValues { mapping(it.value) }.forEach { (key, json) -> add(key, json) }
}

fun <K : Any, V : Any> Map<K, V>.toJsonObject(keyMapping: (K) -> String, valueMapping: (V) -> JsonElement): JsonObject =
	this@toJsonObject.mapKeys { keyMapping(it.key) }.toJsonObject(valueMapping)

//    JsonArray    //

fun buildJsonArray(builderAction: JsonArray.() -> Unit): JsonArray = JsonArray().apply(builderAction)

// fun JsonArray.add(value: ResourceLocation) = add(value.toString())

fun Iterable<JsonElement>.toJsonArray() = toJsonArray { it }

fun <T : Any> Iterable<T>.toJsonArray(mapping: (T) -> JsonElement): JsonArray = buildJsonArray {
	this@toJsonArray.map(mapping).forEach(::add)
}
