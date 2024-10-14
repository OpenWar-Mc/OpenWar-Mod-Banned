package com.openwar.charpy.Handler;

import net.minecraft.item.ItemStack;
import net.minecraft.init.Items;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

public class TooltipHandler {
    List<ItemStack> items;

public TooltipHandler (List<ItemStack> items) {
    this.items = items;
}
    @SubscribeEvent
    public void onTooltip(ItemTooltipEvent event) {
        ItemStack itemStack = event.getItemStack();
        for (ItemStack bannedItem : items) {
            if (ItemStack.areItemStacksEqual(itemStack, bannedItem)) {
                List<String> tooltip = event.getToolTip();
                tooltip.clear();
                tooltip.add("§8» §4§lBANNED ITEM §8«");
                tooltip.add("§7This item will be removed automatically from your inventory");
                break;
            }
        }
    }
}