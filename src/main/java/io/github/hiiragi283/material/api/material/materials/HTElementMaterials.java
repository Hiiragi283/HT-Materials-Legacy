package io.github.hiiragi283.material.api.material.materials;

import io.github.hiiragi283.material.HMReference;
import io.github.hiiragi283.material.api.HTAddon;
import io.github.hiiragi283.material.api.HTMaterialsAddon;
import io.github.hiiragi283.material.api.material.HTMaterialKey;
import io.github.hiiragi283.material.api.registry.HTObjectKeySet;

@HTAddon
public class HTElementMaterials implements HTMaterialsAddon {

    //    4th Period    //

    public static HTMaterialKey IRON = new HTMaterialKey("iron", 28);

    //    HTMaterialsAddon    //

    @Override
    public String getModId() {
        return HMReference.MOD_ID;
    }

    @Override
    public int getPriority() {
        return -100;
    }

    @Override
    public void registerMaterialKey(HTObjectKeySet<HTMaterialKey> registry) {
        //4th Period
        registry.addAll(
                IRON
        );
    }

}