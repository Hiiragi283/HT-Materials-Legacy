package hiiragi283.materials.common.item;

import hiiragi283.materials.api.HMReferences;
import hiiragi283.materials.api.HTMaterialsAPI;
import hiiragi283.materials.api.mateial.HTMaterialProvider;
import hiiragi283.materials.api.mateial.part.HTPart;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
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

        }
    }

    //    HTMaterialProvider    //

    @Override
    public @NotNull HTPart getPart() {
        return part;
    }
}
