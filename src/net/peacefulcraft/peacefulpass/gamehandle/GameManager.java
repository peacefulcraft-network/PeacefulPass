package net.peacefulcraft.peacefulpass.gamehandle;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.entity.Player;

import net.peacefulcraft.peacefulpass.PeacefulPass;
import net.peacefulcraft.peacefulpass.gamehandle.player.PassPlayer;
import net.peacefulcraft.peacefulpass.gamehandle.player.PlayerData;
import net.peacefulcraft.peacefulpass.io.IOHandler;
import net.peacefulcraft.peacefulpass.io.IOLoader;
import net.peacefulcraft.peacefulpass.io.PassConfig;

public class GameManager {

    private static HashMap<UUID, PassPlayer> players;
        public static Map<UUID, PassPlayer> getPlayers() {
            return Collections.unmodifiableMap(players); 
        }

    private static HashMap<UUID, PlayerData> playerData;
        public static Map<UUID, PlayerData> getPlayerData() {
            return Collections.unmodifiableMap(playerData);
        }

    public GameManager() {
        players = new HashMap<UUID, PassPlayer>();

        IOLoader<PeacefulPass> defaultPlayers = new IOLoader<PeacefulPass>(PeacefulPass.getPluginInstance(), "ExamplePlayer.yml", "Players");
        defaultPlayers = new IOLoader<PeacefulPass>(PeacefulPass.getPluginInstance(), "ExamplePlayers.yml", "Players");
        List<File> playerFiles = IOHandler.getAllFiles(defaultPlayers.getFile().getParent());
        List<IOLoader<PeacefulPass>> playerLoaders = IOHandler.getSaveLoad(PeacefulPass.getPluginInstance(), playerFiles, "Players");

        for(IOLoader<PeacefulPass> sl : playerLoaders) {
            //Name of file should be player UUID
            for(String name : sl.getCustomConfig().getConfigurationSection("").getKeys(false)) {
                try {
                    PassConfig pc = new PassConfig(name, sl.getFile(), sl.getCustomConfig());
                    String file = sl.getFile().getPath();
                    UUID id = UUID.fromString(name);

                    PlayerData pd = new PlayerData(file, name, pc);
                    playerData.put(id, pd);
                } catch (IllegalArgumentException ex) {
                    PeacefulPass.logInfo("[GameManager] Illegal Argument converting file: " + name + " to UUID");
                    return;
                }
            }
        }
        PeacefulPass.logInfo("[GameManager] Player data loaded.");
    }

    public void preProccesPlayerJoin(UUID uuid) {
        if(findPassPlayer(uuid) != null) {
            throw new RuntimeException("Command executor is already registered in PeacefulPass");
        }
        PlayerData pd = getData(uuid);
        if(pd == null) {
            //TODO: Handle no player data
        }
        PassPlayer p = new PassPlayer(uuid, pd);
        players.put(uuid, p);
    }

    public static PassPlayer findPassPlayer(Player p) {
        return findPassPlayer(p.getUniqueId());
    }

    public static PassPlayer findPassPlayer(UUID uuid) {
        return players.get(uuid);
    }

    public static PlayerData getData(UUID id) {
        return playerData.get(id);
    }

}