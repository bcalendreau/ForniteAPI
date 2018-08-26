package com.brunocalendreau.model.restmodel.player.gamemode;

import com.brunocalendreau.model.restmodel.player.Stats;

public class Solo {

    public final Stats stats = new Stats();
    public void calculateData(){
        stats.calculate_data();
    }
}
