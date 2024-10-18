package com.openwar.charpy.Handler;

import com.openwar.charpy.openwarbanned.Tags;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.init.Items;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
        if (items == null) {
            Logger LOGGER = LogManager.getLogger(Tags.MOD_NAME);
            LOGGER.warn("ITEM IS NULL !");
            return;
        }
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