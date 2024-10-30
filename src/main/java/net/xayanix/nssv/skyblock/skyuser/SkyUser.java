package net.xayanix.nssv.skyblock.skyuser;

import lombok.Getter;
import lombok.Setter;
import net.milkbowl.vault.economy.Economy;
import net.xayanix.nssv.core.mongo.SerializeObject;
import net.xayanix.nssv.core.utils.ChatUtil;
import net.xayanix.nssv.core.utils.RandomUtil;
import net.xayanix.nssv.skyblock.drop.Drop;
import net.xayanix.nssv.skyblock.economy.EconomyManager;
import net.xayanix.nssv.skyblock.skyuser.dummy.Dummy;
import net.xayanix.nssv.skyblock.skyuser.jobs.JobType;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.scoreboard.Scoreboard;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Getter @Setter
public class SkyUser extends SerializeObject {

    private String name;
    private int brokenBlocks;
    private long lastKitReceived;

    private transient long lastInventoryDrop;
    private transient Inventory chestInventory;
    private transient Scoreboard scoreboard;
    private transient Dummy dummy;
    private transient int money;
    private transient boolean stoneDropDisabled;
    private transient List<Drop> disabledDrops;
    private transient ConcurrentHashMap<String, Long> cooldownMap;
    private transient String casinoInvite;
    private transient int casinoMoney;
    private transient Block spawnerBlock;

    private transient long lastBlokiCommand;
    private boolean autoBloki;

    public SkyUser(String name) {
        super("skyblock-db", "name", name);
        this.name = name;
        this.chestInventory = Bukkit.createInventory(null, 54);
        this.disabledDrops = new CopyOnWriteArrayList<>();

        SkyUserManager.getInstance().getSkyUsers().put(name.toLowerCase(), this);
    }

    public void loaded(){
        this.cooldownMap = new ConcurrentHashMap<>();
        this.casinoInvite = "";
    }

    public int getMinProgress(){
        return 0;
    }

    public void addJobPoint(Player player){
        /*this.progress++;
        if(this.progress >= this.getMinProgress()){
            this.progress = 0;
            this.level++;

            ChatUtil.sendMessage(player, "&8#&7 Nowy poziom pracy!");
            ChatUtil.sendMessage(player, "&8#&7 Twoj poziom pracy to teraz &a" + this.level + " Lv&7.");
        }

        if(this.level < 100){
            if(RandomUtil.getChance(this.level))
                money++;

            return;
        }*/

        /*double money = 0.5 + this.level / 1000.0;
        if(money > 2)
            money = 2;
        this.money+=money;*/

    }

    public boolean isCooldownFinished(String key, long cooldown){
        if(!cooldownMap.containsKey(key)){
            cooldownMap.put(key, System.currentTimeMillis() + cooldown);
            return true;
        }

        if(cooldownMap.get(key) > System.currentTimeMillis())
            return false;

        cooldownMap.put(key, System.currentTimeMillis() + cooldown);
        return true;
    }

    public void cancelCooldown(String key){
        this.cooldownMap.remove(key);
    }

}
