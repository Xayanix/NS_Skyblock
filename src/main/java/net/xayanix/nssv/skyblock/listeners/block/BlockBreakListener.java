package net.xayanix.nssv.skyblock.listeners.block;

import net.xayanix.nssv.core.utils.RandomUtil;
import net.xayanix.nssv.hoppers.HopperReplacement;
import net.xayanix.nssv.hoppers.HopperSystem;
import net.xayanix.nssv.skyblock.basic.Main;
import net.xayanix.nssv.skyblock.drop.Drop;
import net.xayanix.nssv.skyblock.drop.DropManager;
import net.xayanix.nssv.skyblock.skyuser.SkyUser;
import net.xayanix.nssv.skyblock.skyuser.SkyUserManager;
import net.xayanix.nssv.skyblock.utils.ItemUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;


public class BlockBreakListener implements Listener {

	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void onBreak(BlockBreakEvent event) {
		Player player = event.getPlayer();
		Block block = event.getBlock();
		SkyUser skyUser = SkyUserManager.getInstance().getUser(player.getName());

		if (event.getBlock().getType().equals(Material.COBBLESTONE) || event.getBlock().getType().equals(Material.STONE)) {
			skyUser.setBrokenBlocks(skyUser.getBrokenBlocks() + 1);
			int dodatek = 0;
			Material type = event.getBlock().getType();

			if (RandomUtil.getChance(10)) {
				event.getPlayer().giveExp(1);
			}

			if (event.getBlock().getType().equals(Material.STONE)) {
				dodatek += 0.5;
			}
			if (player.hasPermission("vip")) {
				dodatek += 1.0;
			}
			if (player.hasPermission("vipplus")) {
				dodatek += 0.5;
			}

			for (Drop drop : DropManager.getInstance().getDropList())
				if (!skyUser.getDisabledDrops().contains(drop) && RandomUtil.getChance(drop.getChance() + dodatek)) {
					HopperSystem hopperSystem = HopperReplacement.INSTANCE.getHopperSystemManager().getPlotSystem().getHopperSystem(block.getLocation(), null);
					if(hopperSystem != null){
						hopperSystem.addItem(drop.getItemStack().clone());
					}
					else {
						ItemUtil.giveItem(player, skyUser, drop.getItemStack().clone());
					}
					break;
				}

			if (event.getBlock().getLocation().add(0.0, -1.0, 0.0).getBlock().getType() == Material.END_STONE) {
				Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> {
					if (event.getBlock().getType() == Material.AIR) {
						event.getBlock().setType(type);
					}
				}, 15);
			}

			if (skyUser.isStoneDropDisabled()) {
				block.setType(Material.AIR);
			}


		}/* else if(event.getBlock().getType().toString().contains("SPAWNER")){
			event.setDropItems(false);
			event.setCancelled(true);
			event.getBlock().setType(Material.AIR);
		}*/

		Collection<ItemStack> drops = event.getBlock().getDrops();
		switch (block.getType()) {
			case BLACK_SHULKER_BOX:
			case RED_SHULKER_BOX:
			case BLUE_SHULKER_BOX:
			case CYAN_SHULKER_BOX:
			case GRAY_SHULKER_BOX:
			case PINK_SHULKER_BOX:
			case LIME_SHULKER_BOX:
			case SHULKER_SHELL:
			case BROWN_SHULKER_BOX:
			case GREEN_SHULKER_BOX:
			case WHITE_SHULKER_BOX:
			case ORANGE_SHULKER_BOX:
			case PURPLE_SHULKER_BOX:
			case LIGHT_GRAY_SHULKER_BOX:
			case YELLOW_SHULKER_BOX:
			case MAGENTA_SHULKER_BOX:
			case LIGHT_BLUE_SHULKER_BOX:
			case ACACIA_LEAVES:
			case OAK_LEAVES:
			case BIRCH_LEAVES:
			case AZALEA_LEAVES:
			case JUNGLE_LEAVES:
			case SPRUCE_LEAVES:
			case DARK_OAK_LEAVES:
			case MANGROVE_LEAVES:
			case FLOWERING_AZALEA_LEAVES:
			case NETHER_WART:
			case CARROT:
			case POTATO:
			case ENDER_CHEST:
			case SUGAR_CANE:
			case CHEST:
			case LEGACY_MOB_SPAWNER:
			case LEGACY_MELON_BLOCK:
			case MELON_SEEDS:
			case MELON_STEM:
			case MELON:
			case INK_SAC:
			case SPAWNER:
			case WHEAT:
			case WHEAT_SEEDS:
			case SNOW:
			case SNOW_BLOCK:
/*			case IRON_ORE:
			case GOLD_ORE:
			case COAL_ORE:
			case LAPIS_ORE:
			case REDSTONE_ORE:
			case DEEPSLATE_REDSTONE_ORE:*/
			case VINE:
			case LILY_PAD:
			case LEGACY_CROPS:
			case PUMPKIN:
			case PUMPKIN_PIE:
			case PUMPKIN_SEEDS:
			case PUMPKIN_STEM:
			case COCOA:
			case MELON_SLICE:
			case LEGACY_MELON:
			case BEETROOT:
			case LEGACY_BEETROOT_BLOCK:
			case BEETROOT_SEEDS:
			case BEETROOT_SOUP:
				return;
			/*case DIAMOND_ORE:
				drops.stream()
						.filter(drop -> drop.getType() == Material.DIAMOND)
						.forEach(drop -> drop.setType(Material.DIAMOND_ORE));
				break;*/
		}

		ItemUtil.recalculateDurability(player, player.getInventory().getItemInMainHand());

		boolean silktouch = player.getInventory().getItemInHand().getType() != Material.AIR && player.getInventory().getItemInHand().containsEnchantment(Enchantment.SILK_TOUCH);
		Material material = block.getType();

		if (silktouch) {
			ItemUtil.giveItem(player, skyUser, new ItemStack(material, 1, block.getData()));
		}
		else {
			for (ItemStack drop : drops)
				ItemUtil.giveItem(player, skyUser, drop);
		}
		block.setType(Material.AIR);

	}
	
	/*@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void onPlace(BlockPlaceEvent event){
		if(event.getBlock().getType() == Material.ENDER_STONE){
			event.getBlock().getLocation().add(0.0, 1.0, 0.0).getBlock().setType(Material.COBBLESTONE);
		}

		if(!event.getPlayer().hasPermission("admin")) {
			if(event.getPlayer().getWorld().getName().contains("nether")) {
				if(event.getBlock().getType() != Material.COBBLESTONE) {
					event.setCancelled(true);
					ChatUtil.sendMessage(event.getPlayer(), "&8#&c W netherze mozesz stawiac tylko cobblestone.");
				}
			}
		}

	}*/

}
