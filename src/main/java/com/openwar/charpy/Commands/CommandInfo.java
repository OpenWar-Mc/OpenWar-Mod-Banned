package com.openwar.charpy.Commands;

import com.openwar.charpy.Handler.NetworkHandler;
import com.openwar.charpy.Main;
import com.openwar.charpy.Network.PacketMessageInfo;
import com.openwar.charpy.Network.PacketWorldName;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import org.lwjgl.Sys;

public class CommandInfo extends CommandBase {

    @Override
    public String getName() {
        return "setall";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/setall <Level: number>$<Money: number>$<Player>";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length < 1) {
            throw new CommandException("Usage: /setall <Level: number>%<Money: number>%<Player>");
        }

        String input = String.join(" ", args);
        String[] text = input.split(";");

        if (text.length < 3) {
            throw new CommandException("Invalid format! Usage: /setall Level: 100;Money: 1000;Player");
        }
        String level;
        String balance;

        try {
            String levelInfo = text[0];
            String balanceInfo = text[1];
            String[] levelParts = levelInfo.split(" ");
            String[] balanceParts = balanceInfo.split(" ");
            level = levelParts[1];
            balance = balanceParts[1];
            level = "Level: \u00A7c" + level;
            balance = "Money: \u00A76" + balance;

        } catch (Exception e) {
            throw new CommandException("Error parsing level or balance.");
        }

        String playerName = text[2];
        EntityPlayerMP player = server.getPlayerList().getPlayerByUsername(playerName);

        if (player == null) {
            throw new CommandException("Player not found!");
        }

        showInfoOnScreen(player, balance, level);
    }

    private void showInfoOnScreen(EntityPlayerMP player, String balance, String level) {
        PacketMessageInfo packet = new PacketMessageInfo(balance, level);
        NetworkHandler.INSTANCE.sendTo(packet, player);
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return true;
    }
}
