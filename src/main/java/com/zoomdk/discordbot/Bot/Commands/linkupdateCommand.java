package com.zoomdk.discordbot.Bot.Commands;

import com.zoomdk.discordbot.Bot.data.config;
import com.zoomdk.discordbot.Bot.data.data;
import com.zoomdk.discordbot.Main;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class linkupdateCommand implements CommandExecutor {

    public static boolean isPlayerInGroup(Player player, String group) {
        return player.hasPermission("group." + group);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;

            if (args.length == 0) {
                p.sendMessage("test");
                return true;
            }
            int test = 1;
            if (p.isOp()) {
                test = 2;
                p.setOp(false);
            }
            Object[] groups = Objects.requireNonNull(config.get().getConfigurationSection("discordranks")).getKeys(false).toArray();
            User user = Main.bot.getBot().getUserById(Objects.requireNonNull(data.get().getString(String.valueOf(p.getUniqueId()))));
            for (Object key : groups) {
                if (isPlayerInGroup(p, key.toString())) {
                    Guild g = Main.bot.getBot().getGuildById("831153474145878056");
                    String roleid = config.get().getString("discordranks." + key);
                    assert g != null;
                    g.addRoleToMember(Objects.requireNonNull(g.getMember(user)), Objects.requireNonNull(g.getJDA().getRoleById(roleid))).queue();
                }
            }
            if (test == 2) {
                p.setOp(true);

            }


        }


        return false;
    }
}
