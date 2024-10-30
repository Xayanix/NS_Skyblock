package net.xayanix.nssv.skyblock.commands;

import net.xayanix.nssv.core.utils.ChatUtil;
import net.xayanix.nssv.core.utils.StringUtil;
import net.xayanix.nssv.skyblock.basic.Main;
import net.xayanix.nssv.skyblock.casino.Casino;
import net.xayanix.nssv.skyblock.skyuser.SkyUser;
import net.xayanix.nssv.skyblock.skyuser.SkyUserManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.concurrent.TimeUnit;

/*
    created by: Xayanix at 2020-07-16 23:23
*/
public class CasinoCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
        if(arg3.length != 2){
            ChatUtil.sendMessage(arg0, "&8#&c Prawidlowe uzycie komendy:&7 /kasyno <gracz> <kwota>");
            return false;
        }

        SkyUser userarg0 = SkyUserManager.getInstance().getUser(arg0.getName());
        if(arg3[0].equalsIgnoreCase("akceptuj")){
            Player target = Bukkit.getPlayerExact(arg3[1]);

            if(target == null){
                ChatUtil.sendMessage(arg0, "&8#&c Gracz jest offline.");
                return true;
            }

            SkyUser userplayer = SkyUserManager.getInstance().getUser(target.getName());

            if(!userarg0.getCasinoInvite().equalsIgnoreCase(target.getName())){
                ChatUtil.sendMessage(arg0, "&8#&c Gracz nie zaprosil Cie do gry.");
                return true;
            }

            userarg0.setCasinoInvite("");
            Bukkit.getScheduler().runTaskAsynchronously(Main.getInstance(), () -> {
                Casino casino = new Casino(userarg0, userplayer, (Player) arg0, target, userarg0.getCasinoMoney());
                if(casino.takeMoney())
                    casino.play();
            });

            return true;
        }

        Player player = Bukkit.getPlayerExact(arg3[0]);
        if(player == null){
            ChatUtil.sendMessage(arg0, "&8#&c Gracz jest offline.");
            return false;
        }
        Player sender = (Player) arg0;

        SkyUser userplayer = SkyUserManager.getInstance().getUser(player.getName());


        if(sender.getWorld() != player.getWorld() || sender.getLocation().distance(player.getLocation()) > 10) {
            ChatUtil.sendMessage(arg0, "&8#&c Jestescie za daleko siebie!");
            return false;
        }

        if(!StringUtil.isInteger(arg3[1])){
            ChatUtil.sendMessage(arg0, "&8#&7 " + arg3[1] + "&c nie jest prawidlowa liczba.");
            return false;
        }

        int price = Integer.parseInt(arg3[1]);
        if(price <= 0){
            ChatUtil.sendMessage(arg0, "&8#&c Minimalna kwota gry to &e1$&c.");
            return false;
        }

        if(player == arg0){
            ChatUtil.sendMessage(arg0, "&8#&c Prawidlowe uzycie komendy:&7 /kasyno <gracz> <kwota>");
            return false;
        }

        /*if(userarg0.getCharacter().getMoney().getWallet() < price){
            ChatUtil.sendMessage(arg0, "&8#&c Nie posiadasz &e" + price + " zlotych&c na gre o ta kwote.");
            return false;
        }

        if(userplayer.getCharacter().getMoney().getWallet() < price){
            ChatUtil.sendMessage(arg0, "&8#&c Gracz &e" + userplayer.getName() + "&c nie posiada &e" + price + " zlotych&c na gre o ta kwote.");
            return false;
        }*/

        if(!userarg0.isCooldownFinished("kasyno", TimeUnit.SECONDS.toMillis(15))){
            ChatUtil.sendMessage(arg0, "&8#&a Oferty w kasynie mozesz skladac raz na 15 sekund.");
            return false;
        }


        ChatUtil.sendMessage(player, "&8#&e " + userarg0.getName() + "&a zaprasza Cie do gry w kasynie o &e" + price + "$&a.");
        ChatUtil.sendMessage(player, "&8#&a Wpisz &e/kasyno akceptuj " + arg0.getName() + "&a, aby zaakceptowac.");
        ChatUtil.sendMessage(arg0, "&8#&a Wyslales &e" + userplayer.getName() + "&a zaproszenie do gry o &e" + price + "$&a.");

        userplayer.setCasinoInvite(arg0.getName());
        userplayer.setCasinoMoney(price);

        return true;
    }

}
