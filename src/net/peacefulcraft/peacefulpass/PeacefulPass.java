package net.peacefulcraft.peacefulpass;

import java.util.logging.Level;

import org.bukkit.plugin.java.JavaPlugin;

import net.peacefulcraft.peacefulpass.gamehandle.GameManager;

public class PeacefulPass extends JavaPlugin {
    
    public static PeacefulPass pp;
        public static PeacefulPass getPluginInstance() { return pp; }

    public static PeacefulPassConfig cfg;
        public static PeacefulPassConfig getPassConfig() { return cfg; }
        public static Boolean showDebug() { return cfg.getDebug(); }
    
    public static GameManager gameManager;
        public static GameManager getGameManager() { return gameManager; }

    public PeacefulPass() {
        pp = this;
        cfg = new PeacefulPassConfig(getConfig());

    }

    public void onEnable() {
        this.saveDefaultConfig();

        this.loadCommands();
        this.loadEventListeners();

        gameManager = new GameManager();

        this.getLogger().info("PeacefulPass has been enabled!");
    }

    public void onDisable() {
        //TODO: Save player data to config

        this.saveConfig();
        this.getLogger().info("PeacefulPass has been disabled!");
    }

    public void loadCommands() {

    }

    public void loadEventListeners() {

    }

    public static void logDebug(String debug) {
		if(showDebug()) {
			pp.getLogger().log(Level.INFO, debug);
		}
	}
	
	public static void logInfo(String info) {
		pp.getLogger().log(Level.INFO, info);
	}
	
	public static void logWarning(String warning) {
		pp.getLogger().log(Level.WARNING, warning);
	}
	
	public static void logSevere(String severe) {
		pp.getLogger().log(Level.SEVERE, severe);
	}
}