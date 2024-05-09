package io.github.hiiragi283.htms.api.material.property;

import io.github.hiiragi283.htms.api.material.HTMaterial;

@FunctionalInterface
public interface HTTooltipProperty {

    void addInformation(HTMaterial.TooltipContext context);
}
