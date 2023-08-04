package de.bruno.village.main;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import de.bruno.village.commands.UtilityCommand;
import de.bruno.village.events.JoinEvent;

public class Main extends JavaPlugin{
	public static Main plugin;	
	
	public void onEnable() {
		System.out.println("Plugin is alive");
		plugin = this;
	
		// Commands
		getCommand("list").setExecutor(new UtilityCommand());
		
		// Events
		PluginManager pmanager = Bukkit.getPluginManager();
		pmanager.registerEvents(new JoinEvent(), this);
	
	}
	
	
	public static Main getPlugin() {
		return plugin;
	}
	
}
