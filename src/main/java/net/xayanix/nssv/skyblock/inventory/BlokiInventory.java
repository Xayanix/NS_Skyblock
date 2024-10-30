package net.xayanix.nssv.skyblock.inventory;

import net.xayanix.nssv.core.interfaces.CustomInventoryAction;
import net.xayanix.nssv.core.objects.CustomInventory;
import net.xayanix.nssv.core.objects.ItemBuilder;
import net.xayanix.nssv.core.utils.ChatUtil;
import net.xayanix.nssv.skyblock.skyuser.SkyUser;
import net.xayanix.nssv.skyblock.skyuser.SkyUserManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;


public class BlokiInventory implements CustomInventoryAction {

	public static CustomInventory getGUI(Player player){
		SkyUser user = SkyUserManager.getInstance().getUser(player.getName());
		CustomInventory inventory = new CustomInventory("&6Crafting blokow", 6, player);
		inventory.background(
				new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE)
				.setName(" ")
				.toItemStack(),
				new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE)
						.setName(" ")
						.toItemStack()
				);
		inventory.setAction(new BlokiInventory());

		inventory.setItem(10, new ItemBuilder(Material.DIAMOND_BLOCK, 16)
				.setName("&bStworz 16x Diamentowy Blok")
				.setLore("&7Kliknij tutaj, aby scraftowac ten przedmiot.")
				.toItemStack());
		inventory.setItem(12, new ItemBuilder(Material.GOLD_BLOCK, 16)
				.setName("&eStworz 16x Zloty Blok")
				.setLore("&7Kliknij tutaj, aby scraftowac ten przedmiot.")
				.toItemStack());
		inventory.setItem(14, new ItemBuilder(Material.IRON_BLOCK, 16)
				.setName("&fStworz 16x Zelazny Blok")
				.setLore("&7Kliknij tutaj, aby scraftowac ten przedmiot.")
				.toItemStack());
		inventory.setItem(16, new ItemBuilder(Material.EMERALD_BLOCK, 16)
				.setName("&2Stworz 16x Emerald Blok")
				.setLore("&7Kliknij tutaj, aby scraftowac ten przedmiot.")
				.toItemStack());

		inventory.setItem(19, new ItemBuilder(Material.DIAMOND_BLOCK, 64)
				.setName("&bStworz 64x Diamentowy Blok")
				.setLore("&7Kliknij tutaj, aby scraftowac ten przedmiot.")
				.toItemStack());
		inventory.setItem(21, new ItemBuilder(Material.GOLD_BLOCK, 64)
				.setName("&eStworz 64x Zloty Blok")
				.setLore("&7Kliknij tutaj, aby scraftowac ten przedmiot.")
				.toItemStack());
		inventory.setItem(23, new ItemBuilder(Material.IRON_BLOCK, 64)
				.setName("&fStworz 64x Zelazny Blok")
				.setLore("&7Kliknij tutaj, aby scraftowac ten przedmiot.")
				.toItemStack());
		inventory.setItem(25, new ItemBuilder(Material.EMERALD_BLOCK, 64)
				.setName("&2Stworz 64x Emerald Blok")
				.setLore("&7Kliknij tutaj, aby scraftowac ten przedmiot.")
				.toItemStack());

		inventory.setItem(28, new ItemBuilder(Material.DIAMOND_BLOCK, 1)
				.setName("&bStworz max. ilosc diamentowych blokow")
				.setLore("&7Kliknij tutaj, aby scraftowac ten przedmiot.")
				.toItemStack());
		inventory.setItem(30, new ItemBuilder(Material.GOLD_BLOCK, 1)
				.setName("&eStworz max. ilosc zlotych blokow")
				.setLore("&7Kliknij tutaj, aby scraftowac ten przedmiot.")
				.toItemStack());
		inventory.setItem(32, new ItemBuilder(Material.IRON_BLOCK, 1)
				.setName("&fStworz max. ilosc zelaznych blokow")
				.setLore("&7Kliknij tutaj, aby scraftowac ten przedmiot.")
				.toItemStack());
		inventory.setItem(34, new ItemBuilder(Material.EMERALD_BLOCK, 1)
				.setName("&2Stworz max. ilosc blokow emeraldu")
				.setLore("&7Kliknij tutaj, aby scraftowac ten przedmiot.")
				.toItemStack());
		inventory.setItem(49, new ItemBuilder(Material.CLOCK, 1)
				.setName("&7Automatyczne craftowanie &e[VIP+]")
				.setLore(user.isAutoBloki() ? "&awłączone" : "&cwyłączone")
				.toItemStack());

		
		return inventory;
	}

	public static void craft(Player player, Material material, int amount){

		Material reagent = Material.AIR;
		int reagents = 0;


		switch (material){
			case DIAMOND_BLOCK:
				reagent = Material.DIAMOND;
				reagents = 9 * amount;
				break;
			case GOLD_BLOCK:
				reagent = Material.GOLD_INGOT;
				reagents = 9 * amount;
				break;
			case IRON_BLOCK:
				reagent = Material.IRON_INGOT;
				reagents = 9 * amount;
				break;
			case EMERALD_BLOCK:
				reagent = Material.EMERALD;
				reagents = 9 * amount;
				break;
		}

		if(!player.getInventory().containsAtLeast(new ItemStack(reagent), reagents)){
			ChatUtil.sendMessage(player, "&8#&c Nie masz &6" + reagents + "x " + reagent + "&c.");
			return;
		}

		player.getInventory().removeItem(new ItemStack(reagent, reagents));
		player.getInventory().addItem(new ItemStack(material, amount));

	}

	@Override
	public void handle(InventoryClickEvent inventoryClickEvent) {
		Player player = (Player) inventoryClickEvent.getWhoClicked();
		SkyUser user = SkyUserManager.getInstance().getUser(player.getName());
		inventoryClickEvent.setCancelled(true);

		if(!player.hasPermission("vipplus") && user.getLastBlokiCommand() > System.currentTimeMillis()){
			ChatUtil.sendMessage(player, "&8#&7 Tylko &eVIP+&7 nie musi czekać pomiędzy kolejnymi craftowaniami bloków...");
			return;
		}

		user.setLastBlokiCommand(System.currentTimeMillis() + 1000);

		switch (inventoryClickEvent.getSlot()){
			case 10:
				this.craft(player, Material.DIAMOND_BLOCK, 16);
				break;
			case 12:
				this.craft(player, Material.GOLD_BLOCK, 16);
				break;
			case 14:
				this.craft(player, Material.IRON_BLOCK, 16);
				break;
			case 16:
				this.craft(player, Material.EMERALD_BLOCK, 16);
				break;
			case 19:
				this.craft(player, Material.DIAMOND_BLOCK, 64);
				break;
			case 21:
				this.craft(player, Material.GOLD_BLOCK, 64);
				break;
			case 23:
				this.craft(player, Material.IRON_BLOCK, 64);
				break;
			case 25:
				this.craft(player, Material.EMERALD_BLOCK, 64);
				break;
			case 28:
				int amount = amountOfItem(Material.DIAMOND, (short) 0, player.getInventory(), true) / 9;
				if(amount == 0)
					return;

				this.craft(player, Material.DIAMOND_BLOCK, amount);
				break;
			case 30:
				amount = amountOfItem(Material.GOLD_INGOT, (short) 0, player.getInventory(), true) / 9;
				if(amount == 0)
					return;

				this.craft(player, Material.GOLD_BLOCK, amount);
				break;
			case 32:
				amount = amountOfItem(Material.IRON_INGOT, (short) 0, player.getInventory(), true) / 9;
				if(amount == 0)
					return;

				this.craft(player, Material.IRON_BLOCK, amount);
				break;
			case 34:
				amount = amountOfItem(Material.EMERALD, (short) 0, player.getInventory(), true) / 9;
				if(amount == 0)
					return;

				this.craft(player, Material.EMERALD_BLOCK, amount);
				break;
			case 49:
				if(!player.hasPermission("vipplus")){
					ChatUtil.sendMessage(player, "&8&c Nie posiadasz rangi VIP+.");
					return;
				}

				user.setAutoBloki(!user.isAutoBloki());
				ChatUtil.sendMessage(player, String.format("&8#&7 Automatyczne craftowanie zostało %s.", (user.isAutoBloki() ? "&awłączone" : "&cwyłączone")));
				player.closeInventory();
				break;
		}

	}

	public static int amountOfItem(Material m, Short data, Inventory inv, Boolean disp){
		int count = 0;
		for(ItemStack is : inv.getContents()){
			if(is != null)
				if(is.getType() == m && is.getDurability() == data)
					if(!disp)
						count = count + is.getAmount();
					else if(is.getItemMeta().getDisplayName().isEmpty())
						count = count + is.getAmount();
		}
		return count;
	}
}
