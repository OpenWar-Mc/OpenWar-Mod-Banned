package com.openwar.charpy.Handler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.event.entity.player.PlayerContainerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import java.util.List;

public class EventBanned {
    List<ItemStack> items;
    List<String> admin;

    public EventBanned(List<ItemStack> items, List<String> admin) {
        this.items = items;
        this.admin = admin;
    }

    @SubscribeEvent
    public void onClickOn(PlayerInteractEvent.RightClickItem event) {
        ItemStack itemStack = event.getItemStack();
        EntityPlayer player = event.getEntityPlayer();
        if (!admin.contains(player.getDisplayNameString())) {
            for (ItemStack bannedItem : items) {
                if (ItemStack.areItemStacksEqual(itemStack, bannedItem)) {
                    InventoryPlayer inv = event.getEntityPlayer().inventory;
                    inv.deleteStack(event.getItemStack());
                }
            }
        }
    }

    @SubscribeEvent
    public void onOpenContainer(PlayerContainerEvent.Open event) {
        InventoryPlayer inv = event.getEntityPlayer().inventory;
        EntityPlayer player = event.getEntityPlayer();
        if (admin == null) {return;}
        if (!admin.contains(player.getDisplayNameString())) {
            for (int i = 0; i < inv.getSizeInventory(); i++) {
                ItemStack currentStack = inv.getStackInSlot(i);
                if (!currentStack.isEmpty()) {
                    for (ItemStack bannedItem : items) {
                        if (ItemStack.areItemsEqual(currentStack, bannedItem)) {
                            inv.deleteStack(currentStack);
                            break;
                        }
                    }
                }
            }
        }
    }
}

