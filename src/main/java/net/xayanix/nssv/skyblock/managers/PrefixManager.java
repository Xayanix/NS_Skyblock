package net.xayanix.nssv.skyblock.managers;

import net.xayanix.nssv.core.objects.User;
import net.xayanix.nssv.core.objects.UserConfiguration;
import net.xayanix.nssv.core.utils.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class PrefixManager {

	public static void setPrefix(Player player, String suffix){
		Scoreboard sb = Bukkit.getScoreboardManager().getMainScoreboard();
	    Team team = sb.getPlayerTeam(player);
	    if (team == null) {
	      team = sb.getTeam(player.getName());
	      if (team == null) team = sb.registerNewTeam(player.getName());
	      team.addPlayer(player);
	    }

	    String prefix = "&f";
		UserConfiguration userConfiguration = User.getUser(player.getName()).getUserConfiguration();
	    if(userConfiguration != null && !userConfiguration.getNicknameColor().equalsIgnoreCase(""))
	    	prefix = userConfiguration.getNicknameColor();
	    else if(player.hasPermission("vip"))
	    	prefix = pexPrefix(player).substring(0, 2);

	    team.setPrefix(ChatUtil.fixColors(prefix));
	    team.setSuffix(ChatUtil.fixColors(suffix));
	    player.setScoreboard(sb);
	}
	
	public static String pexPrefix(Player player){
		return ChatUtil.fixColors(PermissionsEx.getUser(player).getPrefix());
	}
}
