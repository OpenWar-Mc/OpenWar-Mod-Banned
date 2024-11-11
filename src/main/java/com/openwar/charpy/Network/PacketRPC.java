package com.openwar.charpy.Network;

import com.openwar.charpy.RPC.RichPresence;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketRPC implements IMessage {
    private String details;
    private String state;

    public PacketRPC() {}

    public PacketRPC(String details, String state) {
        this.details = details;
        this.state = state;
    }

    @Override
    public void toBytes(ByteBuf buf) {
        byte[] detailsBytes = details.getBytes();
        byte[] stateBytes = state.getBytes();

        buf.writeInt(detailsBytes.length);
        buf.writeBytes(detailsBytes);
        buf.writeInt(stateBytes.length);
        buf.writeBytes(stateBytes);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        int detailsLength = buf.readInt();
        byte[] detailsBytes = new byte[detailsLength];
        buf.readBytes(detailsBytes);
        details = new String(detailsBytes);

        int stateLength = buf.readInt();
        byte[] stateBytes = new byte[stateLength];
        buf.readBytes(stateBytes);
        state = new String(stateBytes);
    }

    public String getDetails() { return details; }
    public String getState() { return state; }

    public static class Handler implements IMessageHandler<PacketRPC, IMessage> {
        @Override
        public IMessage onMessage(PacketRPC message, MessageContext ctx) {
            RichPresence rpc = new RichPresence("947431491284115456");
            rpc.updatePresence(message.getDetails(), message.getState(), "original_openwar", "Join Us !", "", "");
            return null;
        }
    }
}
