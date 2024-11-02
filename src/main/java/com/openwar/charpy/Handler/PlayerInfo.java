package com.openwar.charpy.Handler;

import com.openwar.charpy.Hud.HudPlayer;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import java.util.Arrays;
import java.util.List;

public class PlayerInfo {

    private final Minecraft mc = Minecraft.getMinecraft();
    public static String playerBalance;
    public static String playerLevel;
    public static boolean isInWarzone = false;
    private boolean showGui = true;
    private boolean otherKeyPressedWithF3 = false;
    private boolean wasF3Pressed = false;

    List<String> targetable = Arrays.asList(
            "Block{mwc:fridge_closed}",
            "Block{mwc:fridge_open}",
            "Block{mwc:filingcabinet_opened}",
            "Block{mwc:filingcabinet}",
            "Block{mwc:dumpster}",
            "Block{mwc:wooden_crate_opened}",
            "Block{cfm:counter_drawer}",
            "Block{cfm:bedside_cabinet_oak}",
            "Block{cfm:desk_cabinet_oak}",
            "Block{mwc:russian_weapons_case}",
            "Block{mwc:weapons_case}",
            "Block{mwc:ammo_box}",
            "Block{mwc:weapons_case_small}",
            "Block{mwc:weapons_locker}",
            "Block{mwc:medical_crate}",
            "Block{mwc:trash_bin}",
            "Block{mwc:vending_machine}",
            "Block{mwc:supply_drop}",
            "Block{mwc:scp_locker}",
            "Block{mwc:locker}",
            "Block{mwc:electric_box_opened}",
            "Block{mwc:electric_box}",
            "Block{hbm:radiorec}",
            "Block{hbm:safe}"
    );



    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        Minecraft mc = Minecraft.getMinecraft();
        World world = mc.player.getEntityWorld();

        if (world.isRemote) {
            if (Keyboard.isKeyDown(Keyboard.KEY_APOSTROPHE)) {
               showGui = !showGui;
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_F3)) {
                for (int keyCode = Keyboard.KEY_1; keyCode <= Keyboard.KEY_Z; keyCode++) {
                    if (Keyboard.isKeyDown(keyCode) && keyCode != Keyboard.KEY_F3) {
                        otherKeyPressedWithF3 = true;
                        break;
                    }
                }
                wasF3Pressed = true;
            }
            else if (wasF3Pressed) {
                wasF3Pressed = false;
                if (!otherKeyPressedWithF3) {
                    showGui = !showGui;
                }
                otherKeyPressedWithF3 = false;
            }
        }
    }
    @SubscribeEvent
    public void onRenderOverlay(RenderGameOverlayEvent.Post event) {
        if (event.getType() == RenderGameOverlayEvent.ElementType.ALL) {
            if (mc.currentScreen == null && showGui) {
                drawRectangleWithLines();
                HudPlayer gui = new HudPlayer(Minecraft.getMinecraft());
                String player = mc.player.getName();
                if (player.length() > 12) {
                    player = player.substring(0, 12);
                }
                gui.drawRect(10, 10, 100, 20);
                gui.drawRect(10, 30, 100, 10);
                gui.drawRect(10, 40, 100, 10);
                gui.drawTitle(10, 10, 100, 20, "Info \u00A74" + player, 0xFFFFFF);
                gui.drawString(10, 25, playerLevel, 0xFFFFFF);
                gui.drawString(10, 35, playerBalance, 0xFFFFFF);

                gui.drawLogoOpenWar();
                Block target = raytracePlayer(mc.player);
                if (target != null) {
                    if (targetable.contains(target.toString()) && isInWarzone) {
                        ScaledResolution scaledResolution = new ScaledResolution(mc);
                        int screenWidth = scaledResolution.getScaledWidth();
                        int screenHeight = scaledResolution.getScaledHeight();
                        gui.drawRect60(screenWidth / 2 + 8, screenHeight / 2 + 8, 85, 15);
                        gui.drawCursor();
                        Minecraft.getMinecraft().ingameGUI.drawTexturedModalRect(screenWidth / 2 + 5, screenHeight / 2 + 5, 0, 0, 16, 16);
                        gui.drawString(screenWidth / 2 + 10, screenHeight / 2 + 12, "    \u00A78» \u00A7cClick Here", 0x7700FF);
                    }
                }
            }
        }
    }



    public Block raytracePlayer(EntityPlayer player) {
        double reachDistance = 5.0D;
        Vec3d eyePosition = player.getPositionEyes(1.0F);
        Vec3d lookVector = player.getLook(1.0F);
        Vec3d rayEnd = eyePosition.add(lookVector.x * reachDistance, lookVector.y * reachDistance, lookVector.z * reachDistance);
        RayTraceResult rayTraceResult = player.world.rayTraceBlocks(eyePosition, rayEnd);
        if (rayTraceResult != null && rayTraceResult.typeOfHit == RayTraceResult.Type.BLOCK) {
            BlockPos blockPos = rayTraceResult.getBlockPos();
            return player.world.getBlockState(blockPos).getBlock();
        }
        return null;
    }

    private void drawRectangleWithLines() {
        ScaledResolution scaledResolution = new ScaledResolution(mc);
        int screenWidth = scaledResolution.getScaledWidth();
        int screenHeight = scaledResolution.getScaledHeight();
        int rectX = 5;
        int rectY = 5;
        int rectWidth = 67;
        int rectHeight = 25;
        drawRect(rectX, rectY, rectX + rectWidth, rectY + rectHeight, 0x80000000);
        drawRect(rectX + 110, rectY + 50, rectX + rectWidth, rectY + rectHeight, 0x80000000);
        //drawRect(rectX, rectY, rectX + rectWidth, rectY + rectHeight, 0x80000000);

        //drawLine(rectX, rectY + rectHeight / 3, rectX + rectWidth, rectY + rectHeight / 3, 0x80FFFFFF);
        //drawLine(rectX, rectY + (2 * rectHeight / 3), rectX + rectWidth, rectY + (2 * rectHeight / 3), 0x80FFFFFF);
        //drawLine(rectX, rectY + (rectHeight), rectX + rectWidth, rectY + (rectHeight), 0x80FFFFFF);
    }

    private void drawRect(int left, int top, int right, int bottom, int color) {
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glColor4f((color >> 16 & 255) / 255.0F, (color >> 8 & 255) / 255.0F, (color & 255) / 255.0F, (color >> 24 & 255) / 255.0F);
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glVertex2f(left, top);
        GL11.glVertex2f(left, bottom);
        GL11.glVertex2f(right, bottom);
        GL11.glVertex2f(right, top);
        GL11.glEnd();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
    }

    private void drawLine(int x1, int y1, int x2, int y2, int color) {
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glColor4f((color >> 16 & 255) / 255.0F, (color >> 8 & 255) / 255.0F, (color & 255) / 255.0F, (color >> 24 & 255) / 255.0F);
        GL11.glBegin(GL11.GL_LINES);
        GL11.glVertex2f(x1, y1);
        GL11.glVertex2f(x2, y2);
        GL11.glEnd();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
    }
}
