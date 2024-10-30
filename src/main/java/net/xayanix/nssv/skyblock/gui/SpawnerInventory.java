package net.xayanix.nssv.skyblock.gui;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.xayanix.nssv.core.interfaces.CustomInventoryAction;
import net.xayanix.nssv.core.objects.CustomInventory;
import net.xayanix.nssv.core.objects.ItemBuilder;
import net.xayanix.nssv.core.utils.ChatUtil;
import net.xayanix.nssv.skyblock.economy.EconomyManager;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

/*
    created by: Xayanix at 2020-07-17 00:09
*/

@RequiredArgsConstructor
@Getter
public class SpawnerInventory implements CustomInventoryAction {

    private final Block block;

    public static CustomInventory getGUI(Player player, Block block) {
        CustomInventory inventory = new CustomInventory("&6Spawner:&e " + player.getName(), 2, player);
        inventory.setAction(new SpawnerInventory(block));

        inventory.setItem(0,
                new ItemBuilder(Material.CHICKEN_SPAWN_EGG)
                        .setName("&eKurczak")
                        .setEggType(EntityType.CHICKEN)
                        .setLore("&7Cena: &e20 000$")
                        .toItemStack());
        inventory.setItem(1,
                new ItemBuilder(Material.COW_SPAWN_EGG)
                        .setName("&eKrowa")
                        .setLore("&7Cena: &e20 000$")
                        .setEggType(EntityType.COW)
                        .toItemStack());
        inventory.setItem(2,
                new ItemBuilder(Material.SHEEP_SPAWN_EGG)
                        .setName("&eOwca")
                        .setLore("&7Cena: &e20 000$")
                        .setEggType(EntityType.SHEEP)
                        .toItemStack());
        inventory.setItem(3,
                new ItemBuilder(Material.VILLAGER_SPAWN_EGG)
                        .setName("&eVillager")
                        .setLore("&7Cena: &e30 000$")
                        .setEggType(EntityType.VILLAGER)
                        .toItemStack());
        inventory.setItem(4,
                new ItemBuilder(Material.CREEPER_SPAWN_EGG)
                        .setName("&eCreeper")
                        .setLore("&7Cena: &e35 000$")
                        .setEggType(EntityType.CREEPER)
                        .toItemStack());
        inventory.setItem(5,
                new ItemBuilder(Material.ZOMBIE_SPAWN_EGG)
                        .setName("&eZombie")
                        .setLore("&7Cena: &e35 000$")
                        .setEggType(EntityType.ZOMBIE)
                        .toItemStack());
        inventory.setItem(6,
                new ItemBuilder(Material.ZOMBIFIED_PIGLIN_SPAWN_EGG)
                        .setName("&ePigman")
                        .setLore("&7Cena: &e35 000$")
                        .toItemStack());
        inventory.setItem(7,
                new ItemBuilder(Material.SPIDER_SPAWN_EGG)
                        .setName("&ePajak")
                        .setLore("&7Cena: &e35 000$")
                        .setEggType(EntityType.SPIDER)
                        .toItemStack());
        inventory.setItem(8,
                new ItemBuilder(Material.WOLF_SPAWN_EGG)
                        .setName("&eWilk")
                        .setLore("&7Cena: &e35 000$")
                        .setEggType(EntityType.WOLF)
                        .toItemStack());
        inventory.setItem(9,
                new ItemBuilder(Material.WITCH_SPAWN_EGG)
                        .setName("&eWiedzma")
                        .setLore("&7Cena: &e35 000$")
                        .setEggType(EntityType.WITCH)
                        .toItemStack());
        inventory.setItem(10,
                new ItemBuilder(Material.BLAZE_SPAWN_EGG)
                        .setName("&eBlaze")
                        .setLore("&7Cena: &e35 000$")
                        .setEggType(EntityType.BLAZE)
                        .toItemStack());
        inventory.setItem(11,
                new ItemBuilder(Material.ELDER_GUARDIAN_SPAWN_EGG)
                        .setName("&eIron Golem")
                        .setLore("&7Cena: &e500 000$")
                        .setEggType(EntityType.IRON_GOLEM)
                        .toItemStack());
        inventory.setItem(12,
                new ItemBuilder(Material.ENDERMAN_SPAWN_EGG)
                        .setName("&eEnderman")
                        .setLore("&7Cena: &e50 000$")
                        .setEggType(EntityType.ENDERMAN)
                        .toItemStack());
        inventory.setItem(13,
                new ItemBuilder(Material.SKELETON_SPAWN_EGG)
                        .setName("&eSzkielet")
                        .setLore("&7Cena: &e50 000$")
                        .setEggType(EntityType.SKELETON)
                        .toItemStack());


        return inventory;
    }

    @Override
    public void handle(InventoryClickEvent inventoryClickEvent) {
        inventoryClickEvent.setCancelled(true);
        Player player = (Player) inventoryClickEvent.getWhoClicked();
        int slot = inventoryClickEvent.getSlot();

        if (slot == 0)
            buy(player, EntityType.CHICKEN, 20000);
        else if (slot == 1)
            buy(player, EntityType.COW, 20000);
        else if (slot == 2)
            buy(player, EntityType.SHEEP, 20000);
        else if (slot == 3)
            buy(player, EntityType.VILLAGER, 30000);
        else if (slot == 4)
            buy(player, EntityType.CREEPER, 35000);
        else if (slot == 5)
            buy(player, EntityType.ZOMBIE, 35000);
        else if (slot == 6)
            buy(player, EntityType.ZOMBIFIED_PIGLIN, 35000);
        else if (slot == 7)
            buy(player, EntityType.SPIDER, 35000);
        else if (slot == 8)
            buy(player, EntityType.WOLF, 35000);
        else if (slot == 9)
            buy(player, EntityType.WITCH, 35000);
        else if (slot == 10)
            buy(player, EntityType.BLAZE, 35000);
        else if (slot == 11)
            buy(player, EntityType.IRON_GOLEM, 500000);
        else if (slot == 12)
            buy(player, EntityType.ENDERMAN, 50000);
        else if (slot == 13)
            buy(player, EntityType.SKELETON, 50000);

    }

    private void buy(Player player, EntityType entityType, int price) {
        if (EconomyManager.getInstance().getEconomy().getBalance(player) < price) {
            ChatUtil.sendMessage(player, "&8#&c Nie masz &6" + price + " zlotych&c na zakup tego spawnera.");
            return;
        }

        EconomyManager.getInstance().getEconomy().withdrawPlayer(player, price);

        CreatureSpawner creatureSpawner = (CreatureSpawner) this.block.getState();
        creatureSpawner.setSpawnedType(entityType);
        creatureSpawner.update();

        player.closeInventory();
        ChatUtil.sendMessage(player, "&8#&a Zakupiles spawner &e" + entityType + " &aza &e" + price + "$&a.");
    }

}
