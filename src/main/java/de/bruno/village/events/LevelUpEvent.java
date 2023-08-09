package de.bruno.village.events;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLevelChangeEvent;

import de.bruno.village.util.Color;

public class LevelUpEvent implements Listener {
	@EventHandler
	public void onPlayerLevelUp(PlayerLevelChangeEvent event) {
		if (event.getOldLevel() < event.getNewLevel())
			return;
		
		Bukkit.broadcastMessage(Color.ORANGE_BOLD + event.getPlayer().getName() + Color.R_GREEN + " hat Level "
				+ Color.PURPLE_BOLD + event.getNewLevel() + Color.R_GREEN + " erreicht.");

	}
}
