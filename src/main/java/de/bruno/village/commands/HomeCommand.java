package de.bruno.village.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import de.bruno.village.main.Main;
import de.bruno.village.util.Color;

public class HomeCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			FileConfiguration config = Main.getPlugin().getConfig();
			Player player = (Player) sender;
			
				// /home
			if (args.length == 0) {
				if (config.getBoolean("Player." + player.getUniqueId() + ".hasHome")) {
					World world = Bukkit.getWorld(config.getString("Player." + player.getUniqueId() + ".World"));
					double x = config.getDouble("Player." + player.getUniqueId() + ".X");
					double y = config.getDouble("Player." + player.getUniqueId() + ".Y");
					double z = config.getDouble("Player." + player.getUniqueId() + ".Z");
					float yaw = (float) config.getDouble("Player." + player.getUniqueId() + ".Yaw");
					float pitch = (float) config.getDouble("Player." + player.getUniqueId() + ".Pitch");

					Location location = new Location(world, x, y, z, yaw, pitch);
					player.teleport(location);
					player.sendMessage(Color.GREEN + "Wilkommen zuhause");

				} else
					player.sendMessage(Color.RED + "Du hast noch kein zuhause gesetzt!");
			} else 
				player.sendMessage(Color.RED + "Bitte verwende " + Color.ORANGE + "/home" + Color.RED + "!");
		} else
			sender.sendMessage(Color.RED + "Your are not Permittet!");
		return false;
	}
}
