package io.github.hiiragi283.bwms.api.extension

import io.github.hiiragi283.bwms.api.HTLogger

inline fun <T> T.check(predicate: T.() -> Boolean, message: () -> Any): T = apply { check(predicate(this), message) }

fun <T> T?.checkNotNull(message: () -> Any): T = checkNotNull(this, message)

inline fun <R> runCatchAndLog(block: () -> R): Result<R> = runCatching(block).onFailure(HTLogger::throwing)
