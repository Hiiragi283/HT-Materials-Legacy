package io.github.hiiragi283.material;

import io.github.hiiragi283.material.api.item.HTItemMaterial;
import io.github.hiiragi283.material.api.shape.HTShapes;
import net.minecraft.item.Item;

public abstract class HMItems {

    private HMItems() {
    }

    public static final Item MATERIAL_DUST = new HTItemMaterial(HTShapes.DUST);

}