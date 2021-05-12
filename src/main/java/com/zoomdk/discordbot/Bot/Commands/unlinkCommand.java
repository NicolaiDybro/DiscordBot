package com.zoomdk.discordbot.Bot.Commands;

import com.zoomdk.discordbot.Bot.data.data;
import com.zoomdk.discordbot.Main;
import net.dv8tion.jda.api.entities.User;
import net.luckperms.api.model.user.UserManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class unlinkCommand implements CommandExecutor {




    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (data.get().contains(String.valueOf(p.getUniqueId()))) {
                User user = Main.bot.getBot().getUserById(Objects.requireNonNull(data.get().getString(String.valueOf(p.getUniqueId()))));
                assert user != null;
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&l【 &a&lLINK &8&l】&7Du har unlinket din account med: &c" + user.getName() + "#" + user.getDiscriminator()));
                data.get().set(String.valueOf(p.getUniqueId()), null);
            }
            else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&l【 &a&lLINK &8&l】&7Din account er ikke linket!"));
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&l【 &a&lLINK &8&l】&7Brug &a/link &7for at link din account"));
            }
        }

        return false;
    }
}
