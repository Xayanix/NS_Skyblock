package net.xayanix.nssv.skyblock.basic;

import net.milkbowl.vault.economy.Economy;
import net.xayanix.nssv.core.utils.ChatUtil;
import net.xayanix.nssv.skyblock.commands.Bloki;
import net.xayanix.nssv.skyblock.commands.CasinoCommand;
import net.xayanix.nssv.skyblock.commands.Drop;
import net.xayanix.nssv.skyblock.commands.TpposCommand;
import net.xayanix.nssv.skyblock.drop.DropManager;
import net.xayanix.nssv.skyblock.economy.EconomyManager;
import net.xayanix.nssv.skyblock.inventory.BlokiInventory;
import net.xayanix.nssv.skyblock.listeners.block.BlockBreakListener;
import net.xayanix.nssv.skyblock.listeners.block.BlockGrowthListener;
import net.xayanix.nssv.skyblock.listeners.player.*;
import net.xayanix.nssv.skyblock.placeholder.StatsHolder;
import net.xayanix.nssv.skyblock.skyuser.SkyUser;
import net.xayanix.nssv.skyblock.skyuser.SkyUserManager;
import net.xayanix.nssv.skyblock.tab.TopManager;
import net.xayanix.nssv.skyblock.tasks.DummyTask;
import net.xayanix.nssv.skyblock.tasks.SaveTask;
import net.xayanix.nssv.skyblock.top.TopList;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class Main extends JavaPlugin {

    public static Economy economy = null;
    public static World ISLAND_WORLD;
    private static Main instance;

    public static Main getInstance() {
        if (instance == null) instance = new Main();
        return instance;
    }

    public void onEnable() {
        Bukkit.getScheduler().runTaskLater(this, () -> ISLAND_WORLD = Bukkit.getWorlds().get(1), 0);
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economyProvider != null) {
            economy = economyProvider.getProvider();
        }

        PluginManager pm = Bukkit.getPluginManager();
        instance = this;

        new EconomyManager();
        new SkyUserManager();
        new DropManager();
        TopManager.init();

        pm.registerEvents(new PlayerChatListener(), this);
        pm.registerEvents(new PlayerDeathListener(), this);
        pm.registerEvents(new PlayerJoinListener(), this);
       // pm.registerEvents(new PlayerInteractListener(), this);
        pm.registerEvents(new BlockBreakListener(), this);
        pm.registerEvents(new PlayerPortalListener(), this);
        new EntitySpawnListener(this);

        new EntityDamageListener(this);
        new EntityDeathListener(this);
        new BlockGrowthListener();
        new PlayerPreCommandListener();

        getCommand("kasyno").setExecutor(new CasinoCommand());
        getCommand("drop").setExecutor(new Drop());
        getCommand("tpxyz").setExecutor(new TpposCommand());
        getCommand("bloki").setExecutor(new Bloki());
        //getCommand("schowek").setExecutor(new Schowek());

        //new TopList(new Location(Bukkit.getWorlds().get(0), 0, 109, 10), "&b&lTOP 10 WYSP").register();

        new StatsHolder().register();

        /*Bukkit.getScheduler().runTaskTimer(this, () -> {
            Bukkit.getOnlinePlayers().forEach(player -> {

                if(player.hasPermission("vipplus")){
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "crate give physical Epicka 6 " + player.getName());
                } else if(player.hasPermission("vip")){
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "crate give physical Epicka 3 " + player.getName());
                } else {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "crate give physical Epicka 1 " + player.getName());
                }

            });
            ChatUtil.broadcast("&8#&5 Wszyscy gracze online dostali &7darmowe klucze do skrzynek&5!");

        }, 20 * TimeUnit.HOURS.toSeconds(3), 20 * TimeUnit.HOURS.toSeconds(3));*/

        new SaveTask();
        new DummyTask().start();

        Bukkit.getScheduler().runTaskTimer(this, () -> Bukkit.getOnlinePlayers().forEach(player -> {
            SkyUser pvPUser = SkyUserManager.getInstance().getUser(player.getName());
            if (pvPUser.isAutoBloki()) {

                int amount = BlokiInventory.amountOfItem(Material.DIAMOND, (short) 0, player.getInventory(), true) / 9;
                if (amount > 0) {
                    BlokiInventory.craft(player, Material.DIAMOND_BLOCK, amount);
                }

                amount = BlokiInventory.amountOfItem(Material.GOLD_INGOT, (short) 0, player.getInventory(), true) / 9;
                if (amount > 0) {
                    BlokiInventory.craft(player, Material.GOLD_BLOCK, amount);
                }

                amount = BlokiInventory.amountOfItem(Material.IRON_INGOT, (short) 0, player.getInventory(), true) / 9;
                if (amount > 0) {
                    BlokiInventory.craft(player, Material.IRON_BLOCK, amount);
                }

                amount = BlokiInventory.amountOfItem(Material.EMERALD, (short) 0, player.getInventory(), true) / 9;
                if (amount > 0) {
                    BlokiInventory.craft(player, Material.EMERALD_BLOCK, amount);
                }

            }
        }), 20 * 60, 20 * 60);

        getLogger().info("Uruchomiono.");

        /*Bukkit.getScheduler().runTaskTimerAsynchronously(this, () -> {
            CraftServer craftServer = (CraftServer) Bukkit.getServer();
            try {
                Field field = craftServer.getEntityMetadata().getClass().getSuperclass().getDeclaredField("metadataMap");
                field.setAccessible(true);
                field.set(craftServer.getEntityMetadata(), new ConcurrentHashMap());
                System.out.println("Cleared metadata map.");
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }, 20 * 60 * 60, 20 * 60 * 60);*/

    }

    public void onDisable() {
        getLogger().info("Wylaczono.");
    }
}
