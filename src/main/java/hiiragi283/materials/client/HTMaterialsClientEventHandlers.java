package hiiragi283.materials.client;

import hiiragi283.materials.api.HMReferences;
import hiiragi283.materials.api.HTMaterialsAPI;
import hiiragi283.materials.api.mateial.DefaultMaterialProperties;
import hiiragi283.materials.api.mateial.HTMaterialKey;
import hiiragi283.materials.api.mateial.HTMaterialRegistry;
import hiiragi283.materials.api.mateial.part.HTPart;
import hiiragi283.materials.api.mateial.property.HTMaterialModelFunction;
import hiiragi283.materials.api.property.HTPropertyHolder;
import hiiragi283.materials.common.init.HMItems;
import hiiragi283.materials.common.item.ItemPartMaterial;
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

import java.awt.*;
import java.util.OptionalInt;

@Mod.EventBusSubscriber(modid = HMReferences.MOD_ID, value = Side.CLIENT)
public final class HTMaterialsClientEventHandlers {

    private static final Logger LOGGER = LogManager.getLogger(HTMaterialsClientEventHandlers.class.getSimpleName());

    @SubscribeEvent
    public static void onRegisterModel(ModelRegistryEvent event) {
        HTMaterialRegistry registry = HTMaterialsAPI.INSTANCE.getMaterialRegistry();
        for (HTMaterialKey material : registry.getMaterials()) {
            OptionalInt index = registry.getIndex(material);
            if (index.isPresent()) {
                HTPropertyHolder holder = registry.getPropertyHolder(material);
                HTMaterialModelFunction modelFunction = holder.getPropertyOrDefault(DefaultMaterialProperties.MODEL, (HTPart part, HTMaterialKey key) -> HTMaterialsAPI.id(part.name));
                for (ItemPartMaterial item : HMItems.ITEMS) {
                    ModelLoader.setCustomModelResourceLocation(
                            item,
                            index.getAsInt(),
                            new ModelResourceLocation(modelFunction.apply(item.getPart(), material), "inventory")
                    );
                }
            }
        }

        LOGGER.info("Registered item models!");
    }
    
    @SubscribeEvent
    public static void onRegisterItemColor(ColorHandlerEvent.Item event) {
        IItemColor itemColor = (ItemStack stack, int tintIndex) -> {
            if (tintIndex != 1) return -1;
            HTMaterialRegistry registry = HTMaterialsAPI.INSTANCE.getMaterialRegistry();
            return registry.getPropertyFromIndex(stack.getMetadata())
                    .getOptional(DefaultMaterialProperties.COLOR)
                    .map(Color::getRGB)
                    .orElse(-1);
        };

        event.getItemColors().registerItemColorHandler(
                itemColor,
                HMItems.DUST,
                HMItems.INGOT,
                HMItems.PLATE
        );

        LOGGER.info("Registered Item Colors!");
    }
}
