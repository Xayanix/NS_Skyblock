package net.xayanix.nssv.skyblock.economy;

import lombok.Getter;
import net.milkbowl.vault.economy.Economy;
import net.xayanix.nssv.core.basic.Main;
import org.bukkit.plugin.RegisteredServiceProvider;

public class EconomyManager {

    @Getter
    private Economy economy;
    @Getter
    private static EconomyManager instance;

    public EconomyManager(){
        instance = this;

        RegisteredServiceProvider<Economy> economyProvider = Main.getInstance().getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economyProvider != null)
            economy = economyProvider.getProvider();


    }


}
