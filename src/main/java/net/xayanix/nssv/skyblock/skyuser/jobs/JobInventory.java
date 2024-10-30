package net.xayanix.nssv.skyblock.skyuser.jobs;

import lombok.AllArgsConstructor;
import net.xayanix.nssv.core.objects.ItemBuilder;
import net.xayanix.nssv.core.utils.ChatUtil;
import net.xayanix.nssv.skyblock.skyuser.SkyUser;
import net.xayanix.nssv.skyblock.skyuser.SkyUserManager;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;

import java.util.concurrent.TimeUnit;

@AllArgsConstructor
public class JobInventory {

    private Player player;

    public void set(JobType jobType){
        SkyUser skyUser = SkyUserManager.getInstance().getUser(player.getName());
        if(skyUser.getLastKitReceived() > System.currentTimeMillis())
            return;

        skyUser.setLastKitReceived(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(12));
        ChatUtil.sendMessage(player, "&8#&a Otrzymales zestaw startowy.");
        switch (jobType){
            case GORNIK:
                player.getInventory().addItem(
                        new ItemBuilder(Material.IRON_PICKAXE)
                        .addEnchant(Enchantment.DURABILITY, 2)
                        .addEnchant(Enchantment.DIG_SPEED, 1).toItemStack(),
                        new ItemBuilder(Material.COOKED_BEEF, 4).toItemStack(),
                        new ItemBuilder(Material.COOKED_CHICKEN).toItemStack(),
                        new ItemBuilder(Material.BREAD, 3).toItemStack(),
                        new ItemBuilder(Material.SPONGE, 3).toItemStack()
                );
                break;
            case DRWAL:
                player.getInventory().addItem(
                        new ItemBuilder(Material.IRON_AXE)
                                .addEnchant(Enchantment.DURABILITY, 2)
                                .addEnchant(Enchantment.DIG_SPEED, 1).toItemStack(),
                        new ItemBuilder(Material.COOKED_BEEF, 4).toItemStack(),
                        new ItemBuilder(Material.GOLDEN_APPLE).toItemStack(),
                        new ItemBuilder(Material.BREAD, 4).toItemStack(),
                        new ItemBuilder(Material.BIRCH_SAPLING, 5, (short) 0).toItemStack(),
                        new ItemBuilder(Material.ACACIA_SAPLING, 5, (short) 1).toItemStack(),
                        new ItemBuilder(Material.OAK_SAPLING, 5, (short) 2).toItemStack()
                );
                break;
            /*case FARMER:
                player.getInventory().addItem(
                        new ItemBuilder(Material.GOLD_HOE)
                                .addEnchant(Enchantment.DURABILITY, 2).toItemStack(),
                        new ItemBuilder(Material.IRON_SPADE)
                                .addEnchant(Enchantment.DURABILITY, 1).toItemStack(),
                        new ItemBuilder(Material.COOKED_BEEF, 4).toItemStack(),
                        new ItemBuilder(Material.APPLE, 2).toItemStack(),
                        new ItemBuilder(Material.BREAD, 3).toItemStack(),
                        new ItemBuilder(Material.SEEDS, 32).toItemStack(),
                        new ItemBuilder(Material.MELON_SEEDS, 5).toItemStack(),
                        new ItemBuilder(Material.WATER_BUCKET, 1).toItemStack()
                );
                break;*/
            case LOWCA:
                player.getInventory().addItem(
                        new ItemBuilder(Material.IRON_SWORD)
                                .addEnchant(Enchantment.DAMAGE_ALL, 3).toItemStack(),
                        new ItemBuilder(Material.COOKED_BEEF, 4).toItemStack()
                );
                break;
        }

    }

}
