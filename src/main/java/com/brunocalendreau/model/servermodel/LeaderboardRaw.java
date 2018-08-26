package com.brunocalendreau.model.servermodel;

public class LeaderboardRaw {

    private String statName;
    private Account[] entries;

    public LeaderboardRaw(){}

    public String getStatName() {
        return statName;
    }

    public Account[] getEntries() {
        return entries;
    }
}