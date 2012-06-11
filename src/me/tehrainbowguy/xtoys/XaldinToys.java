package me.tehrainbowguy.xtoys;


import net.milkbowl.vault.permission.Permission;
import net.minecraft.server.Packet13PlayerLookMove;
import net.minecraft.server.Packet32EntityLook;
import net.minecraft.server.Packet41MobEffect;
import net.minecraft.server.Packet42RemoveMobEffect;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.map.MapView;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import java.util.*;


public class XaldinToys extends JavaPlugin implements Listener {
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
        getServer().getPluginManager().registerEvents(this, this);
        System.out.println(this + " is now enabled!");
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player && command.getName().equalsIgnoreCase("xt")) {
            if (!permission.has((Player) sender, "xt.use") && !sender.isOp()) {
                sender.sendMessage("You don't have permission to do this.");
                return true;
            }
            if (args[0].equalsIgnoreCase("trip")) {
                if (args.length < 1) {
                    return true;
                }
                if (args[1].equalsIgnoreCase("all")) {
                    getServer().broadcastMessage(ChatColor.GREEN + "XeGaming is not responsible for any vomit that may appear on your screen.");
                    for (Player p : getServer().getOnlinePlayers()) {
                        trip(p);
                    }
                } else {
                    Player p1 = getServer().getPlayer(args[1]);
                    if (p1 != null) {
                        trip(p1);
                        sender.sendMessage("Causing " + p1.getDisplayName() + " to trip balls!");
                    } else {
                        sender.sendMessage("Check your spelling please.");
                    }
                }
                return true;

            }

                if(args[0].equalsIgnoreCase("banish")){
                Player p = (Player)sender;
                if(args.length < 2){
                    p.sendMessage("Needs more arguments");
                    return true;
                }

                Server s = p.getServer();
                Player banished = s.getPlayer(args[1]);

                s.broadcastMessage(ChatColor.RED + "Player " + ChatColor.BLUE + banished.getDisplayName() + ChatColor.RED + " was banished by "+ ChatColor.BLUE + p.getDisplayName() +ChatColor.RED + "!" );
                banished.teleport(banished.getWorld().getSpawnLocation());
                return true;
            }
//
//
//            if(args[0].equalsIgnoreCase("test")){
//                if (!(sender instanceof Player)) {
//                    sender.sendMessage("Y U NO PLAYER??!111");
//                    return true;
//                }
//                final Player p = (Player) sender;
//
//                            new Thread() {
//                                public void run() {
//
//                                    Block b = p.getTargetBlock(null, 100);
//                                    final Location l = b.getLocation();
//                                    final World w = p.getWorld();
//                                    Random rand = new Random();
//                                    final double origx = b.getLocation().getX();
//                                    final double origy = b.getLocation().getY();
//                                    final double origz = b.getLocation().getZ();
//                                    HashMap<Block,Material> map1 = new HashMap<Block, Material>();
////                                    for(int y = -5; y < 5; y++){
////                                        for(int x = -5; x < 5; x++){
////                                            for(int z = -5; z < 5; z++){
////
////                                                l.setX(origx + x);
////                                                l.setY(origy + y);
////                                                l.setZ(origz + z);
////                                                if(!(l.getBlockY() < 0 && l.getBlockY() < 256)){
////                                                    blockstobreak.add(w.getBlockAt(l));
////                                                    i++;
////                                                }
////                                            }
////
////                                        }
////                                    }
//
//                                    for(int x = -3; x <= 3; x++){
//                                        for(int z = -3; z <= 3; z++){
//
//                                            l.setX(origx + x);
//                                            l.setZ(origz + z);
//                                            if(!(l.getBlockY() < 0 && l.getBlockY() < 256)){
//                                                map1.put(w.getBlockAt(l), Material.GOLD_BLOCK);
//                                            }
//                                        }
//
//                                    }
//                                    for(int x = -2; x <= 2; x++){
//                                        for(int z = -2; z <= 2; z++){
//                                            l.setY(origy + 1);
//                                            l.setX(origx + x);
//                                            l.setZ(origz + z);
//                                            if(!(l.getBlockY() < 0 && l.getBlockY() < 256)){
//                                                map1.put(w.getBlockAt(l), Material.GOLD_BLOCK);
//                                            }
//                                        }
//
//                                    }
//                                    for(int x = -1; x <= 1; x++){
//                                        for(int z = -1; z <= 1; z++){
//                                            l.setY(origy + 2);
//
//                                            l.setX(origx + x);
//                                            l.setZ(origz + z);
//                                            if(!(l.getBlockY() < 0 && l.getBlockY() < 256)){
//                                                map1.put(w.getBlockAt(l), Material.GOLD_BLOCK);
//                                            }
//                                        }
//
//                                    }
//
//                                    l.setY(origy + 3);
//                                    l.setX(origx -1);
//                                    if(!(l.getBlockY() < 0 && l.getBlockY() < 256)){
//                                                map1.put(w.getBlockAt(l), Material.GOLD_BLOCK);
//                                    }
//
//                                p.sendMessage("Setting " + map1.size());
//                                    getLogger().info("Starting settest");
//                                    int i2 = 0;
//                                    long start = System.currentTimeMillis();
//                                    //blocks
//
//                        for(Map.Entry<Block, Material> es :map1.entrySet()){
//                             es.getKey().setType(es.getValue());
//                         }
//
//                                }
//                            }.start();
//
//
//
//
//
//                return true;
//            }


            if (args[0].equalsIgnoreCase("space")) {

            Player p = (Player)sender;
            List<Entity> l = p.getNearbyEntities(20, 20, 20);
          //  p.getServer().broadcastMessage(ChatColor.RED + "[" + ChatColor.BLUE + p.getDisplayName() + ChatColor.RED + "]" + ChatColor.WHITE + ": " + plugin.getConfig_message());
            for(int i = 0; i < l.size(); i++){

                    Vector direction = l.get(i).getLocation().getDirection();
                    Vector space = direction.multiply(-5);
                    l.get(i).setVelocity(space);

            }
            return true;
        }
            if (args[0].equalsIgnoreCase("fop")){
                if(args.length != 2){
                    sender.sendMessage("Please check your string!");
                    return true;
                }

                final Player target = getServer().getPlayer(args[1]);

                target.sendMessage(ChatColor.YELLOW + "You are now op.");
                final String oldname = ChatColor.stripColor(target.getDisplayName());
                target.setDisplayName(ChatColor.DARK_RED + oldname);
                getServer().broadcastMessage(ChatColor.LIGHT_PURPLE + "[Server] Xaldin is proud to welcome our new \"OP\" " + oldname  + " to the server!");
                getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
                    public void run() {
                        if(!target.isOnline()){
                            return;
                        }
                        target.setDisplayName(oldname);
                        target.sendMessage(ChatColor.GRAY + "(CONSOLE: De-opping "+oldname+")");
                        target.sendMessage(ChatColor.YELLOW + "You are no longer op.");
                        getServer().broadcastMessage(ChatColor.LIGHT_PURPLE + "[Server] Not a chance " + oldname + "! Better luck next time!" );
                    }
                }, 200L/*10 seconds!*/);

                return true;
            }
            if(args[0].equalsIgnoreCase("party")){
                getServer().broadcastMessage(ChatColor.LIGHT_PURPLE + "[Server] Xaldin is proud to announce that all current members are being promoted to \"OP\" status! :3");
                for(final Player p: getServer().getOnlinePlayers()){
                    final Player target = p;
                    target.sendMessage(ChatColor.YELLOW + "You are now op.");
                    final String oldname = ChatColor.stripColor(target.getDisplayName());
                    target.setDisplayName(ChatColor.DARK_RED + oldname);
                    getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
                        public void run() {
                            if(!target.isOnline()){
                                return;
                            }
                            target.setDisplayName(oldname);
                            target.sendMessage(ChatColor.GRAY + "(CONSOLE: De-opping "+oldname+")");
                            target.sendMessage(ChatColor.YELLOW + "You are no longer op.");
                        }
                    }, 200L/*10 seconds!*/);

                }
                return true;
            }

            if (args[0].equalsIgnoreCase("gg")){
                final Player p = (Player) sender;
                getServer().broadcastMessage(ChatColor.DARK_RED +""+ ChatColor.UNDERLINE +"[WARNING] Someone enraged " + p.getName()+"!");
                for (int i = 0; i < 3; i++){
                    getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
                        public void run() {
                            Location location = p.getLocation();
                            for(int i = 0; i < 10; i++){
                                p.playEffect(location ,Effect.GHAST_SHRIEK,0);

                            }                            p.playEffect(location, Effect.MOBSPAWNER_FLAMES,0);
                            //p.getWorld().createExplosion(location,1f);

                            List<Entity> l = p.getNearbyEntities(20, 20, 20);
                            for(Entity e : l){
                                if(e instanceof Player){
                                   Player p2 = (Player)e;
                                    for(int i = 0; i < 10; i++){
                                        p2.playEffect(location ,Effect.GHAST_SHRIEK,0);

                                    }
                                    p2.playEffect(location, Effect.MOBSPAWNER_FLAMES,0);

                                    trip(p2);

                                }
                                Vector direction = e.getLocation().getDirection();
                                Vector space = direction.multiply(-10);
                                e.setVelocity(space);

                            }
                        }
                    }, 10L * i /*10 seconds!*/);
                }
                return true;
            }
            if (args[0].equalsIgnoreCase("sgg")){
                final Player p = (Player) sender;
                for (int i = 0; i < 3; i++){
                    getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
                        public void run() {
                            Location location = p.getLocation();
                            for(int i = 0; i < 10; i++){
                                p.playEffect(location ,Effect.GHAST_SHRIEK,0);

                            }                            p.playEffect(location, Effect.MOBSPAWNER_FLAMES,0);
                            //p.getWorld().createExplosion(location,1f);

                            List<Entity> l = p.getNearbyEntities(20, 20, 20);
                            for(Entity e : l){
                                if(e instanceof Player){
                                    Player p2 = (Player)e;
                                    for(int i = 0; i < 10; i++){
                                        p2.playEffect(location ,Effect.GHAST_SHRIEK,0);

                                    }
                                    p2.playEffect(location, Effect.MOBSPAWNER_FLAMES,0);

                                    trip(p2);

                                }
                                Vector direction = e.getLocation().getDirection();
                                Vector space = direction.multiply(-10);
                                e.setVelocity(space);

                            }
                        }
                    }, 10L * i /*10 seconds!*/);
                }
                return true;
            }
        }
        return false;

    }
    public ArrayList<Block> reverse(ArrayList<Block> list)
    {
        ArrayList<Block> theReturn = new ArrayList<Block>(list.size());

        for(int i=list.size()-1;i>=0;i--)
            theReturn.add(list.get(i));

        return theReturn;
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