package com.openwar.charpy.Handler;

import com.openwar.charpy.Main;
import com.openwar.charpy.Network.PacketDeleteItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

@SideOnly(Side.CLIENT)
public class GuiEventHandler {
    List<ItemStack> items;
    List<String> admin;

    public GuiEventHandler(List<ItemStack> items, List<String> admin) {
        this.items = items;
        this.admin = admin;
    }

    @SubscribeEvent
    public void onClickInventory(GuiScreenEvent.MouseInputEvent event) {
        if (Minecraft.getMinecraft().currentScreen instanceof GuiContainer) {
            EntityPlayer player = Minecraft.getMinecraft().player;
            InventoryPlayer inv = player.inventory;
            if (!admin.contains(player.getDisplayNameString())) {
                for (int i = 0; i < inv.getSizeInventory(); i++) {
                    ItemStack currentStack = inv.getStackInSlot(i);
                    if (!currentStack.isEmpty()) {
                        for (ItemStack bannedItem : items) {
                            if (ItemStack.areItemsEqual(currentStack, bannedItem)) {
                                Main.network.sendToServer(new PacketDeleteItem(i));
                                System.out.println("Sent delete request to server");
                                break;
                            }
                        }
                    }
                }
            }
        }
    }
}
