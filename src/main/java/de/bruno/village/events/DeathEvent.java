package de.bruno.village.events;

import java.util.HashMap;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import de.bruno.village.main.Main;
import de.bruno.village.util.Color;

public class DeathEvent implements Listener {

	static HashMap<String, ItemStack[]> inventory = Main.getPlugin().getInventory();

	@EventHandler
	public static void onPlayerDeath(PlayerDeathEvent event) {
		Player player = event.getEntity().getPlayer();
		FileConfiguration config = Main.getPlugin().getConfig();
		String playerUID = config.getString("Player." + player.getUniqueId());

		inventory.put(playerUID, player.getInventory().getContents());
		player.sendMessage(Color.YELLOW + "Dein Inventar wurde gespeichert. Mit " + Color.ORANGE_BOLD + "/recover "
				+ Color.YELLOW + "bekommst du es zurück!");
		
		config.set("Player."+player.getUniqueId()+".hasRecover", true);
		Main.getPlugin().setInventory(inventory);
		Main.getPlugin().saveConfig();
	}
}
