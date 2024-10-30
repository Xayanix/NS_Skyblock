package net.xayanix.nssv.skyblock.drop;

import lombok.Getter;
import net.xayanix.nssv.core.interfaces.CustomInventoryAction;
import net.xayanix.nssv.core.inventory.actions.PlayerCheckAction;
import net.xayanix.nssv.core.inventory.ocactions.CheckAction;
import net.xayanix.nssv.core.objects.CustomInventory;
import net.xayanix.nssv.core.objects.ItemBuilder;
import net.xayanix.nssv.core.utils.ChatUtil;
import net.xayanix.nssv.hoppers.HopperReplacement;
import net.xayanix.nssv.skyblock.skyuser.SkyUser;
import net.xayanix.nssv.skyblock.skyuser.SkyUserManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DropManager {

    @Getter
    private static DropManager instance;

    @Getter
    private List<Drop> dropList;

    public DropManager() {
        instance = this;
        this.dropList = new ArrayList<>();

        this.dropList.addAll(Arrays.asList(
                new Drop(new ItemStack(Material.DIAMOND), 0.625),
                new Drop(new ItemStack(Material.REDSTONE), 0.640),
                new Drop(new ItemStack(Material.COAL), 0.655),
                new Drop(new ItemStack(Material.GOLD_INGOT), 0.675),
                new Drop(new ItemStack(Material.IRON_INGOT), 0.205),
                new Drop(new ItemStack(Material.ANCIENT_DEBRIS), 0.611),
                new Drop(new ItemBuilder(Material.LAPIS_LAZULI, 3).toItemStack(), 0.135)
        ));

        dropList.forEach(drop -> HopperReplacement.INSTANCE.getHopperSystemManager()
                .getItems()
                .add(drop.getItemStack().getType()));

        HopperReplacement.INSTANCE.getHopperSystemManager().getItems().addAll(HopperReplacement.INSTANCE.getHopperSystemManager().getItemsPremium());
        HopperReplacement.INSTANCE.getHopperSystemManager().getItemsPremium().clear();

        HopperReplacement.INSTANCE.getHopperSystemManager().getItems().add(Material.NETHER_WART);
        HopperReplacement.INSTANCE.getHopperSystemManager().getItems().add(Material.DARK_OAK_LOG);
        HopperReplacement.INSTANCE.getHopperSystemManager().getItems().add(Material.ENDER_PEARL);
        HopperReplacement.INSTANCE.getHopperSystemManager().getItems().add(Material.COBBLESTONE);
        HopperReplacement.INSTANCE.getHopperSystemManager().getItems().add(Material.EMERALD);
        HopperReplacement.INSTANCE.getHopperSystemManager().getItems().add(Material.EMERALD_ORE);
        HopperReplacement.INSTANCE.getHopperSystemManager().getItems().add(Material.RAW_GOLD);
        HopperReplacement.INSTANCE.getHopperSystemManager().getItems().add(Material.RAW_IRON);
        HopperReplacement.INSTANCE.getHopperSystemManager().getItems().add(Material.COD);
        HopperReplacement.INSTANCE.getHopperSystemManager().getItems().add(Material.SALMON);
        HopperReplacement.INSTANCE.getHopperSystemManager().getItems().add(Material.PUFFERFISH);
        HopperReplacement.INSTANCE.getHopperSystemManager().getItems().add(Material.CARROT);
        HopperReplacement.INSTANCE.getHopperSystemManager().getItems().add(Material.TROPICAL_FISH);
        HopperReplacement.INSTANCE.getHopperSystemManager().getItems().add(Material.WHEAT);

        HopperReplacement.INSTANCE.getHopperSystemManager().getItems().remove(Material.ARROW);
        HopperReplacement.INSTANCE.getHopperSystemManager().getItems().remove(Material.ANCIENT_DEBRIS);
        HopperReplacement.INSTANCE.getHopperSystemManager().getItems().remove(Material.CHORUS_FRUIT);
        HopperReplacement.INSTANCE.getHopperSystemManager().getItems().remove(Material.COOKED_PORKCHOP);
        HopperReplacement.INSTANCE.getHopperSystemManager().getItems().remove(Material.COOKED_MUTTON);
    }

    public CustomInventory getGUI(Player player){
        CustomInventory inventory = new CustomInventory("&cDrop", 1, player);
        inventory.setAction(new GuiAction());
        SkyUser skyUser = SkyUserManager.getInstance().getUser(player.getName());

        int count = 0;
        for(Drop drop : this.dropList){
            inventory.setItem(count,
                    new ItemBuilder(drop.getItemStack().clone())
                    .setName(" &7Przedmiot:&f " + drop.getItemStack().getType())
                    .setLore(" &7Szansa:",
                             " &e VIP+:&f " + (drop.getChance() + 1.5) + "%",
                             " &6 VIP:&f " + (drop.getChance() + 1.0) + "%",
                             " &7 ze STONE:&f " + (drop.getChance() + 0.5) + "%",
                             " &7 ze COBBLESTONE:&f " + drop.getChance() + "%",
                             " ",
                             " " + (skyUser.getDisabledDrops().contains(drop) ? "&cWYLACZONY" : "&aWLACZONY")
                    )
                    .toItemStack());
            count++;
        }

        inventory.setItem(8, new ItemBuilder(Material.STONE)
                .setName("&7Drop COBBLESTONE/STONE:")
                .setLore(skyUser.isStoneDropDisabled() ? "&cWYLACZONY" : "&aWLACZONY")
                .toItemStack());


        return inventory;
    }

    public class GuiAction implements CustomInventoryAction{

        @Override
        public void handle(InventoryClickEvent inventoryClickEvent) {
            inventoryClickEvent.setCancelled(true);
            Player player = (Player) inventoryClickEvent.getWhoClicked();
            SkyUser skyUser = SkyUserManager.getInstance().getUser(player.getName());

            if(inventoryClickEvent.getSlot() == 8){
                skyUser.setStoneDropDisabled(!skyUser.isStoneDropDisabled());
                DropManager.getInstance().getGUI(player).display(player);
                return;
            }

            int count = 0;
            for(Drop drop : DropManager.this.dropList){
                if(inventoryClickEvent.getSlot() == count){
                    if(skyUser.getDisabledDrops().contains(drop))
                        skyUser.getDisabledDrops().remove(drop);
                    else skyUser.getDisabledDrops().add(drop);

                    DropManager.getInstance().getGUI(player).display(player);
                    return;
                }
                count++;
            }
        }

    }

}
