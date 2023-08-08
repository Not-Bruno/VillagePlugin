package de.bruno.village.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import de.bruno.village.main.Main;
import de.bruno.village.util.Color;

public class SetHomeCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			FileConfiguration config = Main.getPlugin().getConfig();
			
			if (args.length==0) {
				config.set("Player." + player.getUniqueId() + ".World", player.getWorld().getName());
				config.set("Player." + player.getUniqueId() + ".X", player.getLocation().getX());
				config.set("Player." + player.getUniqueId() + ".Y", player.getLocation().getY());
				config.set("Player." + player.getUniqueId() + ".Z", player.getLocation().getZ());
				config.set("Player." + player.getUniqueId() + ".Yaw", player.getLocation().getYaw());
				config.set("Player." + player.getUniqueId() + ".Pitch", player.getLocation().getPitch());
				config.set("Player." + player.getUniqueId() + ".hasHome", true);
				Main.getPlugin().saveConfig();
			} else {
				player.sendMessage(Color.RED + "Bitte verwende " + Color.ORANGE + "/sethome " + Color.RED + "!");
			}
			
		}
		return false;
	}

}
