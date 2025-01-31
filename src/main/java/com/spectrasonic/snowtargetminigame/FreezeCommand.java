package com.spectrasonic.snowtargetminigame;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.Optional;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import com.spectrasonic.snowtargetminigame.Utils.MessageUtils;

@CommandAlias("defreeze")
@Description("Manually unfreeze a player")
public class FreezeCommand extends BaseCommand {

    private final FreezeManager freezeManager;

    public FreezeCommand(FreezeManager freezeManager) {
        this.freezeManager = freezeManager;
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
}