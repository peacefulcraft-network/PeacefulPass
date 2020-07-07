package net.peacefulcraft.peacefulpass.gamehandle;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.entity.Player;

import net.peacefulcraft.peacefulpass.gamehandle.player.PassPlayer;

public class GameManager {

    private static HashMap<UUID, PassPlayer> preProcessedPlayers;

    private static HashMap<UUID, PassPlayer> players;
        public static Map<UUID, PassPlayer> getPlayers() {
            return Collections.unmodifiableMap(players); 
        }

    public GameManager() {
        preProcessedPlayers = new HashMap<UUID, PassPlayer>();
        players = new HashMap<UUID, PassPlayer>();
    }

    public void preProccesPlayerJoin(UUID uuid) {
        if(findPassPlayer(uuid) != null) {
            throw new RuntimeException("Command executor is already registered in PeacefulPass");
        }
        PassPlayer p = new PassPlayer(uuid);
        preProcessedPlayers.put(uuid, p);
    }

    public static PassPlayer findPassPlayer(Player p) {
        return findPassPlayer(p.getUniqueId());
    }

    public static PassPlayer findPassPlayer(UUID uuid) {
        return players.get(uuid);
    }

}