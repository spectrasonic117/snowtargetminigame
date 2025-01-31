package com.spectrasonic.snowtargetminigame;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.spectrasonic.snowtargetminigame.Utils.MessageUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class FreezeManager {

    private final Set<UUID> frozenPlayers = new HashSet<>();
    private final HashMap<UUID, Long> cooldowns = new HashMap<>();
    private final HashMap<UUID, Integer> snowballCount = new HashMap<>();

    public boolean isPlayerFrozen(Player player) {
        return frozenPlayers.contains(player.getUniqueId());
    }

    public boolean isOnCooldown(Player player) {
        long now = System.currentTimeMillis();
        return cooldowns.getOrDefault(player.getUniqueId(), 0L) > now;
    }

    public void freezePlayer(Player player) {
        frozenPlayers.add(player.getUniqueId());

        // Add snowballs to inventory
        ItemStack snowballs = new ItemStack(Material.SNOWBALL, 144);
        player.getInventory().addItem(snowballs);

        // Track added snowballs
        snowballCount.put(player.getUniqueId(), 144);

        MessageUtils.sendMessage(player, "&bEstá congelado! Usa bolas de nieve para golpear el objetivos"); 
    }

    public void unfreezePlayer(Player player) {
        frozenPlayers.remove(player.getUniqueId());

        // Remove only the snowballs added by the system
        player.getInventory().remove(Material.SNOWBALL);

        // Set cooldown
        cooldowns.put(player.getUniqueId(), System.currentTimeMillis() + 10_000); // 10 seconds cooldown

        MessageUtils.sendMessage(player, "&a Has completado el Minijuego, continúa con tu camino!");
    }
}
