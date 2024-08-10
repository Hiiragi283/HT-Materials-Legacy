package io.github.hiiragi283.api.extension

import net.minecraft.util.NonNullList

inline fun <T : Any> buildNonNullList(
    defaultValue: T,
    builderAction: NonNullList<T>.() -> Unit
): NonNullList<T> = NonNullList.from(defaultValue).apply(builderAction)

inline fun <T : Any> buildNonNullList(
    size: Int,
    defaultValue: T,
    builderAction: NonNullList<T>.() -> Unit
): NonNullList<T> = NonNullList.withSize(size, defaultValue).apply(builderAction)