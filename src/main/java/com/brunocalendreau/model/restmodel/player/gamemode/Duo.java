package com.brunocalendreau.model.restmodel.player.gamemode;

import com.brunocalendreau.model.restmodel.player.Stats;

public class Duo {
    public Stats stats = new Stats();
    public void calculateData(){
        stats.calculate_data();
    }
}
