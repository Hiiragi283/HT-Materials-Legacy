package io.github.hiiragi283.material.compat.crt;

import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.oredict.IOreDictEntry;
import crafttweaker.mc1120.oredict.MCOreDictEntry;
import io.github.hiiragi283.material.api.material.HTMaterialKey;
import io.github.hiiragi283.material.api.shape.HTShapeKey;
import stanhebben.zenscript.annotations.ZenExpansion;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenExpansion(HTCrTPlugin.SHAPE_PREFIX + "HTShapeKey")
@ZenRegister
public class HTShapeKeyExtension {

    @ZenMethod
    public static IOreDictEntry getOreDictEntry(HTShapeKey shapeKey, HTMaterialKey materialKey) {
        return new MCOreDictEntry(shapeKey.getOreDict(materialKey));
    }

}