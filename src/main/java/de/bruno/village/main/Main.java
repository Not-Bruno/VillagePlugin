package de.bruno.village.main;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import de.bruno.village.commands.HomeCommand;
import de.bruno.village.commands.SetHomeCommand;
import de.bruno.village.events.Backpack;
import de.bruno.village.events.JoinEvent;

public class Main extends JavaPlugin {
	public static Main plugin;
	
	HashMap<String, ItemStack[]> inventory = new HashMap<String, ItemStack[]>();
	
	public void onEnable() {
		plugin = this;
		
		Backpack backpack = new Backpack();
		
		// Commands
		getCommand("home").setExecutor(new HomeCommand());
		getCommand("sethome").setExecutor(new SetHomeCommand());
		getCommand("bp").setExecutor(backpack);

		// Events
		PluginManager pmanager = Bukkit.getPluginManager();
		pmanager.registerEvents(new JoinEvent(), this);
		pmanager.registerEvents(backpack, this);

		System.out.println("[LOG] Plugin VillagePlugin successfully loaded!");
	}

	
	public void onDisable() {
		System.out.println("[LOG] Plugin VillagePlugin unloaded!");
	}

	public static Main getPlugin() {
		return plugin;
	}

	public HashMap<String, ItemStack[]> getInventory() {
		return inventory;
	}

	public void setInventory(HashMap<String, ItemStack[]> inventory) {
		this.inventory = inventory;
	}
	
}
