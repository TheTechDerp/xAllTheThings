package com.xegaming.xTroll;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.List;

/**
 * User: Benjamin
 * Date: 21/07/12
 * Time: 04:39
 */
public class spaceCommand extends commandBase {
    @Override
    protected void run(CommandSender sender, String[] args, Player player, String playerName) {
        if (xTroll.permission.has(player, "XT.space")) {
            List<Entity> l = player.getNearbyEntities(20, 20, 20);
            for (int i = 0; i < l.size(); i++) {

                Vector direction = l.get(i).getLocation().getDirection();
                Vector space = direction.normalize().multiply(-5);
                l.get(i).setVelocity(space);

            }
        }
    }

    protected spaceCommand(xTroll XE) {
        super(XE);
    }
}
