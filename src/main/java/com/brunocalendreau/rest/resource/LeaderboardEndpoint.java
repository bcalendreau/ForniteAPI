package com.brunocalendreau.rest.resource;

import com.brunocalendreau.model.restmodel.Leaderboard;
import com.brunocalendreau.rest.service.LeaderboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Component
@Produces(MediaType.APPLICATION_JSON)
@Path("leaderboard")
public class LeaderboardEndpoint {

    private final LeaderboardService leaderboardService;

    @Autowired
    public LeaderboardEndpoint(LeaderboardService leaderboardService) {
        this.leaderboardService = leaderboardService;
    }

    @GET
    public Leaderboard getStats(@QueryParam("gamemode") String gameMode){
        return leaderboardService.getLeaderboard(gameMode);
    }

}
