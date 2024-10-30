package net.xayanix.nssv.skyblock.skyuser.dummy;

import net.xayanix.nssv.core.utils.ChatUtil;
import net.xayanix.nssv.skyblock.islandutil.IslandUtil;
import net.xayanix.nssv.skyblock.skyuser.SkyUser;
import net.xayanix.nssv.skyblock.skyuser.SkyUserManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

public class Dummy {

	private static String name = "points";
	private final SkyUser user;

	public Dummy(SkyUser user){
		this.user = user;
		this.initialize();
	}

	@SuppressWarnings("deprecation")
	public void updateScore(SkyUser user){
		Scoreboard scoreboard = this.user.getScoreboard();
		Objective objective = scoreboard.getObjective(name);
		if(objective == null || !objective.getName().equals(name)) initialize();
		else {

		}
	}

	@SuppressWarnings("deprecation")
	private void initialize(){
		Scoreboard scoreboard = this.user.getScoreboard();
		Objective objective = scoreboard.getObjective(name);
		if(objective == null || !objective.getName().equals(name)){
			objective = scoreboard.registerNewObjective(name, "dummy");
			objective.setDisplaySlot(DisplaySlot.BELOW_NAME);
			objective.setDisplayName(ChatUtil.fixColors("&aLv"));
		}

		for(Player player : Bukkit.getOnlinePlayers()){
			SkyUser user = SkyUserManager.getInstance().getUser(player.getName());
		}
	}
}
