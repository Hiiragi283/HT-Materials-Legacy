package hiiragi283.materials.api;

import hiiragi283.materials.api.mateial.HTMaterialRegistry;
import hiiragi283.materials.common.HTMaterialsAPIImpl;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

@ApiStatus.NonExtendable
public interface HTMaterialsAPI {
    @NotNull HTMaterialsAPI INSTANCE = new HTMaterialsAPIImpl();

    @NotNull CreativeTabs CREATIVE_TABS = new CreativeTabs("ht_materials.materials") {
        @Override
        public @NotNull ItemStack createIcon() {
            return new ItemStack(Items.IRON_INGOT);
        }
    };

    static @NotNull ResourceLocation id(@NotNull final String path) {
        return new ResourceLocation(HMReferences.MOD_ID, path);
    }

    @NotNull HTMaterialRegistry getMaterialRegistry();
}
