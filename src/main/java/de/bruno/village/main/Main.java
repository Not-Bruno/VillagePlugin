package de.bruno.village.main;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import de.bruno.village.commands.UtilityCommand;
import de.bruno.village.events.JoinEvent;

public class Main extends JavaPlugin{
	public static Main plugin;	
	
	public void onEnable() {
		plugin = this;
		FileConfiguration config = Main.getPlugin().getConfig();
		
		UpdateConfig(config);
		
		// Commands
		getCommand("cdel").setExecutor(new UtilityCommand());
		
		// Events
		PluginManager pmanager = Bukkit.getPluginManager();
		pmanager.registerEvents(new JoinEvent(), this);
		
		System.out.println("Plugin is alive");
	}
	
	
	public static Main getPlugin() {
		return plugin;
	}
	
	private static void UpdateConfig(FileConfiguration config) {
		if (config.getList("PlayerList")!=null) {
			List<String> playerList = new ArrayList<String>();
			config.set("PlayerList", playerList);
			Main.getPlugin().saveConfig();
		}
	}
	
}
