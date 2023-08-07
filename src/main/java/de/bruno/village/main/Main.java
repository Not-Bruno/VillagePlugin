package de.bruno.village.main;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import de.bruno.village.commands.HomeCommand;
import de.bruno.village.events.JoinEvent;

public class Main extends JavaPlugin{
	public static Main plugin;	
	
	public void onEnable() {
		plugin = this;
		
		// Commands
		getCommand("home").setExecutor(new HomeCommand());
		
		// Events
		PluginManager pmanager = Bukkit.getPluginManager();
		pmanager.registerEvents(new JoinEvent(), this);
		
		System.out.println("Plugin VillagePlugin successfully loaded!");
	}
	
	
	public void onDisable() {
		System.out.println("Plugin VillagePlugin unloaded!");
		getPlugin().saveConfig();
	}
	
	public static Main getPlugin() {
		return plugin;
	}
	
}
