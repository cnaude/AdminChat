package com.ammaraskar.adminonly;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class AdminChatToggleCommand implements CommandExecutor {
    AdminChat adminchat;

    public AdminChatToggleCommand(AdminChat adminchat) {
        this.adminchat = adminchat;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission("adminchat.toggle") || sender.isOp()) {
            if (adminchat.toggledPlayers.contains(sender.getName())) {
                adminchat.toggledPlayers.remove(sender.getName());
                sender.sendMessage(ChatColor.RED + "Detoggled Admin Chat");
            } else {
                adminchat.toggledPlayers.add(sender.getName());
                sender.sendMessage(ChatColor.RED + "Toggled Admin Chat");
            }
            return true;
        }
        return true;
    }
}
