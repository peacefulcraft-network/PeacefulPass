package net.peacefulcraft.peacefulpass.gamehandle.player;

import java.util.List;

import net.peacefulcraft.peacefulpass.io.PassConfig;

public class PlayerData {
    
    private String file;

    /**The String value of UUID */
    private String internalName;

    private PassConfig config;

    private List<String> completedChallenges;
        public List<String> getCompletedChallenges() { return this.completedChallenges; }

    private List<String> activeChallenges;
        public List<String> getActiveChallenges() { return this.activeChallenges; }

    private Integer passExperience;
        public Integer getExperience() { return this.passExperience; }

    public PlayerData(String file, String internalName, PassConfig pc) {
        this.file = file;
        this.internalName = internalName;
        this.config = pc;

        this.completedChallenges = config.getStringList("CompletedChallenges");
        this.activeChallenges = config.getStringList("ActiveChallenges");
        this.passExperience = config.getInteger("Experience");
    }

}