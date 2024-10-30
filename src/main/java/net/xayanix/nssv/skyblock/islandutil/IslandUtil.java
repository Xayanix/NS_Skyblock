package net.xayanix.nssv.skyblock.islandutil;


import com.bgsoftware.superiorskyblock.api.SuperiorSkyblockAPI;
import com.bgsoftware.superiorskyblock.api.island.Island;
import org.bukkit.entity.Player;


/*
    created by: Xayanix at 2021-01-22 00:16
*/
public class IslandUtil {

	public static int getIslandLevel(Player player) {
		Island island = SuperiorSkyblockAPI.getPlayer(player).getIsland();
        if (island == null) {
            return 1;
        }

		return island.getIslandLevel().intValue();
	}

	public static String getFormattedIslandLevel(Player player) {
		int islandLevel = getIslandLevel(player);

		// Sprawdź, czy poziom wyspy jest większy niż 1000
		if (islandLevel >= 1000) {
			// Jeśli tak, oblicz poziom w tysiącach i dodaj "k" na końcu
			double levelInK = islandLevel / 1000.0;
			return String.format("%.1fk", levelInK);
		} else {
			// Jeśli nie, zwróć poziom bez zmian
			return String.valueOf(islandLevel);
		}
	}


}
