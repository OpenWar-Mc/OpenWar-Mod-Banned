package com.openwar.charpy.Handler;

import com.openwar.charpy.Network.PacketDeleteItem;
import com.openwar.charpy.Network.PacketWorldName;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class NetworkHandler {
    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel("openwar");

    public static void init() {
        INSTANCE.registerMessage(PacketDeleteItem.Handler.class, PacketDeleteItem.class, 0, Side.SERVER);
        INSTANCE.registerMessage(PacketWorldName.Handler.class, PacketWorldName.class, 1, Side.CLIENT);
    }
}
