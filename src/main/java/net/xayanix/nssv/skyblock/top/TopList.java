package net.xayanix.nssv.skyblock.top;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import lombok.RequiredArgsConstructor;
import net.xayanix.nssv.core.utils.ChatUtil;
import net.xayanix.nssv.skyblock.basic.Main;
import net.xayanix.nssv.skyblock.tab.TopManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.Map;
import java.util.UUID;

/*
    created by: Xayanix at 2020-09-06 00:57
*/

@RequiredArgsConstructor
public class TopList {

    private final Location location;
    private final String text;
    private Hologram hologram;

    public TopList register() {
        this.hologram = HologramsAPI.createHologram(Main.getInstance(), location);
        Bukkit.getScheduler().runTaskTimer(Main.getInstance(), this::setHolo, 20 * 10, 20 * 60);
        return this;
    }


    public void setHolo() {
        this.hologram.clearLines();
        this.hologram.appendTextLine(ChatUtil.fixColors(text));


        /*int c = 1;
        for (final Map.Entry<UUID, Long> en : TopManager.getTop().entrySet()) {
            this.hologram.appendTextLine(ChatUtil.fixColors("&e" + c + ". &7" + "kutas" + " &b(" + en.getValue() + ")"));
            ++c;
            if (c > 15)
                break;
        }*/
    }


}

