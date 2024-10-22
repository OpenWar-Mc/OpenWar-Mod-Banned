package com.openwar.charpy.Proxies;

import com.openwar.charpy.Entity.EntityParachute;
import com.openwar.charpy.Entity.EntityPlane;
import com.openwar.charpy.Handler.EventBanned;
import com.openwar.charpy.Handler.PlayerEventHandler;
import com.openwar.charpy.Main;
import com.openwar.charpy.Utils.ItemLoader;
import com.openwar.charpy.openwarbanned.Tags;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.registry.EntityRegistry;

import java.util.List;

public abstract class CommonProxy {
    public void registerEntity() {
    }
    public abstract void registerRenders();

    public void registerEventHandlers() {
        ItemLoader il = new ItemLoader();
        List<ItemStack> items = il.readItemsFromCSV();
        List<String> admin= il.readAdminsFromCSV();
        MinecraftForge.EVENT_BUS.register(new EventBanned(items, admin));
    }
}