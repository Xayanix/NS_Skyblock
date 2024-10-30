package net.xayanix.nssv.skyblock.listeners.player;

import net.xayanix.nssv.skyblock.basic.Main;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class PlayerPreCommandListener implements Listener {

    public PlayerPreCommandListener() {
        Bukkit.getPluginManager().registerEvents(this, Main.getInstance());
    }

    @EventHandler
    public void onEvent(PlayerCommandPreprocessEvent event) {
        if(event.getMessage().toLowerCase().contains("spawner")) {
            /*if(!IslandWorldApi.canBuildOnLocation(event.getPlayer(), event.getPlayer().getLocation(), false)){
                ChatUtil.sendMessage(event.getPlayer(), "&8#&c Komendy /spawner mozesz uzywac tylko na swojej wyspie.");
                event.setCancelled(true);
            }*/
        }
    }
}
