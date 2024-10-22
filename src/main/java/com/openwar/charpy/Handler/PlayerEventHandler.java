package com.openwar.charpy.Handler;

import com.openwar.charpy.Network.PacketWorldName;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

public class PlayerEventHandler {
    @SubscribeEvent
    public void onPlayerChangeDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
        EntityPlayerMP player = (EntityPlayerMP) event.player;
        String worldName = player.world.getWorldInfo().getWorldName();
        System.out.println("World name : "+worldName);
        PacketWorldName packet = new PacketWorldName(worldName);
        NetworkHandler.INSTANCE.sendTo(packet, player);
    }
    @SubscribeEvent
    public void onPlayerChangeDimension(PlayerEvent.PlayerLoggedInEvent event) {
        EntityPlayerMP player = (EntityPlayerMP) event.player;
        String worldName = player.world.getWorldInfo().getWorldName();
        System.out.println("World name : "+worldName);
        PacketWorldName packet = new PacketWorldName(worldName);
        NetworkHandler.INSTANCE.sendTo(packet, player);
    }
}