package com.brunocalendreau.model.restmodel;

import com.brunocalendreau.model.servermodel.Account;
import com.brunocalendreau.model.servermodel.DisplayName;
import com.brunocalendreau.model.servermodel.LeaderboardRaw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Leaderboard {

    private final String statName;
    private final List<PlayerOnBoard> players;

    public Leaderboard(LeaderboardRaw leaderboardRaw, DisplayName[] displayNames){
        this.statName = leaderboardRaw.getStatName();
        players = new ArrayList<>();

        HashMap<String, String> entries = new HashMap<>();
        for(DisplayName displayName : displayNames){
            entries.put(displayName.getId(), displayName.getDisplayName());
        }

        for(Account account : leaderboardRaw.getEntries()){
            /* Somehow Epic adds extras '-' characters for leaderboards */
            String playerName = entries.get(account.getAccountId().replaceAll("-", ""));
            int score = account.getValue();
            int rank = account.getRank();
            players.add(new PlayerOnBoard(playerName, score, rank));
        }

    }

    public String getStatName() {
        return statName;
    }

    public List<PlayerOnBoard> getPlayers(){
        return players;
    }
}
