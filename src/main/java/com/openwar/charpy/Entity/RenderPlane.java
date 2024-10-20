package com.openwar.charpy.Entity;

import com.openwar.charpy.Utils.IModelCustom;
import com.openwar.charpy.Utils.ResourceManager;
import com.openwar.charpy.openwarbanned.Tags;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import org.lwjgl.opengl.GL11;

public class RenderPlane extends Render<EntityPlane> {
    public static final ResourceLocation plane = new ResourceLocation(Tags.MOD_ID, "textures/models/entity/plane.png");
    public static final IRenderFactory<EntityPlane> FACTORY = (RenderManager man) -> {return new RenderPlane(man);};

    protected RenderPlane(RenderManager renderManager) {
        super(renderManager);

    }

    @Override
    public void doRender(EntityPlane entity, double x, double y, double z, float entityYaw, float partialTicks) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)x, (float)y, (float)z);
        GL11.glRotatef(entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * partialTicks - 90.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(90, 0F, 0F, 1F);
        GL11.glRotatef(entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks, 0.0F, 0.0F, 1.0F);
        GlStateManager.enableLighting();
        GL11.glDisable(GL11.GL_CULL_FACE);
        bindTexture(ResourceManager.planetex);
        GL11.glScalef(30F/3.1F, 30F/3.1F, 30F/3.1F); GL11.glRotatef(180, 0F, 1F, 0F); ResourceManager.plane.renderAll();
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glPopMatrix();
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityPlane entity) {
        return plane;
    }

}