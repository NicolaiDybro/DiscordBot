package com.zoomdk.discordbot.Bot.Commands;

import com.zoomdk.discordbot.Bot.data.data;


import com.zoomdk.discordbot.Main;
import net.dv8tion.jda.api.entities.User;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Objects;

public class linkCommand implements CommandExecutor {

    public static HashMap<Player, String> pagelink = new HashMap<>();

    public static int randomNummer(int max){
        return (int) (Math.random()*max);
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (data.get().contains(String.valueOf(p.getUniqueId()))) {
                User user = Main.bot.getBot().getUserById(Objects.requireNonNull(data.get().getString(String.valueOf(p.getUniqueId()))));
                assert user != null;
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&l【 &a&lLINK &8&l】&7Din account er allerde linket med: &a" + user.getName() + "#" + user.getDiscriminator()));
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&l【 &a&lLINK &8&l】&7Brug &c/unlink &7for at unlink din account"));
                return true;
            }
            if (pagelink.get(p) != null) {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&l【 &a&lLINK &8&l】&7Din link kode er: &b" + pagelink.get(p) + " &7 - skriv til &aLink Botten &7for at link din account"));
            }
            else {
                String code = String.valueOf(randomNummer(9)) + randomNummer(9) + randomNummer(9) + randomNummer(9);
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&l【 &a&lLINK &8&l】&7Din link kode er: &b" + code + " &7 - skriv til &aLink Botten &7for at link din account"));
                pagelink.put(p, code);
            }

        }

        return false;
    }
}
