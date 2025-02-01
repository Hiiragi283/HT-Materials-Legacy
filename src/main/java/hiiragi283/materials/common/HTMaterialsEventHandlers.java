package hiiragi283.materials.common;

import hiiragi283.materials.api.HMReferences;
import hiiragi283.materials.api.event.HTRegisterMaterialEvent;
import hiiragi283.materials.api.mateial.DefaultMaterials;
import hiiragi283.materials.common.init.HMItems;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod.EventBusSubscriber(modid = HMReferences.MOD_ID)
public final class HTMaterialsEventHandlers {

    private static final Logger LOGGER = LogManager.getLogger(HTMaterialsEventHandlers.class.getSimpleName());

    @SubscribeEvent
    public static void onRegisterMaterial(final HTRegisterMaterialEvent event) {
        // Metal
        event.register(DefaultMaterials.IRON, 26);
        event.register(DefaultMaterials.COPPER, 29);
        event.register(DefaultMaterials.GOLD, 79);

        LOGGER.info("Registered default materials!");
    }

    @SubscribeEvent
    public static void onItemRegister(final RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();

        HMItems.ITEMS.forEach(registry::register);

        LOGGER.info("Registered part items!");
    }
}
