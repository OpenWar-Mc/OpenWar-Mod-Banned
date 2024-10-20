package com.openwar.charpy.Entity;

import com.openwar.charpy.openwarbanned.Tags;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderParachute extends Render<EntityParachute> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(Tags.MOD_ID, "textures/entity/parachute.png");
    private final ModelBase model;

    public RenderParachute(RenderManager renderManager, ModelBase model) {
        super(renderManager);
        this.model = model;
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityParachute entity) {
        return TEXTURE;
    }

    @Override
    public void doRender(EntityParachute entity, double x, double y, double z, float entityYaw, float partialTicks) {
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)x, (float)y + 0.5F, (float)z);
        GlStateManager.rotate(180F, 0F, 1F, 180F);
        GlStateManager.scale(0.5F, 0.5F, 0.5F);
        bindEntityTexture(entity);
        model.render(entity, 0.0F, 0.0F, 0.0F, entityYaw, entity.rotationPitch, 0.0625F);
        GlStateManager.popMatrix();
    }
}
