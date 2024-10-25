package com.openwar.charpy.Hud;

import com.openwar.charpy.Handler.NetworkHandler;
import com.openwar.charpy.Main;
import com.openwar.charpy.Network.PacketWorldName;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class HudWarzone {
    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.player instanceof EntityPlayerMP) {
            EntityPlayerMP player = (EntityPlayerMP) event.player;
            String currentWorldName = player.getServerWorld().getWorldInfo().getWorldName();
            String lastWorldName = player.getEntityData().getString("LastWorldName");
            if (!currentWorldName.equals(lastWorldName)) {
                sendWorldNameToClient(player, currentWorldName);
                player.getEntityData().setString("LastWorldName", currentWorldName);
            }
        }
    }
    @SubscribeEvent
    public void onPlayerJoinServer(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.player instanceof EntityPlayerMP) {
            EntityPlayerMP player = (EntityPlayerMP) event.player;
            String worldName = player.getServerWorld().getWorldInfo().getWorldName();
            sendWorldNameToClient(player, worldName);
        }
    }
    @SubscribeEvent
    public void onPlayerChangeDim(PlayerEvent.PlayerChangedDimensionEvent event) {
        if (event.player instanceof EntityPlayerMP) {
            EntityPlayerMP player = (EntityPlayerMP) event.player;
            String worldName = player.getServerWorld().getWorldInfo().getWorldName();
            sendWorldNameToClient(player, worldName);
        }
    }
    public void sendWorldNameToClient(EntityPlayerMP player, String worldName) {
        PacketWorldName packet = new PacketWorldName(worldName);
        NetworkHandler.INSTANCE.sendTo(packet, player);
    }
}
