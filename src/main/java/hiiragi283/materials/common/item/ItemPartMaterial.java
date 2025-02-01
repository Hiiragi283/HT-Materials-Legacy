package hiiragi283.materials.common.item;

import hiiragi283.materials.api.HMReferences;
import hiiragi283.materials.api.HTMaterialsAPI;
import hiiragi283.materials.api.mateial.DefaultMaterialProperties;
import hiiragi283.materials.api.mateial.HTMaterialProvider;
import hiiragi283.materials.api.mateial.part.HTPart;
import hiiragi283.materials.api.util.HTItemUtils;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.IRarity;
import org.jetbrains.annotations.NotNull;

public final class ItemPartMaterial extends Item implements HTMaterialProvider {

    private final @NotNull HTPart part;

    public ItemPartMaterial(HTPart part) {
        this.part = part;
        setCreativeTab(HTMaterialsAPI.CREATIVE_TABS);
        setHasSubtypes(true);
        setRegistryName(HMReferences.MOD_ID, part.name);
    }

    @Override
    public @NotNull String getItemStackDisplayName(@NotNull ItemStack stack) {
        var material = getMaterialKey(stack.getMetadata());
        return material != null ? part.createTranslatedName(material) : super.getItemStackDisplayName(stack);
    }

    @Override
    public void getSubItems(@NotNull CreativeTabs tab, @NotNull NonNullList<ItemStack> items) {
        if (this.isInCreativeTab(tab)) {
            getValidMaterials()
                    .map(this::getStackFromMaterial)
                    .filter(HTItemUtils::isNotEmpty)
                    .forEach(items::add);
        }
    }

    @Override
    public boolean isBeaconPayment(@NotNull ItemStack stack) {
        return true;
    }

    @Override
    public int getItemBurnTime(@NotNull ItemStack stack) {
        var material = getMaterialKey(stack.getMetadata());
        if (material != null) {
            Integer burnTime = getRegistry().getPropertyHolder(material).getProperty(DefaultMaterialProperties.BURN_TIME);
            if (burnTime != null) {
                return burnTime;
            }
        }
        return super.getItemBurnTime(stack);
    }

    @Override
    public @NotNull IRarity getForgeRarity(@NotNull ItemStack stack) {
        var material = getMaterialKey(stack.getMetadata());
        if (material != null) {
            IRarity rarity = getRegistry().getPropertyHolder(material).getProperty(DefaultMaterialProperties.RARITY);
            if (rarity != null) {
                return rarity;
            }
        }
        return super.getForgeRarity(stack);
    }

    //    HTMaterialProvider    //

    @Override
    public @NotNull HTPart getPart() {
        return part;
    }

    @Override
    public @NotNull Item asItem() {
        return this;
    }
}
