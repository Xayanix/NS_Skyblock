package net.xayanix.nssv.skyblock.listeners.player;

import com.bgsoftware.superiorskyblock.api.SuperiorSkyblockAPI;
import com.bgsoftware.superiorskyblock.api.island.Island;
import net.xayanix.nssv.core.utils.ChatUtil;
import net.xayanix.nssv.skyblock.basic.SChatUtil;
import net.xayanix.nssv.skyblock.islandutil.IslandUtil;
import net.xayanix.nssv.skyblock.managers.PrefixManager;
import net.xayanix.nssv.skyblock.utils.LevelUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import ru.tehkode.permissions.bukkit.PermissionsEx;


public class PlayerChatListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();

        if (message.startsWith("!")) {
            message = message.replaceFirst("!", "");
            if (message.length() == 0) {
                event.setCancelled(true);
                return;
            }

            Island island = SuperiorSkyblockAPI.getIslandAt(player.getLocation());
            if (island == null) {
                island = SuperiorSkyblockAPI.getPlayer(player).getIsland();
                if (island == null) {
                    ChatUtil.sendMessage(player, "&8#&c Nie masz swojej wyspy.");
                    event.setCancelled(true);
                    return;
                }

            }

            SChatUtil.sendMessage(island, "&8(&aCzat wyspy&8)&7 " + player.getName() + "&8 »&a " + message);
            event.setCancelled(true);
            return;
        }

        message = "%2$s";

        String color = ChatUtil.fixColors(PermissionsEx.getUser(player).getSuffix());

        if (!player.hasPermission("kidmod")) {
            event.setFormat(ChatUtil.fixColors("&a" + IslandUtil.getFormattedIslandLevel(player) + " Lv &8| " + LevelUtil.getPrefixByLevel(LevelUtil.getLevel(player)) + " &8 × ") + PrefixManager.pexPrefix(player) + "{KOLORNICKU_RGB}" + ChatUtil.fixColors("&8 » &7" + color) + message);
        } else
            event.setFormat(ChatUtil.fixColors(" \n" + PrefixManager.pexPrefix(player) + "{KOLORNICKU_RGB}" + "&8 » &7" + color + message + "\n "));
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent e) {
        Player player = e.getPlayer();
        if (player.getAllowFlight())
            return;

        if (player.hasPermission("helper"))
            return;

        if (player.getFallDistance() > 2) {
            ChatUtil.sendMessage(player, "&8#&c Nie mozesz uzywac komend spadajac.");
            e.setCancelled(true);
        }

    }

}
