package com.ammaraskar.adminonly;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class AdminChatCommand implements CommandExecutor {
	Methods methods;
    public AdminChatCommand(AdminChat adminchat) {
    	this.methods = adminchat.methods;
    }
    
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission("adminchat.send") || sender.isOp()){
            if (args.length < 1) {
                sender.sendMessage(ChatColor.RED + "Usage: /a message");
                return false;
            }
        	String playername = sender.getName();
        	final String message = com.ammaraskar.adminonly.Methods.combineSplit(0, args, " ");
        	methods.MessageBuild(message, playername);
        }
        return true;
    }
}