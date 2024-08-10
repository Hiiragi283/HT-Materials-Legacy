package io.github.hiiragi283.bwms.api.extension

import turniplabs.halplibe.util.toml.Toml

inline fun buildToml(comment: String, builderAction: Toml.() -> Unit): Toml = Toml(comment).apply(builderAction)
