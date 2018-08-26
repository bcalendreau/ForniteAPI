package com.brunocalendreau.rest.service;

import com.brunocalendreau.model.restmodel.player.Player;
import com.brunocalendreau.model.servermodel.StatsRaw;
import com.brunocalendreau.server.fortnite.FortniteDataCaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;

@Service
public class PlayerService {

    private final FortniteDataCaller fortniteDataCaller;

    @Autowired
    public PlayerService(FortniteDataCaller fortniteDataCaller) {
        this.fortniteDataCaller = fortniteDataCaller;
    }

    public Player getPlayer(String player, String window){

        if(player == null || player.isEmpty()){
            throw new BadRequestException("Player name must not be empty");
        }
        if(window == null ||
                (!window.equals("weekly") && !window.equals("alltime"))){
            throw new BadRequestException("Window parameter must be set to 'weekly' or 'alltime'");
        }

        StatsRaw[] stats = fortniteDataCaller.fetchPlayerStats(player, window);
        if(stats == null || stats.length == 0){
            throw new NotFoundException("Player " + player + " not found.");
        }

        return new Player(stats);
    }

}
