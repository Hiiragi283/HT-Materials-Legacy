package io.github.hiiragi283;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;

import org.jetbrains.annotations.NotNull;

@Mod(modid = HMReference.MOD_ID,
     name = HMReference.MOD_NAME,
     version = HMReference.VERSION)
public final class HTMaterialsMod {

    public static final CreativeTabs CREATIVE_TABS = new CreativeTabs("") {

        @NotNull
        @Override
        public ItemStack createIcon() {
            return ItemStack.EMPTY;
        }
    };

    @Mod.EventHandler
    public void onConstruct() {
        HTMaterialsCore.initAddons();
    }

    @Mod.EventHandler
    public void onPreInit() {}

    @Mod.EventHandler
    public void onInit() {}

    @Mod.EventHandler
    public void onPostInit() {}
}