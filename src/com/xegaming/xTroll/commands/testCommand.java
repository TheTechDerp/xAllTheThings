package com.xegaming.xTroll.commands;

import com.xegaming.xTroll.commandBase;
import com.xegaming.xTroll.xTroll;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * User: Benjamin
 * Date: 21/07/12
 * Time: 04:39
 */
public class testCommand extends commandBase {
    @Override
    protected void run(CommandSender sender, String[] args, Player player, String playerName) {
        if (xTroll.permission.has(player, "XT.test")) {

        }
    }

    public testCommand(xTroll XE) {
        super(XE);
    }
}
