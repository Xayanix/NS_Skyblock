package net.xayanix.nssv.skyblock.tasks;

import net.xayanix.nssv.core.basic.Main;
import net.xayanix.nssv.skyblock.skyuser.dummy.DummyManager;
import org.bukkit.Bukkit;

public class DummyTask implements Runnable{

	@Override
	public void run() {
		try { DummyManager.updatePlayers(); }
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public void start(){
		Bukkit.getScheduler().runTaskTimerAsynchronously(Main.getInstance(), this, 20 * 5, 5 * 20);
	}

}
