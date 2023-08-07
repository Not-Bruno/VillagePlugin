package de.bruno.village.events;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import de.bruno.village.main.Main;
import de.bruno.village.util.Color;

public class JoinEvent implements Listener{
	@EventHandler
	public static void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		FileConfiguration config = Main.getPlugin().getConfig();
		
		if (config.get("Player."+player.getUniqueId())==null) {
			SetPlayerConfig(config, player);
			event.setJoinMessage(Color.GREEN+"Hallo "+Color.ORANGE_BOLD+player.getName());
		} else {
			UpdatePlayerConfig(config, player);
			event.setJoinMessage(Color.GREEN+"Wilkommen zurück "+Color.ORANGE_BOLD+player.getName());
		}
	}
	
	private static void SetPlayerConfig(FileConfiguration config, Player player) {
		config.set("Player."+player.getUniqueId(), player.getUniqueId());
		config.set("Player."+player.getUniqueId()+".level", player.getLevel());
		config.set("Player."+player.getUniqueId()+".hasHome", false);
		Main.getPlugin().saveConfig();
	}
	
	private static void UpdatePlayerConfig(FileConfiguration config, Player player) {
		config.set("Player."+player.getUniqueId()+".level", player.getLevel());
		Main.getPlugin().saveConfig();
	}
	
}
