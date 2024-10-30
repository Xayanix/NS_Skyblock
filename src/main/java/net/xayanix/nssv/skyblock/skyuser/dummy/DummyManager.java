package net.xayanix.nssv.skyblock.skyuser.dummy;

import net.xayanix.nssv.skyblock.skyuser.SkyUser;
import net.xayanix.nssv.skyblock.skyuser.SkyUserManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class DummyManager {

	@SuppressWarnings("deprecation")
	public static void updatePlayers(){
		for(Player player : Bukkit.getOnlinePlayers())
			updateScore(SkyUserManager.getInstance().getUser(player.getName()));
	}

	@SuppressWarnings("deprecation")
	public static void updateScore(SkyUser user){
		for(Player player : Bukkit.getOnlinePlayers()){
			SkyUser userr = SkyUserManager.getInstance().getUser(player.getName());
			if(userr != null)
				userr.getDummy().updateScore(user);
		}
	}
}
