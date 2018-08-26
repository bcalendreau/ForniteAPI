package com.brunocalendreau.rest.resource;

import com.brunocalendreau.model.restmodel.player.Player;
import com.brunocalendreau.rest.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Component
@Produces(MediaType.APPLICATION_JSON)
@Path("stats")
public class PlayerStatsEndpoint {

    private final PlayerService playerService;

    @Autowired
    public PlayerStatsEndpoint(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GET
    public Player getStats(@QueryParam("player") String playername, @QueryParam("window") String window){
        return playerService.getPlayer(playername, window);
    }

}
