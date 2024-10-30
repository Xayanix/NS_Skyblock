package net.xayanix.nssv.skyblock.basic;

import com.bgsoftware.superiorskyblock.api.island.Island;
import net.xayanix.nssv.core.utils.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class SChatUtil {

	public static String fixColors(String message) {
		if(message == null){
			return "";
		}
		return (org.bukkit.ChatColor.translateAlternateColorCodes('&', message)).replaceFirst("#", " §6§lN§e§lS§8 »§7");
	}

	public static String[] fixColors(String[] message) {
		String[] strings = message;
		for (int i = 0; i < strings.length; i++) {
			strings[i] = fixColors(strings[i]);
		}
		return strings;
	}

	public static void sendMessage(Player p, String message) {
		p.sendMessage(SChatUtil.fixColors(message));
	}
	
	public static void sendMessage(CommandSender cs, String message) {
		cs.sendMessage(SChatUtil.fixColors(message));
	}
	
	public static void sendMessage(Player p, String[] messages) {
		p.sendMessage(fixColors(messages));
	}

	public static void sendMessage(Player p, List<String> message) {
		for (int i = 0; i < message.size(); i++) {
			sendMessage(p, message.get(i));
		}
	}
	
	@SuppressWarnings("deprecation")
	public static void broadcast(String message){
		message = fixColors(message);
		for(Player player : Bukkit.getOnlinePlayers()){
			player.sendMessage(message);
		}
		
		Bukkit.getConsoleSender().sendMessage(message);
	}
	
	@SuppressWarnings("deprecation")
	public static void broadcast(String message, String permission){
		message = fixColors(message);
		for(Player player : Bukkit.getOnlinePlayers()){
			if(player.hasPermission(permission))
				player.sendMessage(message);
		}
		
		Bukkit.getConsoleSender().sendMessage(message);
	}

	public static void sendMessage(Island island, String message){
		message = ChatUtil.fixColors(message);
		String finalMessage = message;

		island.getCoopPlayers().forEach(key -> {
			Player player = Bukkit.getPlayer(key.getUniqueId());
			if (player != null)
				player.sendMessage(finalMessage);
		});

		island.getIslandMembers(true).forEach(superiorPlayer -> {
			Player player = Bukkit.getPlayer(superiorPlayer.getUniqueId());
			if (player != null)
				player.sendMessage(finalMessage);
		});
	}

	/*public static void sendTitle(SimpleIsland island, String title, String subtitle){
		Player owner = Bukkit.getPlayerExact(island.getOwner());
		if(owner != null)
			TitleUtil.set(owner, title, subtitle);
		island.getMembers().forEach(m -> {
			Player player = Bukkit.getPlayerExact(m);
			if(player != null)
				TitleUtil.set(player, title, subtitle);
		});
	}*/
	
}
