package net.xayanix.nssv.skyblock.listeners.player;

import com.bgsoftware.superiorskyblock.api.SuperiorSkyblockAPI;
import com.bgsoftware.superiorskyblock.api.island.Island;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class EntityDamageListener implements Listener {

    public EntityDamageListener(JavaPlugin instance) {
        Bukkit.getPluginManager().registerEvents(this, instance);
    }

    @EventHandler
    public void onEvent(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (!player.isOnline())
                return;

            Island simpleIsland = SuperiorSkyblockAPI.getIslandAt(player.getLocation());

            if(event.getCause() == EntityDamageEvent.DamageCause.FALL && player.getInventory().getChestplate() != null && player.getInventory().getChestplate().getType() == Material.ELYTRA){
                event.setCancelled(true);
                return;
            }

            if (simpleIsland != null) {
                if (event.getCause() == EntityDamageEvent.DamageCause.VOID) {
                    event.setCancelled(true);
                    player.teleport(Bukkit.getWorlds().get(0).getSpawnLocation());
                }

                if (simpleIsland.getCoopPlayers().stream().noneMatch(superiorPlayer -> superiorPlayer.getUniqueId() == player.getUniqueId()) && simpleIsland.getOwner().getUniqueId().equals(player.getUniqueId())) {
                    event.setCancelled(true);
                }

            } else if (event.getCause() == EntityDamageEvent.DamageCause.VOID) {
                event.setCancelled(true);
                player.teleport(Bukkit.getWorlds().get(0).getSpawnLocation());
            }

        }

    }
}
