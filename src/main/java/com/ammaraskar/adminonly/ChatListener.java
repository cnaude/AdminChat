package com.ammaraskar.adminonly;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    AdminChat adminchat;

    public ChatListener(AdminChat adminchat) {
        this.adminchat = adminchat;
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        if (adminchat.toggledPlayers.contains(event.getPlayer().getName())) {
            event.setCancelled(true);
            String player = event.getPlayer().getName();
            String world = event.getPlayer().getWorld().getName();
            String message = event.getMessage();
            adminchat.methods.SendFormattedMessage(message, player, world);
        }
    }

}
