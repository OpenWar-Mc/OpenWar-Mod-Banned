package com.openwar.charpy.Handler;

import com.openwar.charpy.Network.PacketRPC;
import com.openwar.charpy.RPC.RichPresence;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.PlayerList;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class JoinQuitHandler {

    private static RichPresence rpc;
    private static boolean isConnected;
    private static ScheduledExecutorService scheduler;

    public static void setRPC(RichPresence presence) {
        rpc = presence;
    }

    @SubscribeEvent
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        EntityPlayerMP player = (EntityPlayerMP) event.player;
        MinecraftServer server = player.getServer();
        if (server != null) {
            isConnected = true;
            startUpdateTask(server, player);
        }
    }

    @SubscribeEvent
    public static void onPlayerDisconnect(PlayerEvent.PlayerLoggedOutEvent event) {
        isConnected = false;
        EntityPlayerMP player = (EntityPlayerMP) event.player;
        stopUpdateTask(player);
    }

    private static void startUpdateTask(MinecraftServer server, EntityPlayerMP player) {
        if (scheduler == null) {
            scheduler = Executors.newSingleThreadScheduledExecutor();
            scheduler.scheduleAtFixedRate(() -> {
                if (isConnected) {
                    PlayerList playerList = server.getPlayerList();
                    int onlinePlayers = playerList.getCurrentPlayerCount();
                    int maxPlayers = playerList.getMaxPlayers();
                    String details = "Playing on OpenWar";
                    String state = onlinePlayers + "/" + maxPlayers;

                    NetworkHandler.INSTANCE.sendTo(new PacketRPC(details, state), player);
                }
            }, 0, 2, TimeUnit.SECONDS);
        }
    }

    private static void stopUpdateTask(EntityPlayerMP player) {
        String details = "Main Menu";
        String state = "";
        NetworkHandler.INSTANCE.sendTo(new PacketRPC(details, state), player);
        NetworkHandler.INSTANCE.sendTo(new PacketRPC(details, state), player);
        if (scheduler != null) {
            scheduler.shutdownNow();
            scheduler = null;
        }
    }
}
