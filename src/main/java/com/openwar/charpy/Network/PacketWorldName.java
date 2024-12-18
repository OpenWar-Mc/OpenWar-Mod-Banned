package com.openwar.charpy.Network;

import com.openwar.charpy.Handler.FogHandler;
import com.openwar.charpy.Handler.PlayerInfo;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketWorldName implements IMessage {
    private String worldName;

    public PacketWorldName() {}

    public PacketWorldName(String worldName) {
        this.worldName = worldName;
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, this.worldName);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.worldName = ByteBufUtils.readUTF8String(buf);
    }

    public static class Handler implements IMessageHandler<PacketWorldName, IMessage> {
        @Override
        public IMessage onMessage(PacketWorldName message, MessageContext ctx) {
            Minecraft.getMinecraft().addScheduledTask(() -> {
                System.out.println("PACKET "+message.worldName);

                FogHandler.setCurrentWorldName(message.worldName);
                PlayerInfo.world = message.worldName;
            });
            return null;
        }
    }
}
