package com.brunocalendreau.rest.service;

import com.brunocalendreau.model.restmodel.Leaderboard;
import com.brunocalendreau.model.servermodel.DisplayName;
import com.brunocalendreau.model.servermodel.LeaderboardRaw;
import com.brunocalendreau.server.fortnite.FortniteDataCaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;

@Service
public class LeaderboardService {

    private final FortniteDataCaller fortniteDataCaller;

    @Autowired
    public LeaderboardService(FortniteDataCaller fortniteDataCaller) {
        this.fortniteDataCaller = fortniteDataCaller;
    }

    public Leaderboard getLeaderboard(String gameMode){

        if(gameMode == null ||
                gameMode.isEmpty()){
            throw new BadRequestException("Please specify gamemode. Example : 'placetop1_pc_m0_p2'");
        }

        LeaderboardRaw leaderboardRaw = fortniteDataCaller.fetchLeaderboard(gameMode);
        if(leaderboardRaw == null){
            throw new NotFoundException("Leaderboard for mode " + gameMode + " not found.");
        }

        DisplayName[] names = fortniteDataCaller.fetchDisplayNames(leaderboardRaw);
        if(names == null || names.length == 0){
            throw new NotFoundException("Couldn't fetch names for Leaderboard");
        }

        return new Leaderboard(leaderboardRaw, names);
    }
}
