package net.xayanix.nssv.skyblock.placeholder;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.xayanix.nssv.skyblock.basic.Main;
import net.xayanix.nssv.skyblock.skyuser.SkyUser;
import net.xayanix.nssv.skyblock.skyuser.SkyUserManager;
import org.bukkit.OfflinePlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;


/*
    created by: Xayanix at 2021-12-05 01:52
*/
public class StatsHolder extends PlaceholderExpansion implements Listener {

    private Main plugin = Main.getInstance();

    @Override
    public String getIdentifier() {
        return "sb";
    }

    @Override
    public String getAuthor() {
        return "YourName";
    }

    @Override
    public String getVersion() {
        return "1.0";
    }

    @Override
    public boolean canRegister() {
        return true;
    }

    @Override
    public String onRequest(OfflinePlayer player, String placeholder) {
        if(placeholder.startsWith("broken_blocks")){
            SkyUser skyUser = SkyUserManager.getInstance().getUser(player.getName());
            if(skyUser == null){
                return "-1";
            }

            return skyUser.getBrokenBlocks() + "";
        }

        return null; // Return null if the placeholder doesn't match
    }


}
