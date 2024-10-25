package com.openwar.charpy.Network;
import com.openwar.charpy.Handler.PlayerInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;


public class PacketMessageInfo implements IMessage {

    private String balance;
    private String level;

    public PacketMessageInfo() {}

    public PacketMessageInfo(String balance, String level) {
        this.balance = balance;
        this.level = level;
    }

    @Override
    public void toBytes(ByteBuf buf) {
        byte[] levelBytes = level.getBytes(java.nio.charset.StandardCharsets.UTF_8);
        byte[] balanceBytes = balance.getBytes(java.nio.charset.StandardCharsets.UTF_8);

        buf.writeInt(levelBytes.length);
        buf.writeBytes(levelBytes);

        buf.writeInt(balanceBytes.length);
        buf.writeBytes(balanceBytes);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        int length1 = buf.readInt();
        this.level = buf.readCharSequence(length1, java.nio.charset.StandardCharsets.UTF_8).toString();
        int length2 = buf.readInt();
        this.balance = buf.readCharSequence(length2, java.nio.charset.StandardCharsets.UTF_8).toString();
    }

    public static class Handler implements IMessageHandler<PacketMessageInfo, IMessage> {
        @Override
        @SideOnly(Side.CLIENT)
        public IMessage onMessage(PacketMessageInfo message, MessageContext ctx) {
            Minecraft.getMinecraft().addScheduledTask(() -> {
                PlayerInfo.playerLevel = message.level;
                PlayerInfo.playerBalance = message.balance;
            });
            return null;
        }
    }
}