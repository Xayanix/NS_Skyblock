package net.xayanix.nssv.skyblock.listeners.player;

import net.xayanix.nssv.core.utils.RandomUtil;
import net.xayanix.nssv.skyblock.skyuser.SkyUser;
import net.xayanix.nssv.skyblock.skyuser.SkyUserManager;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.Bukkit;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class EntityDeathListener implements Listener {

    public EntityDeathListener(JavaPlugin instance) {
        Bukkit.getPluginManager().registerEvents(this, instance);
    }

    @EventHandler
    public void onEvent(EntityDeathEvent event) {

        switch (event.getEntityType()){
            case IRON_GOLEM -> event.getDrops().removeIf(drop -> drop.getType() == Material.POPPY);
            case COW -> event.getDrops().removeIf(drop -> drop.getType() == Material.BEEF);
            case PIG -> event.getDrops().removeIf(drop -> drop.getType() == Material.PORKCHOP);
            case SHEEP -> event.getDrops().removeIf(drop -> drop.getType() == Material.MUTTON);
        }

        if(event.getEntity().getKiller() != null){
            Player player = event.getEntity().getKiller();
            SkyUser skyUser = SkyUserManager.getInstance().getUser(player.getName());

            if(RandomUtil.getChance(25))
                skyUser.addJobPoint(player);

        }
    }
}
