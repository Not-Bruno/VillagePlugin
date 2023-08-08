package de.bruno.village.events;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import de.bruno.village.main.Main;
import de.bruno.village.util.Color;

public class Backpack implements CommandExecutor, Listener {

	private final String TITLE = Color.CYAN + "Backpack";

	private HashMap<String, ItemStack[]> inventory = Main.getPlugin().getInventory();

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			FileConfiguration config = Main.getPlugin().getConfig();
			Player player = (Player) sender;
			String UUID = config.getString("Player." + player.getUniqueId());

			Inventory inv = Bukkit.createInventory(null, 9 * 3, TITLE);
			if (inventory.get(UUID) != null) {
				inv.setContents(inventory.get(UUID));
			} else {
				player.openInventory(inv);
			}
		}
		return false;
	}
	
	@EventHandler
	public void onBackpackClose(InventoryCloseEvent event) {

		FileConfiguration config = Main.getPlugin().getConfig();
		Inventory inv = event.getInventory();
		String UUID = config.getString("Player." + event.getPlayer().getUniqueId());
		
		if (inv.getHolder() != event.getPlayer()) {
			inventory.put(UUID, inv.getContents());
			Main.getPlugin().setInventory(inventory);
		}
	}
}
