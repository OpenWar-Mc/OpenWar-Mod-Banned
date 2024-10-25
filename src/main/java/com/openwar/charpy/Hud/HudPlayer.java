package com.openwar.charpy.Hud;
import com.openwar.charpy.Main;
import com.openwar.charpy.openwarbanned.Tags;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;


public class HudPlayer {
    private Minecraft mc;

    public HudPlayer(Minecraft mc) {
        this.mc = mc;
    }

    public void drawTitle(int x, int y, int width, int height, String text, int color) {
        Minecraft mc = Minecraft.getMinecraft();
        mc.fontRenderer.drawString(text, x + 5, y + 5, color);
    }


    public void drawString(int x, int y, String text, int color) {
        Minecraft mc = Minecraft.getMinecraft();
        mc.fontRenderer.drawString(text, x + 3, y, color);
    }

    public void drawRect(int x, int y, int width, int height) {
        Gui.drawRect(x, y, x + width, y + height, 0xFF000000);
        GlStateManager.pushMatrix();
        GlStateManager.scale(1.0F, 1.0F, 1.0F);
        GlStateManager.popMatrix();
    }

    public void drawRect60(int x, int y, int width, int height) {
        int colorWithOpacity = (0x99 << 24) | 0x000000;
        Gui.drawRect(x, y, x + width, y + height, colorWithOpacity);
        GlStateManager.pushMatrix();
        GlStateManager.scale(1.0F, 1.0F, 1.0F);
        GlStateManager.popMatrix();
    }

    public void drawLogoOpenWar() {
        Minecraft mc = Minecraft.getMinecraft();
        ScaledResolution scaledResolution = new ScaledResolution(mc);
        int screenWidth = scaledResolution.getScaledWidth();
        int screenY = scaledResolution.getScaledHeight();

        int xPos = screenWidth - 74;
        int yPos = screenY - 54;

        ResourceLocation ICON = new ResourceLocation(Tags.MOD_ID, "textures/gui/op.png");
        mc.getTextureManager().bindTexture(ICON);

        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        float alpha = 0.4f;
        GlStateManager.color(1.0f, 1.0f, 1.0f, alpha);

        mc.ingameGUI.drawScaledCustomSizeModalRect(
                xPos, yPos,
                0, 0,
                256, 256,
                64, 64,
                256, 256
        );

        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }

    public void drawCursor() {
        Minecraft mc = Minecraft.getMinecraft();
        ScaledResolution scaledResolution = new ScaledResolution(mc);
        int screenWidth = scaledResolution.getScaledWidth();
        int screenHeight = scaledResolution.getScaledHeight();
        int xPos = screenWidth / 2 + 10;
        int yPos = screenHeight / 2 + 7;

        ResourceLocation ICON = new ResourceLocation(Tags.MOD_ID, "textures/emote/cursor.png");
        mc.getTextureManager().bindTexture(ICON);
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        int originalWidth = 147;
        int originalHeight = 157;
        int desiredWidth = 16;
        float scaleFactor = (float) desiredWidth / originalWidth;
        int scaledHeight = (int) (originalHeight * scaleFactor);
        float alpha = 1.0f;
        GlStateManager.color(1.0f, 1.0f, 1.0f, alpha);
        mc.ingameGUI.drawScaledCustomSizeModalRect(
                xPos, yPos,
                0, 0,
                originalWidth, originalHeight,
                desiredWidth, scaledHeight,
                originalWidth, originalHeight
        );

        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }
}