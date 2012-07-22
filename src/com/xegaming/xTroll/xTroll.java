package com.xegaming.xTroll;


import com.xegaming.xTroll.commands.*;
import net.milkbowl.vault.permission.Permission;
import net.minecraft.server.Packet41MobEffect;
import net.minecraft.server.Packet42RemoveMobEffect;
import org.bukkit.Effect;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;


public class xTroll extends JavaPlugin implements Listener {
    public void onDisable() {
        System.out.println(this + " is now disabled!");
    }

    public static Permission permission = null;

    private Boolean setupPermissions() {
        RegisteredServiceProvider<Permission> permissionProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
        if (permissionProvider != null) {
            permission = permissionProvider.getProvider();
        }
        return (permission != null);
    }

    public void onEnable() {
        setupPermissions();
        //TrollCraft
        getServer().getPluginManager().registerEvents(new xListener(this), this);

        this.getCommand("trip").setExecutor(new tripCommand(this));
        this.getCommand("fakeop").setExecutor(new fakeopCommand(this));
        this.getCommand("banish").setExecutor(new banishCommand(this));
        this.getCommand("space").setExecutor(new spaceCommand(this));
        this.getCommand("test").setExecutor(new testCommand(this));
        System.out.println(this + " is now enabled!");
    }

    public void trip(Player p) {
        Packet41MobEffect packetm = new Packet41MobEffect();
        packetm.a = p.getEntityId();
        packetm.b = 9;
        packetm.c = 0;
        packetm.d = 300;
        ((CraftPlayer) p).getHandle().netServerHandler.sendPacket(packetm);
        p.playEffect(p.getLocation(), Effect.POTION_BREAK, 0);
        final Player p2 = p;
        getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
            public void run() {
                Packet42RemoveMobEffect packetr = new Packet42RemoveMobEffect();
                packetr.a = p2.getEntityId();
                packetr.b = 9;
                ((CraftPlayer) p2).getHandle().netServerHandler.sendPacket(packetr);
            }
        }, 300L);
    }


}