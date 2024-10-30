package net.xayanix.nssv.skyblock.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import net.xayanix.nssv.core.runnable.TickingTask;
import net.xayanix.nssv.core.utils.ChatUtil;
import net.xayanix.nssv.core.utils.RandomUtil;
import net.xayanix.nssv.skyblock.skyuser.SkyUser;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.Hopper;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemUtil {

	public static ItemStack namedItem(ItemStack is, String name, String[] lore){
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(ChatUtil.fixColors(name));
		List<String> lorelist = new ArrayList<String>();
		
		for(String s : lore)
			lorelist.add(ChatUtil.fixColors(s));
		
		im.setLore(lorelist);
		is.setItemMeta(im);
		
		return is;
	}

	public static void giveItem(Player player, SkyUser skyUser, ItemStack is){
		Block block;
		if(player.getInventory().firstEmpty() != -1)
			player.getInventory().addItem(is);
		//else if(skyUser.getChestInventory().firstEmpty() != -1)
		//	skyUser.getChestInventory().addItem(is);
		else if((block = player.getLocation().add(0, -1, 0).getBlock()).getType() == Material.HOPPER){
			Hopper hopper = (Hopper) block.getState();
			hopper.getInventory().addItem(is);
		}
	}

	public static int freeSlotsInInventory(Player player){
		return freeSlotsInInventory(player.getInventory());
	}

	public static int freeSlotsInInventory(Inventory inventory){
		int free = 0;
		for(ItemStack is : inventory.getContents())
			if(is == null || is.getType() == Material.AIR)
				free++;

		return free;
	}

	public static void recalculateDurability(Player player, ItemStack item)
	{
		if(item == null) return;
		if (item.getType().getMaxDurability() == 0) {
			return;
		}
		int enchantLevel = item.getEnchantmentLevel(Enchantment.DURABILITY);
		short d = item.getDurability();
		if (enchantLevel > 0)
		{
			if (100 / (enchantLevel + 1) > RandomUtil.getRandom().nextInt(100)) {
				if (d == item.getType().getMaxDurability())
				{
					player.getInventory().clear(player.getInventory().getHeldItemSlot());
					player.playSound(player.getLocation(), Sound.ENTITY_ITEM_BREAK, 1.0F, 1.0F);
				}
				else
				{
					item.setDurability((short)(d + 1));
				}
			}
		}
		else if (d == item.getType().getMaxDurability())
		{
			player.getInventory().clear(player.getInventory().getHeldItemSlot());
			player.playSound(player.getLocation(), Sound.ENTITY_ITEM_BREAK, 1.0F, 1.0F);
		}
		else
		{
			item.setDurability((short)(d + 1));
		}
	}

}
