package net.xayanix.nssv.skyblock.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;

/*
    created by: Xayanix at 2020-07-16 23:23
*/
public class TpposCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
        if(!arg0.hasPermission("admin"))
            return false;

        if(arg3.length > 0 && arg3[0].equalsIgnoreCase("itemsground")) {
            Player player = (Player) arg0;
            player.getWorld().getEntities().stream().filter(entity -> entity.getType() == EntityType.DROPPED_ITEM).forEach(drop -> {
                Item item = (Item) drop;
                System.out.println(item.getItemStack().getType() + " " + " at " + item.getLocation().getBlockX() + " " + item.getLocation().getBlockY() + " " + item.getLocation().getBlockZ());
            });
        }

        String world = arg3[0];
        int x = Integer.parseInt(arg3[1]);
        int y = Integer.parseInt(arg3[2]);
        int z = Integer.parseInt(arg3[3]);


        return true;
    }

}
