package com.openwar.charpy.Network;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketDeleteItem implements IMessage {

    private int slotIndex;

    public PacketDeleteItem() {}

    public PacketDeleteItem(int slotIndex) {
        this.slotIndex = slotIndex;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.slotIndex = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(slotIndex);
    }

    public static class Handler implements IMessageHandler<PacketDeleteItem, IMessage> {
        @Override
        public IMessage onMessage(PacketDeleteItem message, MessageContext ctx) {
            EntityPlayerMP player = ctx.getServerHandler().player;
            player.getServerWorld().addScheduledTask(() -> {
                ItemStack stack = player.inventory.getStackInSlot(message.slotIndex);
                if (!stack.isEmpty()) {
                    player.inventory.deleteStack(stack);
                }
            });
            return null;
        }
    }
}