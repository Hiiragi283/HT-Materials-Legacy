package io.github.hiiragi283.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemStackHandler;

import org.jetbrains.annotations.NotNull;

import com.cleanroommc.modularui.api.IGuiHolder;
import com.cleanroommc.modularui.factory.HandGuiData;
import com.cleanroommc.modularui.factory.ItemGuiFactory;
import com.cleanroommc.modularui.screen.ModularPanel;
import com.cleanroommc.modularui.utils.Alignment;
import com.cleanroommc.modularui.value.sync.GuiSyncManager;
import com.cleanroommc.modularui.widget.ParentWidget;
import com.cleanroommc.modularui.widgets.ButtonWidget;
import com.cleanroommc.modularui.widgets.ItemSlot;
import com.cleanroommc.modularui.widgets.SlotGroupWidget;
import com.cleanroommc.modularui.widgets.layout.Column;

import io.github.hiiragi283.api.HTMaterialsAPI;

public final class ItemMaterialDictionary extends Item implements IGuiHolder<HandGuiData> {

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
        return ModularPanel.defaultPanel("material_dictionary").child(new Column().margin(7)
                .child(new ParentWidget<>().widthRel(1.0f).expanded()
                        .child(SlotGroupWidget.builder()
                                .row("<I>")
                                .key('<', index -> new ButtonWidget<>()
                                        .onMousePressed(mouseButton -> true))
                                .key('I', index -> new ItemSlot().slot(new ItemStackHandler(), index))
                                .key('>', index -> new ButtonWidget<>()
                                        .onMousePressed(mouseButton -> true))
                                .build()
                                .align(Alignment.Center)))
                .child(SlotGroupWidget.playerInventory(0)));
    }
}
