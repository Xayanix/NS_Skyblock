package net.xayanix.nssv.skyblock.casino;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.xayanix.nssv.core.utils.ChatUtil;
import net.xayanix.nssv.core.utils.RandomUtil;
import net.xayanix.nssv.skyblock.economy.EconomyManager;
import net.xayanix.nssv.skyblock.skyuser.SkyUser;
import org.bukkit.entity.Player;

@AllArgsConstructor
@Getter
public class Casino {

    private SkyUser user1;
    private SkyUser user2;
    private Player player1;
    private Player player2;
    private int money;

    public boolean takeMoney(){
        if(player1 == null || player2 == null)
            return false;

        if(EconomyManager.getInstance().getEconomy().getBalance(player1) < money){
            this.broadcast("&8#&c " + user1.getName() + "&e nie ma " + money + "$ na gre.");
            return false;
        }

        if(EconomyManager.getInstance().getEconomy().getBalance(player2)  < money){
            this.broadcast("&8#&c " + user2.getName() + "&e nie ma " + money + "$ na gre.");
            return false;
        }

        EconomyManager.getInstance().getEconomy().withdrawPlayer(player1, money);
        EconomyManager.getInstance().getEconomy().withdrawPlayer(player2, money);

        this.broadcast("&8#&c Z twojego portfela pobrano &e" + money + "$&c na gre w kasynie.");

        return true;
    }

    public void play(){

        int user1kostka = 0;
        int user2kostka = 0;
        int rzut = RandomUtil.getRandomNumber(6);

        try {
            Thread.sleep(1000);
            this.broadcast("&8#&e " + user1.getName() + "&a rzuca kostka i wypada liczba: &e" + rzut);
            user1kostka+= rzut;
            rzut = RandomUtil.getRandomNumber(6);

            Thread.sleep(2500);
            this.broadcast("&8#&e " + user2.getName() + "&a rzuca kostka i wypada liczba: &e" + rzut);
            user2kostka+= rzut;
            rzut = RandomUtil.getRandomNumber(6);

            Thread.sleep(2500);
            this.broadcast("&8#&e " + user1.getName() + "&a rzuca kostka i wypada liczba: &e" + rzut);
            user1kostka+= rzut;
            rzut = RandomUtil.getRandomNumber(6);

            Thread.sleep(2500);
            this.broadcast("&8#&e " + user2.getName() + "&a rzuca kostka i wypada liczba: &e" + rzut);
            user2kostka+= rzut;

            int reward = this.money*2;

            if(user1kostka == user2kostka){
                this.broadcast("&8#&c REMIS! Za 3 sekundy rzucacie jeszcze raz!");
                Thread.sleep(3000);
                this.play();
                return;
            } else if(user1kostka > user2kostka) {
                this.broadcast("&8#&e " + user1.getName() + "&a wygrywa z wynikiem &e" + user1kostka + ":" + user2kostka + "&a i otrzymuje &e" + reward + "$&a.");
                EconomyManager.getInstance().getEconomy().depositPlayer(player1, reward);
            } else {
                this.broadcast("&8#&e " + user2.getName() + "&a wygrywa z wynikiem &e" + user2kostka + ":" + user1kostka + "&a i otrzymuje &e" + reward + "$&a.");
                EconomyManager.getInstance().getEconomy().depositPlayer(player2, reward);
            }


        } catch (InterruptedException e) {

        }

    }

    public void broadcast(String string){
        string = ChatUtil.fixColors(string);
        player1.sendMessage(string);
        player2.sendMessage(string);
    }

}
