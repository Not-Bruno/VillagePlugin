package de.bruno.village.events;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import de.bruno.village.main.Main;

public class JoinEvent implements Listener{
	@EventHandler
	public static void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		FileConfiguration config = Main.getPlugin().getConfig();
		
		if (config.getList("PlayerList")!=null) {
			if (config.getList("PlayerList").contains(""+player.getUniqueId())) {
				UpdatePlayerProfile(player, event, config);
				System.out.println("PlayerList contains Player");
			}
		} else {
			CreatePlayerProfile(player, event, config);
			UpdateConfigPlayers(config, player);
		}	
	}
	
	
	private static void CreatePlayerProfile(Player player, PlayerJoinEvent event, FileConfiguration config) {				
		config.set("Player."+player.getUniqueId()+".UUID", ""+player.getUniqueId());
		config.set("Player."+player.getUniqueId()+".Name", player.getName());
		config.set("Player."+player.getUniqueId()+".Level", player.getLevel());
		config.set("Player."+player.getUniqueId()+".isMutet", false);
		Main.getPlugin().saveConfig();		
		System.out.println("Creating Profile");
	}
	
	private static void UpdatePlayerProfile(Player player, PlayerJoinEvent event, FileConfiguration config) {
		System.out.println("Update Profile");
	}
	
	private static void UpdateConfigPlayers(FileConfiguration config ,Player player) {
		config.set("PlayerList", player);
		Main.getPlugin().saveConfig();
	}
	
}
