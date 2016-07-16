package com.ammaraskar.adminonly;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Methods {

    AdminChat adminchat;
    final String PERM = "adminchat.receive";

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
   
    public void SendFormattedMessage(String rawMessage, String name, String world) {
        String formattedMessage = adminchat.format.replace("%playername", name).replace("%message", rawMessage);
        if (adminchat.sendChatToConsole) {
            adminchat.getServer().broadcast(formattedMessage, PERM);
            adminchat.getLogger().info(ChatColor.stripColor(formattedMessage));
        } else {
            for (Player player : adminchat.getServer().getOnlinePlayers()) {
                if (player.hasPermission(PERM)) {
                    player.sendMessage(formattedMessage);
                }
            }
        }
        adminchat.getServer().getPluginManager().callEvent(new AdminChatEvent(rawMessage, name, world));
    }
    
    public void SendRawMessage(String rawMessage, String name, String world) {
        adminchat.getServer().broadcast(rawMessage, "adminchat.receive");
        adminchat.getServer().getPluginManager().callEvent(new AdminChatEvent(rawMessage, name, world));
        adminchat.getLogger().info(ChatColor.stripColor(rawMessage));
    }

    public String SubstituteColors(String input) {
        String output;
        output = input.replace("*black*", ChatColor.BLACK.toString());
        output = output.replace("*dblue*", ChatColor.DARK_BLUE.toString());
        output = output.replace("*dgreen*", ChatColor.DARK_GREEN.toString());
        output = output.replace("*darkaqua*", ChatColor.DARK_AQUA.toString());
        output = output.replace("*dred*", ChatColor.DARK_RED.toString());
        output = output.replace("*dpurple*", ChatColor.DARK_PURPLE.toString());
        output = output.replace("*gold*", ChatColor.GOLD.toString());
        output = output.replace("*gray*", ChatColor.GRAY.toString());
        output = output.replace("*dgray*", ChatColor.DARK_GRAY.toString());
        output = output.replace("*blue*", ChatColor.BLUE.toString());
        output = output.replace("*green*", ChatColor.GREEN.toString());
        output = output.replace("*aqua*", ChatColor.AQUA.toString());
        output = output.replace("*red*", ChatColor.RED.toString());
        output = output.replace("*lpurple*", ChatColor.LIGHT_PURPLE.toString());
        output = output.replace("*yellow*", ChatColor.YELLOW.toString());
        output = output.replace("*white*", ChatColor.WHITE.toString());
        return output;
    }

}
