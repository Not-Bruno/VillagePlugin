package de.bruno.village.commands;

import java.util.HashMap;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import de.bruno.village.main.Main;

public class InventoryRecoverCommand implements CommandExecutor {
	
	HashMap<String, ItemStack[]> inventory = Main.getPlugin().getInventory();
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			FileConfiguration config = Main.getPlugin().getConfig();			
			String playerUID = config.getString("Player."+player.getUniqueId()+".UUID");
			
			if (args.length==0) {
				if (config.getBoolean("Player."+player.getUniqueId()+".hasRecover") || config.get("Player."+player.getUniqueId()+".hasRecover")!=null) {
					player.getInventory().setContents(inventory.get(playerUID));
					inventory.remove(playerUID);
					Main.getPlugin().setInventory(inventory);
				}
			}
			
		}
		return false;
	}

}
