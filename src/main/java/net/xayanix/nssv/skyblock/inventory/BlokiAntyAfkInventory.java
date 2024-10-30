package net.xayanix.nssv.skyblock.inventory;

import net.xayanix.nssv.core.interfaces.CustomInventoryAction;
import net.xayanix.nssv.core.objects.CustomInventory;
import net.xayanix.nssv.core.objects.ItemBuilder;
import net.xayanix.nssv.core.utils.ChatUtil;
import net.xayanix.nssv.core.utils.RandomUtil;
import net.xayanix.nssv.skyblock.skyuser.SkyUser;
import net.xayanix.nssv.skyblock.skyuser.SkyUserManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;


public class BlokiAntyAfkInventory implements CustomInventoryAction {

	public static CustomInventory getGUI(Player player){
		SkyUser user = SkyUserManager.getInstance().getUser(player.getName());
		CustomInventory inventory = new CustomInventory("&cANTY AFK", 6, player);
		inventory.background(
				new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE)
				.setName(" ")
				.toItemStack(),
				new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE)
						.setName(" ")
						.toItemStack()
				);
		inventory.setAction(new BlokiAntyAfkInventory());

		inventory.setItem(RandomUtil.getRandom().nextInt(54), new ItemBuilder(Material.GOLD_BLOCK)
						.setName("&eOtwórz crafting bloków")
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
		inventoryClickEvent.setCancelled(true);
		Player player = (Player) inventoryClickEvent.getWhoClicked();

		if(inventoryClickEvent.getClickedInventory() == null){
			return;
		}

		player.closeInventory();

		Material material = inventoryClickEvent.getClickedInventory().getItem(inventoryClickEvent.getSlot()).getType();
		if(material == Material.GOLD_BLOCK){
			BlokiInventory.getGUI(player).display(player);
		}



	}

}
