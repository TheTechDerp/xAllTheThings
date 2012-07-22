package com.xegaming.xTroll.commands;

import com.xegaming.xTroll.commandBase;
import com.xegaming.xTroll.xTroll;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * User: Benjamin
 * Date: 20/07/12
 * Time: 18:28
 */
public class tripCommand extends commandBase {
    @Override
    protected void run(CommandSender sender, String[] args, Player player, String playerName) {
        if (xTroll.permission.has(player, "XT.Trip")) {
            if (args.length != 1) {
                player.sendMessage(ChatColor.RED + "[xTroll]: missing params!");
                return;
            }
            if (args[0].equalsIgnoreCase("all")) {
                XE.getServer().broadcastMessage(ChatColor.GREEN + "XeGaming is not responsible for any vomit that may appear on your screen.");
                for (Player p : XE.getServer().getOnlinePlayers()) {
                    XE.trip(p);
                }
            } else {
                Player p1 = XE.getServer().getPlayer(args[1]);
                if (p1 != null) {
                    XE.trip(p1);
                    sender.sendMessage("Causing " + p1.getDisplayName() + " to trip balls!");
                } else {
                    sender.sendMessage("Check your spelling please.");
                }
            }
        }
    }

    public tripCommand(xTroll XE) {
        super(XE);
    }
}
