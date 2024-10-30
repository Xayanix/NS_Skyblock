package net.xayanix.nssv.skyblock.listeners.player;

import net.xayanix.nssv.core.utils.ChatUtil;
import net.xayanix.nssv.skyblock.basic.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener{

	@EventHandler
	public void onDeath(PlayerDeathEvent event){
		
		if(event.getEntity().getKiller() == null){
			event.setDeathMessage(ChatUtil.fixColors("&8#&c " + event.getEntity().getName() + "&7 zginal."));
			return;
		}
		else if(event.getEntity().getKiller() != null){
			Player entity = event.getEntity().getKiller();
			event.setDeathMessage(ChatUtil.fixColors("&8#&c " +  event.getEntity().getName() + " &7zostal zabity przez &c" + entity.getName() + "&7."));
		}
		else event.setDeathMessage(ChatUtil.fixColors("&8#&c " + event.getEntity().getName() + "&7 zginal."));
		
		Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> event.getEntity().spigot().respawn(), 1);
		
	}
	
}
