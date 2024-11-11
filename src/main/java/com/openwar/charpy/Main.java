package com.openwar.charpy;

import com.openwar.charpy.Commands.CommandInfo;
import com.openwar.charpy.Entity.EntityParachute;
import com.openwar.charpy.Entity.EntityPlane;
import com.openwar.charpy.Handler.CommandSpawnParachute;
import com.openwar.charpy.Handler.JoinQuitHandler;
import com.openwar.charpy.Handler.NetworkHandler;
import com.openwar.charpy.Hud.HudWarzone;
import com.openwar.charpy.Proxies.CommonProxy;
import com.openwar.charpy.RPC.RichPresence;
import com.openwar.charpy.openwarbanned.Tags;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

@Mod(modid = Tags.MOD_ID, name = Tags.MOD_NAME, version = Tags.VERSION)
public class Main {

    public static final Logger LOGGER = LogManager.getLogger(Tags.MOD_NAME);
    @SidedProxy(clientSide = "com.openwar.charpy.Proxies.ClientProxy", serverSide = "com.openwar.charpy.Proxies.ServerProxy")
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
        commonProxy.registerRenders();
        commonProxy.registerEntity();
        EntityRegistry.registerModEntity(new ResourceLocation("openwarbanned", "plane"), EntityPlane.class, "Plane", 2, Tags.MOD_ID, 500, 1, true);
        EntityRegistry.registerModEntity(new ResourceLocation("openwarbanned", "parachute"), EntityParachute.class, "Parachute", 1, this, 300, 1, true);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
    }
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        LOGGER.info("OpenWar Banned loading {}!", Tags.MOD_NAME);
        MinecraftForge.EVENT_BUS.register(new HudWarzone());
        MinecraftForge.EVENT_BUS.register(JoinQuitHandler.class);
        commonProxy.registerEventHandlers();
        NetworkHandler.init();
    }
    @Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandSpawnParachute());
        event.registerServerCommand(new CommandInfo());
    }
}
