package com.openwar.charpy.RPC;

import net.arikia.dev.drpc.DiscordRPC;
import net.arikia.dev.drpc.DiscordRichPresence;
import net.arikia.dev.drpc.DiscordEventHandlers;

public class RichPresence {

    private boolean running = true;

    public RichPresence(String clientId) {
        DiscordEventHandlers handlers = new DiscordEventHandlers.Builder().setReadyEventHandler(user -> {}).build();
        DiscordRPC.discordInitialize(clientId, handlers, true);
        startUpdateThread();
    }

    private void startUpdateThread() {
        new Thread(() -> {
            while (running) {
                DiscordRPC.discordRunCallbacks();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Discord-RPC-Callback-Thread").start();
    }

    public void updatePresence(String details, String state, String largeImageKey, String largeImageText, String smallImageKey, String smallImageText) {
        DiscordRichPresence.Builder presence = new DiscordRichPresence.Builder(state)
                .setDetails(details)
                .setBigImage(largeImageKey, largeImageText)
                .setSmallImage(smallImageKey, smallImageText);
        DiscordRPC.discordUpdatePresence(presence.build());
    }

    public void shutdown() {
        running = false;
        DiscordRPC.discordShutdown();
    }
}