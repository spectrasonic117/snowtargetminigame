package com.spectrasonic.snowtargetminigame;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.Optional;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import com.spectrasonic.snowtargetminigame.Utils.MessageUtils;
import co.aikar.commands.annotation.Subcommand;
import co.aikar.commands.annotation.CommandCompletion;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Collections;

@CommandAlias("defreeze|df")
@Description("Manually unfreeze a player")
public class FreezeCommand extends BaseCommand {

    private final FreezeManager freezeManager;
    private final JavaPlugin plugin; // Add a reference to the plugin

    public FreezeCommand(FreezeManager freezeManager, JavaPlugin plugin) {
        this.freezeManager = freezeManager;
        this.plugin = plugin; // Initialize the plugin reference
    }

    @Default
    public void defreeze(Player sender, @Optional String targetName) {
        if (targetName == null) {
            MessageUtils.sendMessage(sender, "&cUsage: /defreeze <player>");
            return;
        }

        Player target = Bukkit.getPlayer(targetName);
        if (target == null) {
            MessageUtils.sendMessage(sender, "&cPlayer not found!");
            return;
        }

        if (!freezeManager.isPlayerFrozen(target)) {
            MessageUtils.sendMessage(sender, "&cPlayer is not frozen!");
            return;
        }

        freezeManager.unfreezePlayer(target);
        MessageUtils.sendMessage(target, "&aYou have been unfrozen!");
    }
    @Subcommand("reload")
    @Description("Reload the configuration")
    public void onReload(Player sender) {
        plugin.reloadConfig();
        MessageUtils.sendMessage(sender, "&aConfiguration reloaded!");
    }

@CommandCompletion("@players reload")
public List<String> onTabComplete(Player sender, String[] args) {
    if (args.length == 1) {
        // Provide player names for the first argument
        return Bukkit.getOnlinePlayers().stream()
                .map(Player::getName)
                .collect(Collectors.toList());
    } else if (args.length == 2) {
        // Provide "reload" as a suggestion for the second argument
        return Collections.singletonList("reload");
    }
    return Collections.emptyList();
}
}