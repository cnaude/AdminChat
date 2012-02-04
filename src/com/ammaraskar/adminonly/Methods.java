package com.ammaraskar.adminonly;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Methods{
	
	AdminChat adminchat;
	
    public Methods(AdminChat adminChat) {
		this.adminchat = adminChat;
	}

	public static String combineSplit(int startIndex, String[] string, String seperator) {
        final StringBuilder builder = new StringBuilder();
        for (int i = startIndex; i < string.length; i++) {
            builder.append(string[i]);
            builder.append(seperator);
        }
        builder.deleteCharAt(builder.length() - seperator.length());
        return builder.toString();
    }

	public void MessageBuild(String message, String playername) {
		final String msg = ChatColor.RED + "[AdminChat] " + ChatColor.WHITE + "<" + ChatColor.LIGHT_PURPLE + playername + ChatColor.WHITE + "> " + message;
		sendMessage(msg);
		adminchat.getLogger().info("<" + playername + ">" + " " + ChatColor.stripColor(message));
	}

	public static void sendMessage(String msg) {	
        for (final Player plr : Bukkit.getServer().getOnlinePlayers()) {
            if (plr.hasPermission("adminchat.recieve") || plr.isOp()) {
                plr.sendMessage(msg);
            }	
        }
	}

}