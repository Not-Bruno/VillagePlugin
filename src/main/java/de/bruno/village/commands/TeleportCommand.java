package de.bruno.village.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.bruno.village.util.Color;

public class TeleportCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			if (args.length==1) {
				Player player = (Player) sender;
				Player target = Bukkit.getPlayer(args[1]);
				
				player.teleport(target);
				player.sendMessage(Color.GREEN+"Du wurdest zu "+Color.ORANGE+target.getName()+Color.GREEN+" Teleportiert");
				
			} else sender.sendMessage(Color.RED+"Bitte verwende "+Color.ORANGE+"/tp <Spieler>");
		} else sender.sendMessage(Color.RED+"You are not Permittet!");
		return false;
	}
	
}
