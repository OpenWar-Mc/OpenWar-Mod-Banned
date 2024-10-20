package com.openwar.charpy.Handler;

import com.openwar.charpy.Entity.EntityParachute;
import com.openwar.charpy.Entity.EntityPlane;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.NumberInvalidException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import org.lwjgl.Sys;

public class CommandSpawnParachute extends CommandBase {

    @Override
    public String getName() {
        return "spawnparachute";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/spawnparachute <x> <y> <z>";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws NumberInvalidException {
        if (args.length != 3) {
            sender.sendMessage(new TextComponentString("Usage: /spawnparachute <x> <y> <z>"));
            return;
        }

        int x = parseInt(args[0]);
        int y = parseInt(args[1]);
        int z = parseInt(args[2]);

        World world = sender.getEntityWorld();
        if (!world.isRemote) {
            EntityPlane plane = new EntityPlane(world, x, z);
            plane.fac(world, x, y ,z);
            world.spawnEntity(plane);
            System.out.println("Avion spawn à : "+plane.getPosition());
        }
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return sender instanceof EntityPlayer;
    }
}
