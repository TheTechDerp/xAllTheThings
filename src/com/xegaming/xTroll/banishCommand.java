package com.xegaming.xTroll;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * User: Benjamin
 * Date: 21/07/12
 * Time: 04:39
 */
public class banishCommand extends commandBase {
    @Override
    protected void run(CommandSender sender, String[] args, Player player, String playerName) {
        if (xTroll.permission.has(player, "XT.banish")) {
            if (args.length != 1) {
                player.sendMessage("Needs more arguments");
                return;
            }

            Server s = player.getServer();
            Player banished = s.getPlayer(args[0]);

            s.broadcastMessage(String.format(" %s%s%s was banished by %s%s%s!", ChatColor.BLUE, banished.getDisplayName(), ChatColor.RED, ChatColor.BLUE, player.getDisplayName(), ChatColor.RED));
            banished.teleport(banished.getWorld().getSpawnLocation());
        }
    }

    protected banishCommand(xTroll XE) {
        super(XE);
    }
}
