package net.peacefulcraft.peacefulpass.gamehandle.player;

import java.util.UUID;

import org.bukkit.entity.Player;

import net.peacefulcraft.peacefulpass.PeacefulPass;

public class PassPlayer
{
    private int passExperience;
        public int getPassExperience() { return this.passExperience; }
        public void setPassExperience(int i) { this.passExperience = i; }

    private Player user;
        public Player getPlayer() { return this.user; }

    private UUID uuid;
        public UUID getUuid() { return this.uuid; }

    private PlayerData pd;

    public PassPlayer(UUID uuid, PlayerData pd) {
        this.uuid = uuid;
        this.user = PeacefulPass.getPluginInstance().getServer().getPlayer(uuid);

        this.pd = pd;
        this.passExperience = pd.getExperience();

    }
}