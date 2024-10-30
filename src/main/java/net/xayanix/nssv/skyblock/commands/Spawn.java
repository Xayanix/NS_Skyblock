package net.xayanix.nssv.skyblock.commands;

import net.xayanix.nssv.core.mongo.MongoConnection;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Spawn implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {


		((Player) arg0).teleport(Bukkit.getWorlds().get(0).getSpawnLocation());
		return true;
	}

}
