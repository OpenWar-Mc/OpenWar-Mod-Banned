package com.openwar.charpy.Handler;


import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.Sys;
import org.lwjgl.opengl.GL11;

public class FogHandler {
    private static String currentWorldName = "";
    @SubscribeEvent
    public static void onRenderFog(EntityViewRenderEvent.FogDensity event) {
        if (currentWorldName.equals("fire")) {
            GL11.glFogi(GL11.GL_FOG_MODE, GL11.GL_LINEAR);
            GL11.glFogf(GL11.GL_FOG_START, 5.0F);
            GL11.glFogf(GL11.GL_FOG_END, 20.0F);
            event.setCanceled(true);
        }
    }

    public static void setCurrentWorldName(String worldName) {
        System.out.println("World FOG " +worldName);
        currentWorldName = worldName;
    }
}