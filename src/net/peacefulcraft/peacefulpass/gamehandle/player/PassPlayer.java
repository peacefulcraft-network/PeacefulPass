package net.peacefulcraft.peacefulpass.gamehandle.player;

import java.util.UUID;

import org.bukkit.entity.Player;

public class PassPlayer
{
    private int passExperience;
        public int getPassExperience() { return this.passExperience; }
        public void setPassExperience(int i) { this.passExperience = i; }

    private Player user;
        public Player getPlayer() { return this.user; }
        public void setPlayer(Player p) { this.user = p; }

    private UUID uuid;
        public UUID getUuid() { return this.uuid; }

    public PassPlayer(UUID uuid) {
        this.uuid = uuid;

    }
}