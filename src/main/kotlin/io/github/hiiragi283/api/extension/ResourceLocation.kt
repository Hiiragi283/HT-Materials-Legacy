package io.github.hiiragi283.api.extension

import net.minecraft.util.ResourceLocation

fun ResourceLocation.prefix(prefix: String): ResourceLocation =
    ResourceLocation(this.namespace, prefix + this.path)

fun ResourceLocation.suffix(suffix: String): ResourceLocation =
    ResourceLocation(this.namespace, this.path + suffix)

inline fun ResourceLocation.modify(function: (String) -> String): ResourceLocation =
    ResourceLocation(this.namespace, function(this.path))

fun ResourceLocation.removePrefix(prefix: String): ResourceLocation =
    ResourceLocation(this.namespace, this.path.removePrefix(prefix))

fun ResourceLocation.removeSuffix(suffix: String): ResourceLocation =
    ResourceLocation(this.namespace, this.path.removeSuffix(suffix))

fun ResourceLocation.arrange(prefix: String? = null, suffix: String? = null): ResourceLocation {
    var arrangedPath: String = path
    prefix?.let { arrangedPath = arrangedPath.removePrefix(it) }
    suffix?.let { arrangedPath = arrangedPath.removeSuffix(it) }
    return ResourceLocation(
        namespace,
        arrangedPath,
    )
}