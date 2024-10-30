package net.xayanix.nssv.skyblock.commands;

import net.xayanix.nssv.core.utils.ChatUtil;
import net.xayanix.nssv.skyblock.drop.DropManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Drop implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		DropManager.getInstance().getGUI((Player) arg0).display((Player) arg0);
		return true;
	}

}
