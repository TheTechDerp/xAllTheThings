package com.xegaming.xTroll;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

/**
 * User: Benjamin
 * Date: 22/07/12
 * Time: 08:16
 */
public class xListener implements Listener {
    xTroll XE;

    public xListener(xTroll XE) {
        this.XE = XE;
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (event.getAction().equals(Action.RIGHT_CLICK_AIR)) {
            final Player player = event.getPlayer();
            ItemStack stack = player.getItemInHand();
            if (xTroll.permission.has(player, "XT.flame") || player.isOp()) {
                if (stack.getType().equals(Material.TORCH)) {
                    final Location location = player.getEyeLocation();
                    final World world = player.getWorld();
                    if (player.isSneaking()) {
                        for (int i = 1; i <= 5; i++) {
                            final int finalI = i;
                            Bukkit.getScheduler().scheduleSyncDelayedTask(XE, new Runnable() {
                                public void run() {
                                    Location newloc = location.toVector().add(location.getDirection().multiply(3 * finalI)).toLocation(player.getWorld());
                                    world.playEffect(newloc, Effect.MOBSPAWNER_FLAMES, 0);
                                }
                            }, 5L);
                        }


                    } else {
                        Location newloc = location.toVector().add(location.getDirection().multiply(3)).toLocation(player.getWorld());
                        world.playEffect(newloc, Effect.MOBSPAWNER_FLAMES, 0);
                    }
                }
            }
        }

    }


}
