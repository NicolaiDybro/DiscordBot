package com.zoomdk.discordbot.Bot.Commands;

import com.zoomdk.discordbot.Bot.data.config;
import com.zoomdk.discordbot.Bot.data.data;
import com.zoomdk.discordbot.Main;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class linkupdateCommand implements CommandExecutor {

    public static boolean isPlayerInGroup(Player player, String group) {
        return player.hasPermission("group." + group);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof ConsoleCommandSender) {


            if (args.length == 0) {
                return true;
            }
            Player p = Bukkit.getPlayer(args[0]);
            assert p != null;
            if (!(data.get().contains(String.valueOf(p.getUniqueId())))) {
                return true;
            }
            User user = Main.bot.getBot().getUserById(Objects.requireNonNull(data.get().getString(String.valueOf(p.getUniqueId()))));
            int test = 1;
            if (p.isOp()) {
                test = 2;
                p.setOp(false);
            }
            Object[] groups = Objects.requireNonNull(config.get().getConfigurationSection("discordranks")).getKeys(false).toArray();
            if (groups == null) {
                return true;
            }
            Guild g = Main.bot.getBot().getGuildById("769269206699999283");
            assert g != null;
            assert user != null;
            g.modifyNickname(Objects.requireNonNull(g.getMember(user)), p.getName()).queue();
            for (Object key : groups) {
                if (isPlayerInGroup(p, key.toString())) {

                    String roleid = config.get().getString("discordranks." + key);
                    assert roleid != null;
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
