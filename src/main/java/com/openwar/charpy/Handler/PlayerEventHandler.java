package com.openwar.charpy.Handler;

import com.openwar.charpy.Network.PacketWorldName;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

public class PlayerEventHandler {
    @SubscribeEvent
    public void onPlayerChangeDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
        System.out.println("change dim");
        EntityPlayerMP player = (EntityPlayerMP) event.player;
        String worldName = player.world.getWorldInfo().getWorldName();
        PacketWorldName packet = new PacketWorldName(worldName);
        System.out.println(worldName);
        NetworkHandler.INSTANCE.sendTo(packet, player);
    }
    @SubscribeEvent
    public void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        System.out.println("change dim log in");
        EntityPlayerMP player = (EntityPlayerMP) event.player;
        String worldName = player.world.getWorldInfo().getWorldName();
        PacketWorldName packet = new PacketWorldName(worldName);
        System.out.println(worldName);
        NetworkHandler.INSTANCE.sendTo(packet, player);
    }
}