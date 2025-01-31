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

@CommandAlias("defreeze|df|st")
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
            MessageUtils.sendMessage(sender, "<yellow>Usage: /defreeze <player>");
            return;
        }

        Player target = Bukkit.getPlayer(targetName);
        if (target == null) {
            MessageUtils.sendMessage(sender, "<red>Player not found!");
            return;
        }

        if (!freezeManager.isPlayerFrozen(target)) {
            MessageUtils.sendMessage(sender, "<red>Player is not frozen!");
            return;
        }

        freezeManager.unfreezePlayer(target);
        MessageUtils.sendMessage(target, "<green>You have been unfrozen!");
    }

    @Subcommand("reload")
    @CommandCompletion("@players reload")
    @Description("Reload the configuration")
    public void onReload(Player sender) {
        plugin.reloadConfig();
        MessageUtils.sendMessage(sender, "<green>Configuration reloaded!");
    }

}