package hiiragi283.materials.common;

import hiiragi283.materials.api.HMReferences;
import hiiragi283.materials.api.event.HTRegisterMaterialEvent;
import hiiragi283.materials.api.event.HTSetupPropertiesEvent;
import hiiragi283.materials.api.mateial.DefaultMaterialProperties;
import hiiragi283.materials.api.mateial.DefaultMaterials;
import hiiragi283.materials.api.mateial.property.HTMaterialGroup;
import hiiragi283.materials.common.init.HMItems;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;

@Mod.EventBusSubscriber(modid = HMReferences.MOD_ID)
public final class HTMaterialsEventHandlers {

    private static final Logger LOGGER = LogManager.getLogger(HTMaterialsEventHandlers.class.getSimpleName());

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onRegisterMaterial(final HTRegisterMaterialEvent event) {
        // Periodic Table
        event.register(DefaultMaterials.CARBON, 6);

        event.register(DefaultMaterials.ALUMINUM, 13);

        event.register(DefaultMaterials.IRON, 26);
        event.register(DefaultMaterials.COBALT, 27);
        event.register(DefaultMaterials.NICKEL, 28);
        event.register(DefaultMaterials.COPPER, 29);
        event.register(DefaultMaterials.ZINC, 30);

        event.register(DefaultMaterials.SILVER, 47);
        event.register(DefaultMaterials.TIN, 50);

        event.register(DefaultMaterials.OSMIUM, 76);
        event.register(DefaultMaterials.IRIDIUM, 77);
        event.register(DefaultMaterials.PLATINUM, 78);
        event.register(DefaultMaterials.GOLD, 79);
        event.register(DefaultMaterials.LEAD, 82);

        // Carbon
        event.register(DefaultMaterials.COAL, 600);
        event.register(DefaultMaterials.CHARCOAL, 601);
        // Iron
        event.register(DefaultMaterials.STEEL, 2600);
        // Copper
        event.register(DefaultMaterials.BRASS, 2900);
        event.register(DefaultMaterials.BRONZE, 2901);
        // Gold
        event.register(DefaultMaterials.ELECTRUM, 7900);

        LOGGER.info("Registered default materials!");
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onSetupProperties(final HTSetupPropertiesEvent event) {
        // Periodic Table
        event.getOrCreate(DefaultMaterials.CARBON)
                .put(DefaultMaterialProperties.COLOR, new Color(0x131313))
                .put(DefaultMaterialProperties.GROUP, HTMaterialGroup.DUST);

        event.getOrCreate(DefaultMaterials.ALUMINUM)
                .put(DefaultMaterialProperties.COLOR, new Color(0xc7cfdd))
                .put(DefaultMaterialProperties.GROUP, HTMaterialGroup.METAL);

        event.getOrCreate(DefaultMaterials.IRON)
                .put(DefaultMaterialProperties.COLOR, new Color(0xb4b4b4))
                .put(DefaultMaterialProperties.GROUP, HTMaterialGroup.METAL_VANILLA);

        event.getOrCreate(DefaultMaterials.COBALT)
                .put(DefaultMaterialProperties.COLOR, new Color(0x0955a8))
                .put(DefaultMaterialProperties.GROUP, HTMaterialGroup.METAL);

        event.getOrCreate(DefaultMaterials.NICKEL)
                .put(DefaultMaterialProperties.COLOR, new Color(0xf9e6cf))
                .put(DefaultMaterialProperties.GROUP, HTMaterialGroup.METAL);

        event.getOrCreate(DefaultMaterials.COPPER)
                .put(DefaultMaterialProperties.COLOR, new Color(0xc15a36))
                .put(DefaultMaterialProperties.GROUP, HTMaterialGroup.METAL);

        event.getOrCreate(DefaultMaterials.ZINC)
                .put(DefaultMaterialProperties.COLOR, new Color(0xd0ddbb))
                .put(DefaultMaterialProperties.GROUP, HTMaterialGroup.METAL);

        event.getOrCreate(DefaultMaterials.SILVER)
                .put(DefaultMaterialProperties.COLOR, new Color(0xc4d2d5))
                .put(DefaultMaterialProperties.GROUP, HTMaterialGroup.METAL);

        event.getOrCreate(DefaultMaterials.TIN)
                .put(DefaultMaterialProperties.GROUP, HTMaterialGroup.METAL);

        event.getOrCreate(DefaultMaterials.OSMIUM)
                .put(DefaultMaterialProperties.COLOR, new Color(0x92a1b9))
                .put(DefaultMaterialProperties.GROUP, HTMaterialGroup.METAL)
                .put(DefaultMaterialProperties.RARITY, EnumRarity.RARE);

        event.getOrCreate(DefaultMaterials.IRIDIUM)
                .put(DefaultMaterialProperties.COLOR, new Color(0xc5cae9))
                .put(DefaultMaterialProperties.GROUP, HTMaterialGroup.METAL)
                .put(DefaultMaterialProperties.RARITY, EnumRarity.RARE);

        event.getOrCreate(DefaultMaterials.PLATINUM)
                .put(DefaultMaterialProperties.COLOR, new Color(0x94fdff))
                .put(DefaultMaterialProperties.GROUP, HTMaterialGroup.METAL)
                .put(DefaultMaterialProperties.RARITY, EnumRarity.RARE);

        event.getOrCreate(DefaultMaterials.GOLD)
                .put(DefaultMaterialProperties.COLOR, new Color(0xffff00))
                .put(DefaultMaterialProperties.GROUP, HTMaterialGroup.METAL_VANILLA);

        event.getOrCreate(DefaultMaterials.LEAD)
                .put(DefaultMaterialProperties.COLOR, new Color(0x424c6e))
                .put(DefaultMaterialProperties.GROUP, HTMaterialGroup.METAL);

        // Carbon
        event.getOrCreate(DefaultMaterials.COAL)
                .put(DefaultMaterialProperties.BURN_TIME, 1600)
                .put(DefaultMaterialProperties.COLOR, new Color(0x1b1b1b))
                .put(DefaultMaterialProperties.GROUP, HTMaterialGroup.DUST);

        event.getOrCreate(DefaultMaterials.CHARCOAL)
                .put(DefaultMaterialProperties.BURN_TIME, 1600)
                .put(DefaultMaterialProperties.COLOR, new Color(0x1f1d1a))
                .put(DefaultMaterialProperties.GROUP, HTMaterialGroup.DUST);

        // Iron
        event.getOrCreate(DefaultMaterials.STEEL)
                .put(DefaultMaterialProperties.COLOR, new Color(0x5d5d5d))
                .put(DefaultMaterialProperties.GROUP, HTMaterialGroup.METAL);

        // Copper
        event.getOrCreate(DefaultMaterials.BRASS)
                .put(DefaultMaterialProperties.COLOR, new Color(0xffa214))
                .put(DefaultMaterialProperties.GROUP, HTMaterialGroup.METAL);

        event.getOrCreate(DefaultMaterials.BRONZE)
                .put(DefaultMaterialProperties.COLOR, new Color(0xed7614))
                .put(DefaultMaterialProperties.GROUP, HTMaterialGroup.METAL);
        // Gold
        event.getOrCreate(DefaultMaterials.ELECTRUM)
                .put(DefaultMaterialProperties.COLOR, new Color(0xffeb57))
                .put(DefaultMaterialProperties.GROUP, HTMaterialGroup.METAL);

        LOGGER.info("Modified material properties!");
    }

    @SubscribeEvent
    public static void onItemRegister(final RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();

        HMItems.ITEMS.forEach(registry::register);

        LOGGER.info("Registered part items!");
    }
}
