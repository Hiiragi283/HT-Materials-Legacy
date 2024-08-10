package io.github.hiiragi283.api.extension

import net.minecraftforge.fml.relauncher.FMLLaunchHandler
import net.minecraftforge.fml.relauncher.Side

//    FMLLaunchHandler    //

val currentSide: Side
    get() = FMLLaunchHandler.side()

inline fun onSide(side: Side, action: () -> Unit) {
    if (currentSide == side) action()
}

inline fun <T> getBySide(client: () -> T, server: () -> T): T = when (currentSide) {
    Side.CLIENT -> client()
    Side.SERVER -> server()
}

val isDev: Boolean
    get() = FMLLaunchHandler.isDeobfuscatedEnvironment()

inline fun onDev(action: () -> Unit) {
    if (isDev) action()
}