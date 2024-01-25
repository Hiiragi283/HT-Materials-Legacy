package io.github.hiiragi283.material;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;

import org.jetbrains.annotations.Nullable;

import zone.rong.mixinbooter.IEarlyMixinLoader;

@IFMLLoadingPlugin.Name(HMReference.MOD_NAME)
@IFMLLoadingPlugin.MCVersion("1.12.2")
public final class HTMaterialsCore implements IFMLLoadingPlugin, IEarlyMixinLoader {

    // IFMLLoadingPlugin //

    @Override
    public String[] getASMTransformerClass() {
        return new String[] {};
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Nullable
    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {}

    @Override
    public String getAccessTransformerClass() {
        return null;
    }

    // IEarlyMixinLoader //

    @Override
    public List<String> getMixinConfigs() {
        return Collections.singletonList("mixins.ht_materials.json");
    }
}
