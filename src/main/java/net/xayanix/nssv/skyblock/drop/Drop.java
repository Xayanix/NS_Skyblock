package net.xayanix.nssv.skyblock.drop;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

@AllArgsConstructor
@Getter
public class Drop {

    private final ItemStack itemStack;
    private double chance;

}
