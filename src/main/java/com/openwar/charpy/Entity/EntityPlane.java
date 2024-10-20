package com.openwar.charpy.Entity;

import com.openwar.charpy.openwarbanned.Tags;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.common.ForgeChunkManager.Ticket;
import net.minecraftforge.common.ForgeChunkManager.Type;

import java.util.ArrayList;
import java.util.List;


public class EntityPlane extends EntityLiving {
    int time = 400;
    int x;
    int z;
    boolean dropped;

    public EntityPlane(World worldIn) {
        super(worldIn);
        this.isImmuneToFire = true;
    }

    public EntityPlane(World worldIn, int targetX, int targetZ) {
        super(worldIn);
        this.isImmuneToFire = true;
        this.x = targetX;
        this.z = targetZ;
        this.dropped = false;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        this.motionY = 0.0D;
        this.motionX *= 1.09D;
        this.motionZ *= 1.09D;
        double targetX = (double) x + 0.5;
        double targetZ = (double) z + 0.5;

        if (ticksExisted > time) {
            this.setDead();
        }

        double distanceThreshold = 10.0;
        double distanceSquared = (this.posX - targetX) * (this.posX - targetX) + (this.posZ - targetZ) * (this.posZ - targetZ);

        if (distanceSquared < distanceThreshold * distanceThreshold) {
            if (!dropped && !world.isRemote) {
                dropped = true;
                EntityParachute parachute = new EntityParachute(world);
                parachute.setPosition(this.posX, this.posY - 6, this.posZ);
                world.spawnEntity(parachute);
            }
        }
    }

    public void fac(World world, double x, double y, double z) {
        Vec3d vector = new Vec3d(world.rand.nextDouble() - 0.5, 0, world.rand.nextDouble() - 0.5).normalize();
        double scale = 2;
        vector = new Vec3d(vector.x * scale, vector.y, vector.z * scale);
        this.setLocationAndAngles(x - vector.x * 30, y + 50, z - vector.z * 30, 0.0F, 0.0F);

        this.motionX = vector.x;
        this.motionZ = vector.z;
        this.motionY = 0.0D;
        this.rotation();
        this.setSize(8.0F, 4.0F);
    }

    protected void rotation() {
        float f2 = MathHelper.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
        this.rotationYaw = (float) (Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);

        for (this.rotationPitch = (float) (Math.atan2(this.motionY, f2) * 180.0D / Math.PI) - 90; this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F) {
            ;
        }

        while (this.rotationPitch - this.prevRotationPitch >= 180.0F) {
            this.prevRotationPitch += 360.0F;
        }

        while (this.rotationYaw - this.prevRotationYaw < -180.0F) {
            this.prevRotationYaw -= 360.0F;
        }

        while (this.rotationYaw - this.prevRotationYaw >= 180.0F) {
            this.prevRotationYaw += 360.0F;
        }
    }

    @Override
    public boolean isEntityInvulnerable(DamageSource source) {
        return true;
    }

    @Override
    public boolean canBeCollidedWith() {
        return false;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
    }
}
