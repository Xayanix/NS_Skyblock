package net.xayanix.nssv.skyblock.listeners.player;

import com.bgsoftware.wildstacker.api.events.SpawnerStackedEntitySpawnEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class EntitySpawnListener implements Listener {

    public EntitySpawnListener(JavaPlugin instance) {
        Bukkit.getPluginManager().registerEvents(this, instance);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onEvent(EntitySpawnEvent event) {
        if(event.getEntityType() == EntityType.DROPPED_ITEM){
            Item item = (Item) event.getEntity();
            if(item.getItemStack().getType() == Material.AIR || item.getItemStack().getType() == Material.LEGACY_AIR){
                event.setCancelled(true);
            }

        }
    }


}
