import io.github.hiiragi283.material.api.material.FormulaConvertible
import io.github.hiiragi283.material.api.material.HTMaterialEvent
import io.github.hiiragi283.material.api.material.HTMaterialKey
import io.github.hiiragi283.material.api.material.flag.HTMaterialFlags
import io.github.hiiragi283.material.api.material.materials.HTElementMaterials
import io.github.hiiragi283.material.api.material.property.HTMetalProperty
import net.minecraftforge.fml.common.eventhandler.EventPriority

def infinityKey = materialKey("infinity", 32000) as HTMaterialKey

event_manager.listen(EventPriority.NORMAL, { HTMaterialEvent.Register event ->
    event.registry.add(infinityKey)
})

event_manager.listen(EventPriority.NORMAL, { HTMaterialEvent.Property event ->
    def registry = event.registry
    registry.getOrCreate(infinityKey)
            .add(HTMetalProperty.INSTANCE)
})

event_manager.listen(EventPriority.NORMAL, { HTMaterialEvent.Flag event ->
    def registry = event.registry
    registry.getOrCreate(infinityKey)
            .add(HTMaterialFlags.GENERATE_DUST)
            .add(HTMaterialFlags.GENERATE_GEAR)
            .add(HTMaterialFlags.GENERATE_INGOT)
            .add(HTMaterialFlags.GENERATE_NUGGET)
            .add(HTMaterialFlags.GENERATE_PLATE)
    registry.getOrCreate(HTElementMaterials.HYDROGEN)
            .add(HTMaterialFlags.GENERATE_DUST)
})

event_manager.listen(EventPriority.NORMAL, { HTMaterialEvent.Formula event ->
    event.put(infinityKey, FormulaConvertible.ofString("INFINITY"))
})