package io.github.hiiragi283.api.extension

import net.minecraftforge.fml.relauncher.Side

inline fun runWhenOn(side: Side, action: () -> Unit) {
    if (currentSide == side) action()
}

inline fun <T : Any> runForSide(server: () -> T, client: () -> T): T = when (currentSide) {
    Side.CLIENT -> client()
    Side.SERVER -> server()
}