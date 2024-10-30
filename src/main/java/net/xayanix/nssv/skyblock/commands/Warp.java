package net.xayanix.nssv.skyblock.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Warp implements CommandExecutor{

	public static Location expiarka = new Location(Bukkit.getWorld("IslandWorld"), -252, 78, 317);
	public static Location enchant = new Location(Bukkit.getWorld("IslandWorld"), -287, 110, -253);
	public static Location informacje = new Location(Bukkit.getWorld("IslandWorld"), -306, 110, -288);
	public static Location pvp = new Location(Bukkit.getWorld("IslandWorld"), -391, 256, -553);
	public static Location parkour = new Location(Bukkit.getWorld("IslandWorld"), -304, 70, -1139);
	public static Location tartak = new Location(Bukkit.getWorld("IslandWorld"), -547, 50, -275);
	public static Location wymieniarki = new Location(Bukkit.getWorld("IslandWorld"), -301, 110, -284);
	
	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		Player player = (Player) arg0;
		Inventory inv = Bukkit.createInventory(null, 9, "�7Menu warpow");
		prepareInventory(inv);
		player.openInventory(inv);
		return true;
	}
	
	public static void prepareInventory(Inventory inv){
		inv.setItem(1, item(Material.ENCHANTING_TABLE, "&9Enchant").clone());
		inv.setItem(2, item(Material.EXPERIENCE_BOTTLE, "&eExpiarka").clone());
		inv.setItem(3, item(Material.PAPER, "&aInformacje").clone());
		inv.setItem(4, item(Material.WATER_BUCKET, "&7Parkour").clone());
		inv.setItem(5, item(Material.BOW, "&4PvP").clone());
		inv.setItem(6, item(Material.ACACIA_PLANKS, "&0-"));
		inv.setItem(7, item(Material.GOLD_INGOT, "&1Wymieniarki").clone());
	}
	
	private static ItemStack item(Material item, String nazwa){
		ItemStack is = new ItemStack(item, 1);
		ItemMeta im = is.getItemMeta();
		
		im.setDisplayName("�8 ��7 Teleportacja do:�e " + nazwa.replace("&", "�"));
		List<String> test = new ArrayList<String>();
		test.add("�8 ��7 Kliknij w ta ikone, aby sie teleportowac.");
		im.setLore(test);

		is.setItemMeta(im);
		
		return is;
	}
}
