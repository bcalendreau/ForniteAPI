package com.brunocalendreau.model.restmodel.player;

import com.fasterxml.jackson.annotation.JsonInclude;

/* Including NON_DEFAULT only to send only significant data */
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Stats {

    long wins;
    long top10;
    long top25;
    long top5;
    long top12;
    long top3;
    long top6;
    long score;
    long kills;
    long matches_played;
    private double kill_death_ratio;
    private double kills_per_match;
    private double win_ratio;
    long minutes_played;

    public void calculate_data(){
        if(matches_played == wins){
            kill_death_ratio = -1;
        }
        else { kill_death_ratio = Math.round((kills * 100.0d / (matches_played-wins))) / 100.0d; }
        if(matches_played == 0){
            win_ratio = 0;
            kills_per_match = 0;
        }
        else {
            win_ratio = Math.round(wins * 10000.0d / matches_played) / 100.0d;
            kills_per_match = Math.round(kills * 100.0d / matches_played) / 100.0d;
        }
    }

    public long getWins() {
        return wins;
    }

    public long getTop10() {
        return top10;
    }

    public long getTop25() {
        return top25;
    }

    public long getTop5() {
        return top5;
    }

    public long getTop12() {
        return top12;
    }

    public long getTop3() {
        return top3;
    }

    public long getTop6() {
        return top6;
    }

    public long getScore() {
        return score;
    }

    public long getKills() {
        return kills;
    }

    public long getMatches_played() {
        return matches_played;
    }

    public double getKill_death_ratio() {
        return kill_death_ratio;
    }

    public double getKills_per_match() {
        return kills_per_match;
    }

    public double getWin_ratio() {
        return win_ratio;
    }

    public long getMinutes_played() {
        return minutes_played;
    }
}
