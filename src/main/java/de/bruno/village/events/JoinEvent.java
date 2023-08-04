package de.bruno.village.events;

import java.util.ArrayList;
import java.util.UUID;

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
		
		@SuppressWarnings("unchecked")
		ArrayList<String> playerList = new ArrayList<String>();
		for (Object ids : config.getList("Main.Players")) {
			System.out.println(ids);
		}
		
		if (!playerList.contains(player.getUniqueId())) 
			CreatePlayerProfile(player, event, config, playerList);
		else UpdatePlayerProfile(player, event, config);
		
	}
	
	
	private static void CreatePlayerProfile(Player player, PlayerJoinEvent event, FileConfiguration config, ArrayList<String> playerList) {				
		config.set("Player"+player.getUniqueId()+".UUID", player.getUniqueId());
		config.set("Player"+player.getUniqueId()+".Name", player.getName());
		config.set("Player"+player.getUniqueId()+".Level", player.getLevel());
		config.set("Player"+player.getUniqueId()+".isMutet", false);
		Main.getPlugin().saveConfig();
		
		playerList.add(config.getString("Player"+player.getUniqueId()+".UUID"));
		config.set("Main.Players", playerList);
		
		System.out.println("Creating Profile");
	}
	
	private static void UpdatePlayerProfile(Player player, PlayerJoinEvent event, FileConfiguration config) {
		System.out.println("Update Profile");
	}
	
}
