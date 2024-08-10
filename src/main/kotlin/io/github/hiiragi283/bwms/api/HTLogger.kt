package io.github.hiiragi283.bwms.api

import io.github.hiiragi283.bwms.api.extension.isDevEnv
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

object HTLogger {
	private val logger: Logger = LogManager.getLogger(BWMsAPI.MOD_NAME)

	@JvmStatic
	fun log(action: (Logger) -> Unit) {
		action(logger)
	}

	@JvmStatic
	fun debug(action: (Logger) -> Unit) {
		if (isDevEnv) action(logger)
	}

	@JvmStatic
	fun <T : Throwable> throwing(throwable: T) {
		log { it.error(throwable) }
	}
}
