package com.zoomdk.discordbot.Bot;

import com.zoomdk.discordbot.Bot.Commands.linkCommand;
import com.zoomdk.discordbot.Bot.data.data;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import net.luckperms.api.event.LuckPermsEvent;
import net.luckperms.api.event.node.NodeAddEvent;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class Events implements EventListener {

    public static <T, E> T getKeyByValue(Map<T, E> map, E value) {
        for (Map.Entry<T, E> entry : map.entrySet()) {
            if (Objects.equals(value, entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }
    public void sendMessage(User user, String content) {
        user.openPrivateChannel()
                .flatMap(channel -> channel.sendMessage(content))
                .queue();
    }





    @Override
    public void onEvent(@NotNull GenericEvent e) {
        if (e instanceof PrivateMessageReceivedEvent) {
            PrivateMessageReceivedEvent Event = (PrivateMessageReceivedEvent) e;
            for ( String key : linkCommand.pagelink.values()) {
                if (key.equals(Event.getMessage().getContentRaw())) {
                    OfflinePlayer of = getKeyByValue(linkCommand.pagelink, key);

                    assert of != null;


                    sendMessage(Event.getAuthor(), "Din account er linket med: " + of.getName());
                    if (of.isOnline()) {
                        Player p = (Player) of;
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&l【 &a&lLINK &8&l】&7Din account er blevet linket med: &a" + ((PrivateMessageReceivedEvent) e).getAuthor().getName() + "#" + ((PrivateMessageReceivedEvent) e).getAuthor().getDiscriminator()));
                    }
                    data.get().set(String.valueOf(of.getUniqueId()), ((PrivateMessageReceivedEvent) e).getAuthor().getId());
                    data.save();


                }

            }
        }
    }
}