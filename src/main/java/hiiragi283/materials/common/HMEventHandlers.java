package hiiragi283.materials.common;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import hiiragi283.materials.api.HMReferences;
import hiiragi283.materials.api.HTMaterialsAPI;
import hiiragi283.materials.api.HTRegisterAddonEvent;
import hiiragi283.materials.api.mateial.item.HTMaterialItem;
import hiiragi283.materials.integration.HMIntegrationEIO;
import hiiragi283.materials.integration.HMIntegrationTCon;
import hiiragi283.materials.integration.HMIntegrationTE;

@Mod.EventBusSubscriber(modid = HMReferences.MOD_ID)
public final class HMEventHandlers {

  private static final Logger LOGGER = LogManager.getLogger(HMEventHandlers.class.getSimpleName());

  @SubscribeEvent(priority = EventPriority.HIGHEST)
  public static void onRegisterAddons(final HTRegisterAddonEvent event) {
    event.register(
        HMIntegrationCommon.INSTANCE,
        HMIntegrationEIO.INSTANCE,
        HMIntegrationTCon.INSTANCE,
        HMIntegrationTE.INSTANCE);
  }

  @SubscribeEvent
  public static void onItemRegister(final RegistryEvent.Register<Item> event) {
    IForgeRegistry<Item> registry = event.getRegistry();

    HTMaterialsAPI.INSTANCE.getPartItemMap().values().stream()
        .map(HTMaterialItem::asItem)
        .forEach(registry::register);

    LOGGER.info("Registered part items!");
  }
}
