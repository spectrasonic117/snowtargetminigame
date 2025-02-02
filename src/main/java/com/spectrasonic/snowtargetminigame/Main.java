package com.spectrasonic.snowtargetminigame;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import com.spectrasonic.snowtargetminigame.Utils.MessageUtils;
import co.aikar.commands.PaperCommandManager;

public class Main extends JavaPlugin {

    private FreezeManager freezeManager;

    @Override
    public void onEnable() {

        this.freezeManager = new FreezeManager(this);

        saveDefaultConfig();
        registerCommands();
        registerEvents();

        MessageUtils.sendStartupMessage(this);
        MessageUtils.sendMonaChina(this);
    }

    public void onDisable() { 
        MessageUtils.sendShutdownMessage(this);
    }

    public FreezeManager getFreezeManager() {
        return freezeManager;
    }

    public void registerCommands() {
        PaperCommandManager commandManager = new PaperCommandManager(this);
        commandManager.registerCommand(new FreezeCommand(freezeManager, this));
    }

    public void registerEvents() {
        Bukkit.getPluginManager().registerEvents(new FreezeListener(freezeManager, this), this);

    }
}