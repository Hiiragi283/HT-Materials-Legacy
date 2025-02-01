package hiiragi283.materials.client;

import java.awt.*;
import java.util.OptionalInt;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import hiiragi283.materials.api.HMReferences;
import hiiragi283.materials.api.HTMaterialsAPI;
import hiiragi283.materials.api.mateial.DefaultMaterialProperties;
import hiiragi283.materials.api.mateial.HTMaterialKey;
import hiiragi283.materials.api.mateial.HTMaterialRegistry;
import hiiragi283.materials.api.mateial.item.HTMaterialItem;
import hiiragi283.materials.api.mateial.part.HTPart;
import hiiragi283.materials.api.mateial.property.HTMaterialModelFunction;
import hiiragi283.materials.api.property.HTPropertyHolder;

@Mod.EventBusSubscriber(modid = HMReferences.MOD_ID, value = Side.CLIENT)
public final class HMClientEventHandlers {

  private static final Logger LOGGER =
      LogManager.getLogger(HMClientEventHandlers.class.getSimpleName());

  @SubscribeEvent
  public static void onRegisterModel(ModelRegistryEvent event) {
    HTMaterialRegistry registry = HTMaterialsAPI.INSTANCE.getMaterialRegistry();
    for (HTMaterialItem item : HTMaterialsAPI.INSTANCE.getPartItemMap().values()) {
      item.getValidMaterials()
          .forEach(
              (HTMaterialKey material) -> {
                OptionalInt index = registry.getIndex(material);
                if (index.isPresent()) {
                  HTPropertyHolder holder = registry.getPropertyHolder(material);
                  HTMaterialModelFunction modelFunction =
                      holder.getPropertyOrDefault(
                          DefaultMaterialProperties.MODEL,
                          (HTPart part, HTMaterialKey key) -> HTMaterialsAPI.id(part.name));
                  ModelLoader.setCustomModelResourceLocation(
                      item.asItem(),
                      index.getAsInt(),
                      new ModelResourceLocation(
                          modelFunction.apply(item.getPart(), material), "inventory"));
                }
              });
    }

    LOGGER.info("Registered item models!");
  }

  @SubscribeEvent
  public static void onRegisterItemColor(ColorHandlerEvent.Item event) {
    IItemColor itemColor =
        (ItemStack stack, int tintIndex) -> {
          if (tintIndex != 0) return -1;
          HTMaterialRegistry registry = HTMaterialsAPI.INSTANCE.getMaterialRegistry();
          return registry
              .getPropertyFromIndex(stack.getMetadata())
              .getOptional(DefaultMaterialProperties.COLOR)
              .map(Color::getRGB)
              .orElse(-1);
        };

    for (HTMaterialItem item : HTMaterialsAPI.INSTANCE.getPartItemMap().values()) {
      event.getItemColors().registerItemColorHandler(itemColor, item.asItem());
    }

    LOGGER.info("Registered Item Colors!");
  }
}
