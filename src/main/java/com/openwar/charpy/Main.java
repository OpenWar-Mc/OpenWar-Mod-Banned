package com.openwar.charpy;

import com.openwar.charpy.Handler.EventBanned;
import com.openwar.charpy.Handler.TooltipHandler;
import com.openwar.charpy.Network.PacketDeleteItem;
import com.openwar.charpy.Proxies.CommonProxy;
import com.openwar.charpy.Utils.ItemLoader;
import com.openwar.charpy.openwarbanned.Tags;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

@Mod(modid = Tags.MOD_ID, name = Tags.MOD_NAME, version = Tags.VERSION)
public class Main {

    public static final Logger LOGGER = LogManager.getLogger(Tags.MOD_NAME);
    @SidedProxy(clientSide = "com.openwar.charpy.Proxies.ClientProxy", serverSide = "com.openwar.charpy.Proxies.CommonProxy")
    public static CommonProxy commonProxy;
    public static SimpleNetworkWrapper network;
    List<ItemStack> items;
    List<String> admin;

    /**
     * <a href="https://cleanroommc.com/wiki/forge-mod-development/event#overview">
     *     Take a look at how many FMLStateEvents you can listen to via the @Mod.EventHandler annotation here
     * </a>
     */
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    }
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        LOGGER.info("OpenWar Banned loading {}!", Tags.MOD_NAME);
        commonProxy.registerEventHandlers();
        network = NetworkRegistry.INSTANCE.newSimpleChannel("openwar");
        network.registerMessage(PacketDeleteItem.Handler.class, PacketDeleteItem.class, 0, Side.SERVER);
    }
}
