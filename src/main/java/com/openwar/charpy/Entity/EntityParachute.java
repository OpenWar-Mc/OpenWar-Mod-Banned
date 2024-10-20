package com.openwar.charpy.Entity;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
public class EntityParachute extends EntityLiving {


    public EntityParachute(World worldIn) {
        super(worldIn);
        this.isImmuneToFire = true;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        if (this.motionY < 0) {
            this.motionY *= 0.4;
        }

        if (this.onGround) {
            BlockPos blockPos = new BlockPos(this.posX, this.posY, this.posZ);
            world.setBlockState(blockPos, Block.getBlockFromName("mwc:supply_drop").getDefaultState());
            this.setDead();
        }
    }

    @Override
    public boolean isEntityInvulnerable(DamageSource source) {
        return true;
    }

    @Override
    public boolean canBeCollidedWith() {
        return true;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
    }
}