package com.ammaraskar.adminonly;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import com.ammaraskar.adminonly.AdminChatCommand;

public class AdminChat extends JavaPlugin {
	
	public String format;
	public Methods methods;
	public ArrayList<String> listOfTogglePlayers = new ArrayList<String>();

	@Override
	public void onDisable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		this.getLogger().info("AdminChat " + pdfFile.getVersion() + " disabled.");
	}

	@Override
	public void onEnable() {
		this.methods = new Methods(this);
		this.getCommand("amsg").setExecutor(new AdminChatCommand(this));
		this.getConfig().options().copyDefaults(true);
		if(this.getConfig().getBoolean("usealias")){
			this.getCommand("a").setExecutor(new AdminChatCommand(this));
		}
		this.format = methods.SubstituteColors(this.getConfig().getString("format"));
		this.getLogger().info("Using format: " + format);
		if(!format.contains("%playername") && !format.contains("%message")){
			this.getLogger().severe("WARNING: Format did not contain %playername or %message, switching to fallback format");
			this.format = ChatColor.RED + "[AdminChat] " + ChatColor.WHITE + "<" + ChatColor.LIGHT_PURPLE + "%playername" + ChatColor.WHITE + "> %message";
		}
		this.saveConfig();
		this.getCommand("atoggle").setExecutor(new AdminChatToggleCommand(this));
		this.getServer().getPluginManager().registerEvents(new listener(this), this);
        PluginDescriptionFile pdfFile = this.getDescription();
        this.getLogger().info( pdfFile.getName() + " version " + pdfFile.getVersion() + " is enabled!" );
	}
	
	
}