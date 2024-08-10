package io.github.hiiragi283.api

import io.github.hiiragi283.api.extension.isDev
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

object HTLogger {
    private val logger: Logger = LogManager.getLogger(HTMaterialsAPI.MOD_NAME)

    @JvmStatic
    fun log(action: (Logger) -> Unit) {
        action(logger)
    }

    @JvmStatic
    fun debug(action: (Logger) -> Unit) {
        if (isDev) action(logger)
    }

    @JvmStatic
    fun <T : Throwable> throwing(throwable: T) {
        log { it.error(throwable) }
    }
}