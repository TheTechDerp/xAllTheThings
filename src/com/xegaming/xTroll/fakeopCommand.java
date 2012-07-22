package com.xegaming.xTroll;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * User: Benjamin
 * Date: 20/07/12
 * Time: 18:28
 */
public class fakeopCommand extends commandBase {
    @Override
    protected void run(CommandSender sender, String[] args, Player player, String playerName) {
        if (xTroll.permission.has(player, "XT.fop")) {
            if (args.length != 1) {
                player.sendMessage(ChatColor.RED + "[xTroll]: missing params!");
                return;
            }
            if (args[0].equalsIgnoreCase("all")) {
                XE.getServer().broadcastMessage(ChatColor.LIGHT_PURPLE + "[Server] Xaldin is proud to announce that all current members are being promoted to \"OP\" status! :3");
                for (final Player p : XE.getServer().getOnlinePlayers()) {
                    final Player target = p;
                    target.sendMessage(ChatColor.YELLOW + "You are now op.");
                    final String oldname = ChatColor.stripColor(target.getDisplayName());
                    target.setDisplayName(ChatColor.DARK_RED + oldname);
                    XE.getServer().getScheduler().scheduleSyncDelayedTask(XE, new Runnable() {
                        public void run() {
                            if (!target.isOnline()) {
                                return;
                            }
                            target.setDisplayName(oldname);
                            target.sendMessage(ChatColor.GRAY + "(CONSOLE: De-opping " + oldname + ")");
                            target.sendMessage(ChatColor.YELLOW + "You are no longer op.");
                        }
                    }, 200L/*10 seconds!*/);

                }
            } else {
                final Player target = XE.getServer().getPlayer(args[1]);
                if (target != null) {
                    target.sendMessage(ChatColor.YELLOW + "You are now op.");
                    final String oldname = ChatColor.stripColor(target.getDisplayName());
                    target.setDisplayName(ChatColor.DARK_RED + oldname);
                    XE.getServer().broadcastMessage(ChatColor.LIGHT_PURPLE + "[Server] Xaldin is proud to welcome our new \"OP\" " + oldname + " to the server!");
                    XE.getServer().getScheduler().scheduleSyncDelayedTask(XE, new Runnable() {
                        public void run() {
                            if (!target.isOnline()) {
                                return;
                            }
                            target.setDisplayName(oldname);
                            target.sendMessage(ChatColor.GRAY + "(CONSOLE: De-opping " + oldname + ")");
                            target.sendMessage(ChatColor.YELLOW + "You are no longer op.");
                            XE.getServer().broadcastMessage(ChatColor.LIGHT_PURPLE + "[Server] Not a chance " + oldname + "! Better luck next time!");
                        }
                    }, 200L/*10 seconds!*/);
                }
            }
        }
    }

    protected fakeopCommand(xTroll XE) {
        super(XE);
    }
}
