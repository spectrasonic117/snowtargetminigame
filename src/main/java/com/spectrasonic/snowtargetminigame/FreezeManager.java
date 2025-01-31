package com.spectrasonic.snowtargetminigame;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import com.spectrasonic.snowtargetminigame.Utils.MessageUtils;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import org.bukkit.plugin.java.JavaPlugin;

public class FreezeManager {

    private final Set<UUID> frozenPlayers = new HashSet<>();
    private final HashMap<UUID, Long> cooldowns = new HashMap<>();
    private final HashMap<UUID, Integer> snowballCount = new HashMap<>();
    private final JavaPlugin plugin;

    public FreezeManager(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public boolean isPlayerFrozen(Player player) {
        return frozenPlayers.contains(player.getUniqueId());
    }

    public boolean isOnCooldown(Player player) {
        long now = System.currentTimeMillis();
        return cooldowns.getOrDefault(player.getUniqueId(), 0L) > now;
    }

    public void freezePlayer(Player player) {
        frozenPlayers.add(player.getUniqueId());

        // Obtener la cantidad de bolas de nieve desde la configuración
        int snowballAmount = plugin.getConfig().getInt("snowball-amount", 999);
        ItemStack snowballs = new ItemStack(Material.SNOWBALL, snowballAmount);
        player.getInventory().addItem(snowballs);

        // Track added snowballs
        snowballCount.put(player.getUniqueId(), 144);

        MessageUtils.sendMessage(player, "&bEstá congelado! Usa bolas de nieve para golpear el objetivos"); 
    }

    public void unfreezePlayer(Player player) {
        frozenPlayers.remove(player.getUniqueId());

        // Remove only the snowballs added by the system
        player.getInventory().remove(Material.SNOWBALL);

        // Obtener el tiempo de cooldown desde la configuración
        long cooldownTime = plugin.getConfig().getLong("cooldown-time", 10) * 1000; // Convertir a milisegundos
        cooldowns.put(player.getUniqueId(), System.currentTimeMillis() + cooldownTime);

        MessageUtils.sendMessage(player, "&a Has completado el Minijuego, continúa con tu camino!");
    }
}
