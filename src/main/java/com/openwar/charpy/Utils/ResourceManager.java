package com.openwar.charpy.Utils;

import com.openwar.charpy.openwarbanned.Tags;
import net.minecraft.util.ResourceLocation;

public class ResourceManager {
    public static final IModelCustom plane = AdvancedModelLoader.loadModel(new ResourceLocation(Tags.MOD_ID, "models/entity/plane.obj"));
    public static final ResourceLocation planetex = new ResourceLocation(Tags.MOD_ID, "textures/entity/plane.png");
}
