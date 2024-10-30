package net.xayanix.nssv.skyblock.tasks;

import lombok.Getter;
import net.xayanix.nssv.skyblock.basic.Main;
import net.xayanix.nssv.skyblock.economy.EconomyManager;
import net.xayanix.nssv.skyblock.skyuser.SkyUser;
import net.xayanix.nssv.skyblock.skyuser.SkyUserManager;
import org.bukkit.Bukkit;

public class SaveTask implements Runnable {

    @Getter
    private static SaveTask instance;

    public SaveTask() {
        instance = this;
        Bukkit.getScheduler().runTaskTimerAsynchronously(Main.getInstance(), this, 20 * 60, 20 * 60);
    }

    @Override
    public void run() {
        Bukkit.getOnlinePlayers().forEach(p -> {
            SkyUser skyUser = SkyUserManager.getInstance().getUser(p.getName());
            skyUser.synchronize();

            if(skyUser.getMoney() > 0) {
                EconomyManager.getInstance().getEconomy().depositPlayer(p, skyUser.getMoney());
                skyUser.setMoney(0);
            }
        });

    }
}
