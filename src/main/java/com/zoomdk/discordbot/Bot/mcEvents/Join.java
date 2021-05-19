package com.zoomdk.discordbot.Bot.mcEvents;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Join implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "linkupdate " + p.getName());
    }

}
