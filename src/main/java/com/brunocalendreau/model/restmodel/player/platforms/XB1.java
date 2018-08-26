package com.brunocalendreau.model.restmodel.player.platforms;

import com.brunocalendreau.model.restmodel.player.gamemode.Duo;
import com.brunocalendreau.model.restmodel.player.gamemode.Solo;
import com.brunocalendreau.model.restmodel.player.gamemode.Squad;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class XB1 {

    public final Solo solo = new Solo();
    public final Duo duo = new Duo();
    public final Squad squad = new Squad();

    public void calculateData(){
        solo.calculateData();
        duo.calculateData();
        squad.calculateData();
    }
}
