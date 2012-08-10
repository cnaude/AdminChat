package com.ammaraskar.adminonly;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AdminChatCommand implements CommandExecutor {

    Methods methods;
    AdminChat plugin;

    public AdminChatCommand(AdminChat adminchat) {
        this.plugin = adminchat;
        this.methods = adminchat.methods;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission("adminchat.send") || sender.isOp()) {
            if (args.length < 1) {
                sender.sendMessage(ChatColor.RED + "Usage: /a message");
                return true;
            }
            String playername = sender.getName();
            final String message = Methods.combineSplit(0, args, " ");

            if (plugin.toggledPlayers.contains(playername)) {
                Player player;
                if (sender instanceof Player) {
                    player = (Player) sender;
                } else {
                    sender.sendMessage(ChatColor.RED + "You aren't supposed to be here!");
                    return true;
                }
                player.chat(message);
                return true;
            }

            methods.MessageBuild(message, playername);
        }
        return true;
    }
}