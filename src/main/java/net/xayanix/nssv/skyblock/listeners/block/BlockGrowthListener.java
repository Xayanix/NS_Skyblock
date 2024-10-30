package net.xayanix.nssv.skyblock.listeners.block;

import lombok.Getter;
import lombok.Setter;
import net.xayanix.nssv.core.basic.Main;
import net.xayanix.nssv.core.utils.RandomUtil;

import org.bukkit.Chunk;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.Bukkit;
import org.bukkit.event.block.BlockGrowEvent;

import java.util.concurrent.ConcurrentHashMap;

public class BlockGrowthListener implements Listener {

    @Getter
    private ConcurrentHashMap<String, Long> growthMap;

    @Getter
    private static BlockGrowthListener instance;

    public BlockGrowthListener() {
        instance = this;
        this.growthMap = new ConcurrentHashMap<>();
        Bukkit.getPluginManager().registerEvents(this, Main.getInstance());
    }

    @Getter @Setter
    private static int cactusCooldown = 8000;

    @EventHandler(ignoreCancelled = true)
    public void onDrop(BlockGrowEvent event){
        if(RandomUtil.getChance(60))
            event.setCancelled(true);
    }

    private boolean isOnCooldown(Chunk chunk){
        long time = (this.growthMap.containsKey(chunk.getX() + ":" + chunk.getZ()) ? this.growthMap.get(chunk.getX() + ":" + chunk.getZ()) : 0);
        if(time > System.currentTimeMillis())
            return true;
        this.growthMap.put(chunk.getX() + ":" + chunk.getZ(), System.currentTimeMillis() + cactusCooldown);
        return false;
    }

}
