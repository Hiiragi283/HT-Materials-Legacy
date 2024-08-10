package io.github.hiiragi283.common.core

import io.github.hiiragi283.api.HTMaterialsAPI
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin
import zone.rong.mixinbooter.IEarlyMixinLoader

@IFMLLoadingPlugin.Name(HTMaterialsAPI.MOD_NAME)
@IFMLLoadingPlugin.MCVersion("1.12.2")
class HTMaterialsCore : IFMLLoadingPlugin, IEarlyMixinLoader {

    //    IFMLLoadingPlugin    //

    override fun getASMTransformerClass(): Array<String> = arrayOf()

    override fun getModContainerClass(): String? = null

    override fun getSetupClass(): String? = null

    override fun injectData(data: MutableMap<String, Any>) = Unit

    override fun getAccessTransformerClass(): String? = null

    //    IEarlyMixinLoader    //

    override fun getMixinConfigs(): List<String> = listOf("mixins.ht_materials.json")
}