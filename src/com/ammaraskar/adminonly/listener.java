package com.ammaraskar.adminonly;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

public class listener implements Listener{
	
	AdminChat adminchat;

	public listener(AdminChat adminchat){
		this.adminchat = adminchat;
	}
	
	@EventHandler
    public void onPlayerChat(PlayerChatEvent event) {
		if(adminchat.listOfTogglePlayers.contains(event.getPlayer().getName())){
			event.setCancelled(true);
			String player = event.getPlayer().getName();
			String message= event.getMessage();
			adminchat.methods.MessageBuild(message, player);
		}
	}
	
}
