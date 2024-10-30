package net.xayanix.nssv.skyblock.tab;

import lombok.Getter;

import java.util.UUID;

public class TabMoney {

    @Getter
    private UUID name;

    @Getter
    private long money;

    public TabMoney(UUID name, long money){
        this.name = name;
        this.money = money;
    }

}
