package net.xayanix.nssv.skyblock.commands;

import net.xayanix.nssv.skyblock.inventory.BlokiAntyAfkInventory;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class Bloki implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		BlokiAntyAfkInventory.getGUI((Player) arg0).display((Player) arg0);
		return true;
	}

}
