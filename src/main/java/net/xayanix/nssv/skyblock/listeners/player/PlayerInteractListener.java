package net.xayanix.nssv.skyblock.listeners.player;

import com.bgsoftware.superiorskyblock.api.SuperiorSkyblockAPI;
import com.bgsoftware.superiorskyblock.api.island.Island;
import net.xayanix.nssv.core.utils.ChatUtil;
import org.bukkit.Material;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractListener implements Listener{

	@EventHandler
	public void onSpawner(PlayerInteractEvent event){
		if(event.getClickedBlock() == null || event.getClickedBlock().getType() != Material.SPAWNER || event.getItem() == null)
			return;

		Player player = event.getPlayer();
		Island simpleIsland = SuperiorSkyblockAPI.getIslandAt(event.getClickedBlock().getLocation());

		if(simpleIsland.getCoopPlayers().stream().noneMatch(superiorPlayer -> superiorPlayer.getUniqueId() == player.getUniqueId()) && simpleIsland.getOwner().getUniqueId().equals(player.getUniqueId())) {
			ChatUtil.sendMessage(event.getPlayer(), "&8#&c Tylko wlasciciel tego terenu moze to zrobić.");
			return;
		}


		switch (event.getItem().getType()) {
			case BLAZE_SPAWN_EGG, SHEEP_SPAWN_EGG, SKELETON_SPAWN_EGG, SPIDER_SPAWN_EGG, CHICKEN_SPAWN_EGG, COW_SPAWN_EGG, CREEPER_SPAWN_EGG, ELDER_GUARDIAN_SPAWN_EGG, ENDERMAN_SPAWN_EGG, VILLAGER_SPAWN_EGG, WITCH_SPAWN_EGG, WOLF_SPAWN_EGG, ZOMBIE_SPAWN_EGG, SALMON_SPAWN_EGG, ZOMBIFIED_PIGLIN_SPAWN_EGG, SQUID_SPAWN_EGG, SLIME_SPAWN_EGG, SILVERFISH_SPAWN_EGG, SHULKER_SPAWN_EGG, ALLAY_SPAWN_EGG, AXOLOTL_SPAWN_EGG, STRAY_SPAWN_EGG, STRIDER_SPAWN_EGG, SKELETON_HORSE_SPAWN_EGG, BAT_SPAWN_EGG, BEE_SPAWN_EGG, CAT_SPAWN_EGG, CAVE_SPIDER_SPAWN_EGG, COD_SPAWN_EGG, DOLPHIN_SPAWN_EGG, DONKEY_SPAWN_EGG, DROWNED_SPAWN_EGG, ENDERMITE_SPAWN_EGG, EVOKER_SPAWN_EGG, FOX_SPAWN_EGG, FROG_SPAWN_EGG, GHAST_SPAWN_EGG, GLOW_SQUID_SPAWN_EGG, GOAT_SPAWN_EGG, GUARDIAN_SPAWN_EGG, HOGLIN_SPAWN_EGG, HORSE_SPAWN_EGG, HUSK_SPAWN_EGG, LLAMA_SPAWN_EGG, MAGMA_CUBE_SPAWN_EGG, MULE_SPAWN_EGG, ZOMBIE_VILLAGER_SPAWN_EGG, MOOSHROOM_SPAWN_EGG, OCELOT_SPAWN_EGG, PANDA_SPAWN_EGG, PARROT_SPAWN_EGG, PHANTOM_SPAWN_EGG, PIG_SPAWN_EGG, PIGLIN_SPAWN_EGG, PILLAGER_SPAWN_EGG, PIGLIN_BRUTE_SPAWN_EGG, POLAR_BEAR_SPAWN_EGG, PUFFERFISH_SPAWN_EGG, RABBIT_SPAWN_EGG, RAVAGER_SPAWN_EGG, TADPOLE_SPAWN_EGG, TRADER_LLAMA_SPAWN_EGG, TURTLE_SPAWN_EGG, VEX_SPAWN_EGG, TROPICAL_FISH_SPAWN_EGG, VINDICATOR_SPAWN_EGG, WARDEN_SPAWN_EGG, WITHER_SKELETON_SPAWN_EGG, ZOGLIN_SPAWN_EGG, WANDERING_TRADER_SPAWN_EGG, ZOMBIE_HORSE_SPAWN_EGG -> {
				CreatureSpawner creatureSpawner = (CreatureSpawner) event.getClickedBlock().getState();
				EntityType type = EntityType.valueOf(event.getItem().getType().toString().replace("_SPAWN_EGG", ""));
				creatureSpawner.setSpawnedType(type);
				creatureSpawner.update(true);
				event.getItem().setAmount(event.getItem().getAmount() - 1);
				ChatUtil.sendMessage(event.getPlayer(), "&8#&a Spawner zostal zmieniony!");
			}
		}

	}/*

	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void onClick(PlayerInteractEvent event){

		Player player = event.getPlayer();
		Action action = event.getAction();

		if(action.equals(Action.RIGHT_CLICK_BLOCK)){
			Block block = event.getClickedBlock();
			player.getInventory().getItemInMainHand();
			if(player.getInventory().getItemInMainHand().getType() == Material.BUCKET){
				if(block.getType().equals(Material.OBSIDIAN)){
					block.setType(Material.AIR);
					player.getInventory().setItemInMainHand(new ItemStack(Material.LAVA_BUCKET, 1));
				}
			}


		}


	}*/

}
