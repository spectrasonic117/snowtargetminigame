package com.spectrasonic.snowtargetminigame;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class FreezeListener implements Listener {

    private final FreezeManager freezeManager;
    private final JavaPlugin plugin;

    public FreezeListener(FreezeManager freezeManager, JavaPlugin plugin) {
        this.freezeManager = freezeManager;
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        // Only affect players in Adventure mode
        if (player.getGameMode() != GameMode.ADVENTURE) return;

        // Prevent frozen players from moving
        if (freezeManager.isPlayerFrozen(player)) {
            Location to = event.getTo();
            Location from = event.getFrom();
            if (to != null && (to.getX() != from.getX() || to.getZ() != from.getZ())) {
                player.teleport(from);
            }
            return;
        }

        // Check cooldown before freezing
        if (freezeManager.isOnCooldown(player)) return;

        // Obtener la distancia de detección desde la configuración
        int detectionDistance = plugin.getConfig().getInt("detection-distance", 2);
        Location below = player.getLocation().subtract(0, detectionDistance, 0);
        if (below.getBlock().getType() == Material.LAPIS_BLOCK) {
            freezeManager.freezePlayer(player);
        }
    }

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        if (!(event.getEntity().getShooter() instanceof Player)) return;

        Player shooter = (Player) event.getEntity().getShooter();

        // Only affect players in Adventure mode
        if (shooter.getGameMode() != GameMode.ADVENTURE) return;

        // Ensure the player is frozen
        if (!freezeManager.isPlayerFrozen(shooter)) return;

        // Check if the projectile hit a target block
        if (event.getHitBlock() != null && event.getHitBlock().getType() == Material.TARGET) {
            freezeManager.unfreezePlayer(shooter);
        }
    }
}