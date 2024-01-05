#loader ht_materials

import hiiragi283.material.ColorConvertible;
import hiiragi283.material.HTMaterial;
import hiiragi283.material.HTMaterialKey;
import hiiragi283.material.HTMaterialRegistry;
import hiiragi283.material.flag.HTMaterialFlag;
import hiiragi283.shape.HTShapeKey;

print("Loading CrT compat scripts...");

val neutronium = <materialKey:neutronium:32001>;

HTMaterialRegistry.registerMaterialKey(neutronium);

HTMaterialRegistry.propertyMapBuilder(neutronium);

HTMaterialRegistry.flagSetBuilder(neutronium)
    .add(HTMaterialFlag.getFlag("generate_ingot"));

HTMaterialRegistry.setFormula(neutronium, function() { return "Nt"; });

val ingot = <shapeKey:ingot>;

print("Loaded CrT compat scripts!!");
