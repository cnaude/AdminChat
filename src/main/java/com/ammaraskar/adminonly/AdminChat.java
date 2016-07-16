package com.ammaraskar.adminonly;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;


public class AdminChat extends JavaPlugin {

    public String format;
    public boolean sendChatToConsole;
    public Methods methods;
    public Set<String> toggledPlayers;

    public AdminChat() {
        this.toggledPlayers = Collections.synchronizedSet(new HashSet<String>());
    }

    @Override
    public void onDisable() {
        PluginDescriptionFile pdfFile = this.getDescription();
        this.getLogger().log(Level.INFO, "AdminChat {0} disabled.", pdfFile.getVersion());
    }

    @Override
    public void onEnable() {
        this.methods = new Methods(this);
        this.getCommand("amsg").setExecutor(new AdminChatCommand(this));
        
        this.getConfig().options().copyDefaults(true);
        
        this.getCommand("a").setExecutor(new AdminChatCommand(this));
        this.getCommand("atoggle").setExecutor(new AdminChatToggleCommand(this));
        
        this.format = methods.SubstituteColors(this.getConfig().getString("format"));
        this.sendChatToConsole = this.getConfig().getBoolean("send-to-console", true);
        this.getLogger().log(Level.INFO, "Using format: {0}", format);
        
        if (!format.contains("%playername") && !format.contains("%message")) {
            this.getLogger().severe("Format did not contain %playername or %message, switching to fallback format");
            this.format = ChatColor.RED + "[AdminChat] " + ChatColor.WHITE + "<" + ChatColor.LIGHT_PURPLE + "%playername" + ChatColor.WHITE + "> %message";
        }
        
        this.saveConfig();
        
        this.getServer().getPluginManager().registerEvents(new ChatListener(this), this);
    }

}