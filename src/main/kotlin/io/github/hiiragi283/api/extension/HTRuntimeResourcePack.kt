package io.github.hiiragi283.api.extension

import com.google.gson.JsonObject
import io.github.hiiragi283.api.HTMaterialsAPI
import net.minecraft.client.resources.IResourcePack
import net.minecraft.client.resources.data.IMetadataSection
import net.minecraft.client.resources.data.MetadataSerializer
import net.minecraft.util.ResourceLocation
import java.awt.image.BufferedImage
import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStream

object HTRuntimeResourcePack : IResourcePack {

    private val domains: MutableSet<String> = mutableSetOf("minecraft", "forge", HTMaterialsAPI.MOD_ID)
    private val resourceMap: MutableMap<ResourceLocation, String> = mutableMapOf()

    @Synchronized
    @JvmStatic
    fun registerResource(location: ResourceLocation, json: JsonObject) {
        if (location.namespace !in domains) {
            domains.add(location.namespace)
        }
        // HTLogger.debug { it.info("Registered resource; $location") }
        resourceMap[location] = json.toString()
    }

    @JvmStatic
    fun registerBlockModel(location: ResourceLocation, json: JsonObject) {
        registerResource(location.modify { "models/block/$it.json" }, json)
    }

    @JvmStatic
    fun registerItemModel(location: ResourceLocation, json: JsonObject) {
        registerResource(location.modify { "models/item/$it.json" }, json)
    }

    //    IResourcePack    //

    @Throws(IOException::class)
    override fun getInputStream(location: ResourceLocation): InputStream =
        resourceMap[location]?.byteInputStream() ?: throw FileNotFoundException(location.toString())

    override fun resourceExists(location: ResourceLocation): Boolean = location in resourceMap

    override fun getResourceDomains(): Set<String> = domains

    @Throws(IOException::class)
    override fun <T : IMetadataSection> getPackMetadata(
        metadataSerializer: MetadataSerializer,
        metadataSectionName: String
    ): T? = metadataSerializer.parseMetadataSection(metadataSectionName, JsonObject())

    @Throws(IOException::class)
    override fun getPackImage(): BufferedImage = throw IOException()

    override fun getPackName(): String = "HT Runtime Resource Pack"
}