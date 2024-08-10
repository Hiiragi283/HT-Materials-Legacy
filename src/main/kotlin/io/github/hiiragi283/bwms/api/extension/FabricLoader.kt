package io.github.hiiragi283.bwms.api.extension

import net.fabricmc.api.EnvType
import net.fabricmc.loader.api.FabricLoader
import net.fabricmc.loader.api.ModContainer
import net.fabricmc.loader.api.entrypoint.EntrypointContainer
import kotlin.jvm.optionals.getOrNull

val loader: FabricLoader
	get() = FabricLoader.getInstance()

fun getModContainer(modId: String): ModContainer? = loader.getModContainer(modId).getOrNull()

val allMods: Collection<ModContainer>
	get() = loader.allMods

val isDevEnv: Boolean
	get() = loader.isDevelopmentEnvironment

val currentEnvType: EnvType
	get() = loader.environmentType

val isClient: Boolean
	get() = currentEnvType == EnvType.CLIENT

fun isModLoaded(modId: String): Boolean = loader.isModLoaded(modId)

//    Entrypoint    //

fun <T> getEntrypoints(key: String, clazz: Class<T>): List<T> = loader.getEntrypoints(key, clazz)

fun <T> getEntrypointContainers(key: String, clazz: Class<T>): List<EntrypointContainer<T>> =
	loader.getEntrypointContainers(key, clazz)

fun <T : Any> getEntrypointMap(key: String, clazz: Class<T>): Map<String, EntrypointContainer<T>> =
	getEntrypointContainers(key, clazz).associateBy { it.provider.metadata.id }

fun <T : Any> findEntrypointContainer(modId: String, key: String, clazz: Class<T>): EntrypointContainer<T>? =
	getEntrypointContainers(key, clazz).firstOrNull { it.provider.metadata.id == modId }

//    Entrypoint - inline    //

inline fun <reified T : Any> getEntrypoints(key: String): List<T> = loader.getEntrypoints(key, T::class.java)

inline fun <reified T : Any> getEntrypointContainers(key: String): List<EntrypointContainer<T>> =
	loader.getEntrypointContainers(key, T::class.java)

inline fun <reified T : Any> getEntrypointMap(key: String): Map<String, EntrypointContainer<T>> =
	getEntrypointContainers<T>(key).associateBy { it.provider.metadata.id }

inline fun <reified T : Any> findEntrypointContainer(modId: String, key: String): EntrypointContainer<T>? =
	getEntrypointContainers<T>(key).firstOrNull { it.provider.metadata.id == modId }
