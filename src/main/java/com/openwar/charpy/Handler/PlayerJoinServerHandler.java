package com.openwar.charpy.Handler;

import com.openwar.charpy.RPC.RichPresence;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.PlayerList;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

public class PlayerJoinServerHandler {
    private static RichPresence rpc;
    private static boolean isConnected;
    private static Thread updateThread;

    public static boolean isPlayerConnected() { return isConnected; }

    public static void setRPC(RichPresence presence) {
        rpc = presence;
    }

    @SubscribeEvent
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        EntityPlayerMP player = (EntityPlayerMP) event.player;
        MinecraftServer server = player.getServer();

        if (server != null) {
            isConnected = true;
            startUpdateThread(server);
        }
    }

    @SubscribeEvent
    public static void onPlayerDisconnect(PlayerEvent.PlayerLoggedOutEvent event) {
        isConnected = false;
        stopUpdateThread();
    }

    private static void startUpdateThread(MinecraftServer server) {
        if (updateThread == null || !updateThread.isAlive()) {
            updateThread = new Thread(() -> {
                while (isConnected) {
                    PlayerList playerList = server.getPlayerList();
                    int onlinePlayers = playerList.getCurrentPlayerCount();
                    int maxPlayers = playerList.getMaxPlayers();
                    String details = "Playing on OpenWar";
                    String state = onlinePlayers + "/" + maxPlayers;
                    rpc.updatePresence(details, state, "original_openwar", "Join Us !", "", "");

                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "RPC-Update-Thread");
            updateThread.start();
        }
    }

    private static void stopUpdateThread() {
        if (updateThread != null && updateThread.isAlive()) {
            updateThread.interrupt();
            updateThread = null;
        }
    }
}
