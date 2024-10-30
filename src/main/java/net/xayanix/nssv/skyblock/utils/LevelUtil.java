package net.xayanix.nssv.skyblock.utils;

import net.xayanix.nssv.skyblock.skyuser.SkyUser;
import net.xayanix.nssv.skyblock.skyuser.SkyUserManager;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;


public class LevelUtil {

	public static int getLevel(Player player){
		SkyUser skyUser = SkyUserManager.getInstance().getUser(player.getName());
		if(skyUser == null){
			return -1;
		}
		return skyUser.getBrokenBlocks() / 50;
	}

	public static String getPrefixByLevel(int level) {
		if (level >= 0 && level < 100) {
			return "&7[" + level + "✪]";
		} else if (level < 200) {
			return "&f[" + level + "✪]";
		} else if (level < 300) {
			return "&6[" + level + "✪]";
		} else if (level < 400) {
			return "&b[" + level + "✪]";
		} else if (level < 500) {
			return "&2[" + level + "✪]";
		} else if (level < 600) {
			return "&3[" + level + "✪]";
		} else if (level < 700) {
			return "&4[" + level + "✪]";
		} else if (level < 800) {
			return "&d[" + level + "✪]";
		} else if (level < 900) {
			return "&9[" + level + "✪]";
		} else {
			return "&5[" + level + "✪]";
		}
	}

}
