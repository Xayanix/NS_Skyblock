package net.xayanix.nssv.skyblock.commands;

import net.xayanix.nssv.core.utils.ChatUtil;
import net.xayanix.nssv.skyblock.skyuser.SkyUser;
import net.xayanix.nssv.skyblock.skyuser.SkyUserManager;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.concurrent.TimeUnit;

public class Schowek implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		Player player = (Player) arg0;
		SkyUser skyUser = SkyUserManager.getInstance().getUser(arg0.getName());

		if(arg3.length == 1 && arg3[0].equalsIgnoreCase("wyrzuc")){
			if(skyUser.getLastInventoryDrop() > System.currentTimeMillis()){
				ChatUtil.sendMessage(arg0, "&8#&c Mozesz uzywac ta komende raz na 5 minut.");
				return false;
			}

			skyUser.setLastInventoryDrop(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(5));

			for(ItemStack is : skyUser.getChestInventory().getContents())
				if(is != null && is.getType() != Material.AIR)
					player.getWorld().dropItemNaturally(player.getLocation(), is);
			skyUser.getChestInventory().clear();

			ChatUtil.sendMessage(player, "&8#&a Wyrzucono na ziemie zawartosc schowka.");

			return false;
		}

		((Player) arg0).openInventory(skyUser.getChestInventory());

		ChatUtil.sendMessage(arg0, "&8#&c Uwaga: pamietaj, aby wyciagnac wszystkie rzeczy ze schowka. Chcesz wyrzucic zawartosc schowka na ziemie? Wpisz &6/schowek wyrzuc&c.");
		return true;
	}

}
