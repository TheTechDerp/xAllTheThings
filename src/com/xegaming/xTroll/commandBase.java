package com.xegaming.xTroll;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class commandBase implements CommandExecutor {
    protected static xTroll XE;
    // Permission permission = xEssentials.permission;

    protected commandBase(xTroll XE) {
        commandBase.XE = XE;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
//        final String commandName = command.getName().toLowerCase();
        Player player;
        String playerName;
        final boolean isPlayer = (sender instanceof Player);

        if (isPlayer) {
            player = (Player) sender;
            playerName = player.getName();
        } else {
            return false;
        }

        this.run(sender, args, player, playerName);

        return true;
    }

    protected abstract void run(CommandSender sender, String[] args, Player player, String playerName);

}