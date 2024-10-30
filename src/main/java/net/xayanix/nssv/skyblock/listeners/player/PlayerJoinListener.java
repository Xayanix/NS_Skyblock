package net.xayanix.nssv.skyblock.listeners.player;

import net.xayanix.nssv.skyblock.skyuser.SkyUser;
import net.xayanix.nssv.skyblock.skyuser.SkyUserManager;
import net.xayanix.nssv.skyblock.skyuser.dummy.Dummy;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import net.xayanix.nssv.skyblock.managers.PrefixManager;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerJoinListener implements Listener{
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event){
		Player player = event.getPlayer();
		player.setNoDamageTicks(20);

		SkyUser skyUser = SkyUserManager.getInstance().getUser(player.getName());
		if(skyUser == null){
			skyUser = new SkyUser(player.getName());
			skyUser.insert();
		}

		skyUser.setScoreboard(player.getScoreboard());
		skyUser.setDummy(new Dummy(skyUser));
		
		if(!player.hasPlayedBefore())
			player.teleport(Bukkit.getWorlds().get(0).getSpawnLocation());
	}

	@EventHandler
	public void onQuit(PlayerQuitEvent event){
		Player player = event.getPlayer();
		SkyUser skyUser = SkyUserManager.getInstance().getUser(player.getName());
		skyUser.setSpawnerBlock(null);
		skyUser.synchronize();
	}

}
