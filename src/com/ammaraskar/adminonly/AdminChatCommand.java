package com.ammaraskar.adminonly;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChatEvent;

public class AdminChatCommand implements CommandExecutor {
    
	Methods methods;
	AdminChat plugin;
	
    public AdminChatCommand(AdminChat adminchat) {
        this.plugin = adminchat;
    	this.methods = adminchat.methods;
    }
    
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission("adminchat.send") || sender.isOp()){
            if (args.length < 1) {
                sender.sendMessage(ChatColor.RED + "Usage: /a message");
                return true;
            }
        	String playername = sender.getName();
            final String message = Methods.combineSplit(0, args, " ");
            
        	if (plugin.listOfTogglePlayers.contains(playername)) {
        	    Player player;
        	    if(sender instanceof Player) {
        	        player = (Player) sender;
        	    } else {
        	        sender.sendMessage(ChatColor.RED + "You aren't supposed to be here!");
        	        return true;
        	    }
                PlayerChatEvent event = new PlayerChatEvent(player, message);
                plugin.getServer().getPluginManager().callEvent(event);

                String s = String.format(event.getFormat(), event.getPlayer().getDisplayName(), event.getMessage());
                Bukkit.getConsoleSender().sendMessage(s);
                for (Player recipient : event.getRecipients()) {
                    recipient.sendMessage(s);
                }
                return true;
        	}
        	
        	methods.MessageBuild(message, playername);
        }
        return true;
    }
}