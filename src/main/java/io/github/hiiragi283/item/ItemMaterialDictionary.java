package io.github.hiiragi283.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import org.jetbrains.annotations.NotNull;

import com.cleanroommc.modularui.api.IGuiHolder;
import com.cleanroommc.modularui.factory.HandGuiData;
import com.cleanroommc.modularui.factory.ItemGuiFactory;
import com.cleanroommc.modularui.screen.ModularPanel;
import com.cleanroommc.modularui.value.sync.GuiSyncManager;

import io.github.hiiragi283.api.HTMaterialsAPI;

public class ItemMaterialDictionary extends Item implements IGuiHolder<HandGuiData> {

    public static final Item INSTANCE = new ItemMaterialDictionary();

    private ItemMaterialDictionary() {
        setCreativeTab(HTMaterialsAPI.INSTANCE.creativeTab());
        setRegistryName(HTMaterialsAPI.MOD_ID, "material_dictionary");
    }

    @NotNull
    @Override
    public ActionResult<ItemStack> onItemRightClick(@NotNull World worldIn, @NotNull EntityPlayer playerIn,
                                                    @NotNull EnumHand handIn) {
        if (!worldIn.isRemote) {
            ItemGuiFactory.open((EntityPlayerMP) playerIn, handIn);
        }
        return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
    }

    // IGuiHolder

    @Override
    public ModularPanel buildUI(HandGuiData data, GuiSyncManager syncManager) {
        return null;
    }
}
