package com.openwar.charpy;

import com.openwar.charpy.Handler.EventBanned;
import com.openwar.charpy.Handler.TooltipHandler;
import com.openwar.charpy.Proxies.CommonProxy;
import com.openwar.charpy.Utils.ItemLoader;
import com.openwar.charpy.openwarbanned.Tags;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

@Mod(modid = Tags.MOD_ID, name = Tags.MOD_NAME, version = Tags.VERSION)
public class Main {

    public static final Logger LOGGER = LogManager.getLogger(Tags.MOD_NAME);
    public static CommonProxy commonProxy;
    public static SimpleNetworkWrapper network;

    /**
     * <a href="https://cleanroommc.com/wiki/forge-mod-development/event#overview">
     *     Take a look at how many FMLStateEvents you can listen to via the @Mod.EventHandler annotation here
     * </a>
     */
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        LOGGER.info("OpenWar Banned loading {}!", Tags.MOD_NAME);
    }
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        ItemLoader il = new ItemLoader();
        List<ItemStack> items = il.readItemsFromCSV();
        MinecraftForge.EVENT_BUS.register(new TooltipHandler(items));
        MinecraftForge.EVENT_BUS.register(new EventBanned(items));
    }
}
