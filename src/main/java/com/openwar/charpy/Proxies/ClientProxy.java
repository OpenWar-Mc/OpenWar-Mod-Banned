package com.openwar.charpy.Proxies;

import com.openwar.charpy.Handler.GuiEventHandler;
import com.openwar.charpy.Handler.TooltipHandler;
import com.openwar.charpy.Utils.ItemLoader;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;

import java.util.List;

public class ClientProxy extends CommonProxy {
    @Override
    public void registerEventHandlers() {
        super.registerEventHandlers();
        ItemLoader il = new ItemLoader();
        List<ItemStack> items = il.readItemsFromCSV();
        List<String> admin= il.readAdminsFromCSV();
        MinecraftForge.EVENT_BUS.register(new TooltipHandler(items));
        MinecraftForge.EVENT_BUS.register(new GuiEventHandler(items, admin));
    }
}