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

				// /home set && /home del
			} else if (args.length == 1) {
				if (args[1].equalsIgnoreCase("set")) {
					config.set("Player." + player.getUniqueId() + ".World", player.getWorld().getName());
					config.set("Player." + player.getUniqueId() + ".X", player.getLocation().getX());
					config.set("Player." + player.getUniqueId() + ".Y", player.getLocation().getY());
					config.set("Player." + player.getUniqueId() + ".Z", player.getLocation().getZ());
					config.set("Player." + player.getUniqueId() + ".Yaw", player.getLocation().getYaw());
					config.set("Player." + player.getUniqueId() + ".Pitch", player.getLocation().getPitch());
					config.set("Player." + player.getUniqueId() + ".hasHome", true);
					Main.getPlugin().saveConfig();

				} else if (args[1].equalsIgnoreCase("del") || args[1].equalsIgnoreCase("remove")) {
					if (config.getBoolean("Player." + player.getUniqueId() + ".hasHome")) {
						config.set("Player." + player.getUniqueId() + ".hasHome", false);
					} else
						player.sendMessage(Color.RED + "Du hast noch kein zuhause gesetzt!");
				}

			} else
				player.sendMessage(Color.RED + "Bitte verwende " + Color.ORANGE + "/home <set> " + Color.RED + "oder "
						+ Color.ORANGE + "/home <del||remove>");
		} else
			sender.sendMessage(Color.RED + "Your are not Permittet!");
		return false;
	}
}
