package com.brunocalendreau.model.restmodel.player;

import com.brunocalendreau.model.restmodel.player.platforms.PS4;
import com.brunocalendreau.model.restmodel.player.platforms.Pc;
import com.brunocalendreau.model.restmodel.player.platforms.XB1;
import com.brunocalendreau.model.servermodel.StatsRaw;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Player {

    private Pc pc;
    private PS4 ps4;
    private XB1 xb1;

    private Player(){}

    public Player(StatsRaw[] playerRawList){
        this.pc = new Pc();
        this.ps4 = new PS4();
        this.xb1 = new XB1();

        boolean hasPCStats = false;
        boolean hasPS4Stats = false;
        boolean hasXboxStats = false;

        for(StatsRaw rawStat : playerRawList){
            switch(rawStat.getName()){
                case "br_score_pc_m0_p2" :
                    hasPCStats = true;
                    this.pc.solo.stats.score = rawStat.getValue();
                    break;
                case "br_kills_pc_m0_p2" :
                    this.pc.solo.stats.kills = rawStat.getValue();
                    break;
                case "br_lastmodified_pc_m0_p2" :
                    break;
                case "br_matchesplayed_pc_m0_p2" :
                    this.pc.solo.stats.matches_played = rawStat.getValue();
                    break;
                case "br_placetop1_pc_m0_p2" :
                    this.pc.solo.stats.wins = rawStat.getValue();
                    break;
                case "br_placetop10_pc_m0_p2" :
                    this.pc.solo.stats.top10 = rawStat.getValue();
                    break;
                case "br_placetop25_pc_m0_p2" :
                    this.pc.solo.stats.top25 = rawStat.getValue();
                    break;
                case "br_minutesplayed_pc_m0_p2" :
                    this.pc.solo.stats.minutes_played = rawStat.getValue();
                    break;
                case "br_score_pc_m0_p10" :
                    this.pc.duo.stats.score = rawStat.getValue();
                    break;
                case "br_kills_pc_m0_p10" :
                    this.pc.duo.stats.kills = rawStat.getValue();
                    break;
                case "br_lastmodified_pc_m0_p10" :
                    break;
                case "br_matchesplayed_pc_m0_p10" :
                    this.pc.duo.stats.matches_played = rawStat.getValue();
                    break;
                case "br_placetop1_pc_m0_p10" :
                    this.pc.duo.stats.wins = rawStat.getValue();
                    break;
                case "br_placetop5_pc_m0_p10" :
                    this.pc.duo.stats.top5 = rawStat.getValue();
                    break;
                case "br_placetop12_pc_m0_p10" :
                    this.pc.duo.stats.top12 = rawStat.getValue();
                    break;
                case "br_minutesplayed_pc_m0_p10" :
                    this.pc.duo.stats.minutes_played = rawStat.getValue();
                    break;
                case "br_score_pc_m0_p9" :
                    this.pc.squad.stats.score = rawStat.getValue();
                    break;
                case "br_kills_pc_m0_p9" :
                    this.pc.squad.stats.kills = rawStat.getValue();
                    break;
                case "br_lastmodified_pc_m0_p9" :
                    break;
                case "br_matchesplayed_pc_m0_p9" :
                    this.pc.squad.stats.matches_played = rawStat.getValue();
                    break;
                case "br_placetop1_pc_m0_p9" :
                    this.pc.squad.stats.wins = rawStat.getValue();
                    break;
                case "br_placetop3_pc_m0_p9" :
                    this.pc.squad.stats.top3 = rawStat.getValue();
                    break;
                case "br_placetop6_pc_m0_p9" :
                    this.pc.squad.stats.top6 = rawStat.getValue();
                    break;
                case "br_minutesplayed_pc_m0_p9" :
                    this.pc.squad.stats.minutes_played = rawStat.getValue();
                    break;
                case "br_score_ps4_m0_p2" :
                    hasPS4Stats = true;
                    this.ps4.solo.stats.score = rawStat.getValue();
                    break;
                case "br_kills_ps4_m0_p2" :
                    this.ps4.solo.stats.kills = rawStat.getValue();
                    break;
                case "br_lastmodified_ps4_m0_p2" :
                    break;
                case "br_matchesplayed_ps4_m0_p2" :
                    this.ps4.solo.stats.matches_played = rawStat.getValue();
                    break;
                case "br_placetop1_ps4_m0_p2" :
                    this.ps4.solo.stats.wins = rawStat.getValue();
                    break;
                case "br_placetop10_ps4_m0_p2" :
                    this.ps4.solo.stats.top10 = rawStat.getValue();
                    break;
                case "br_placetop25_ps4_m0_p2" :
                    this.ps4.solo.stats.top25 = rawStat.getValue();
                    break;
                case "br_minutesplayed_ps4_m0_p2" :
                    this.ps4.solo.stats.minutes_played = rawStat.getValue();
                    break;
                case "br_score_ps4_m0_p10" :
                    this.ps4.duo.stats.score = rawStat.getValue();
                    break;
                case "br_kills_ps4_m0_p10" :
                    this.ps4.duo.stats.kills = rawStat.getValue();
                    break;
                case "br_lastmodified_ps4_m0_p10" :
                    break;
                case "br_matchesplayed_ps4_m0_p10" :
                    this.ps4.duo.stats.matches_played = rawStat.getValue();
                    break;
                case "br_placetop1_ps4_m0_p10" :
                    this.ps4.duo.stats.wins = rawStat.getValue();
                    break;
                case "br_placetop5_ps4_m0_p10" :
                    this.ps4.duo.stats.top5 = rawStat.getValue();
                    break;
                case "br_placetop12_ps4_m0_p10" :
                    this.ps4.duo.stats.top12 = rawStat.getValue();
                    break;
                case "br_minutesplayed_ps4_m0_p10" :
                    this.ps4.duo.stats.minutes_played = rawStat.getValue();
                    break;
                case "br_score_ps4_m0_p9" :
                    this.ps4.squad.stats.score = rawStat.getValue();
                    break;
                case "br_kills_ps4_m0_p9" :
                    this.ps4.squad.stats.kills = rawStat.getValue();
                    break;
                case "br_lastmodified_ps4_m0_p9" :
                    break;
                case "br_matchesplayed_ps4_m0_p9" :
                    this.ps4.squad.stats.matches_played = rawStat.getValue();
                    break;
                case "br_placetop1_ps4_m0_p9" :
                    this.ps4.squad.stats.wins = rawStat.getValue();
                    break;
                case "br_placetop3_ps4_m0_p9" :
                    this.ps4.squad.stats.top3 = rawStat.getValue();
                    break;
                case "br_placetop6_ps4_m0_p9" :
                    this.ps4.squad.stats.top6 = rawStat.getValue();
                    break;
                case "br_minutesplayed_ps4_m0_p9" :
                    this.ps4.squad.stats.minutes_played = rawStat.getValue();
                    break;
                case "br_score_xb1_m0_p2" :
                    hasXboxStats = true;
                    this.xb1.solo.stats.score = rawStat.getValue();
                    break;
                case "br_kills_xb1_m0_p2" :
                    this.xb1.solo.stats.kills = rawStat.getValue();
                    break;
                case "br_lastmodified_xb1_m0_p2" :
                    break;
                case "br_matchesplayed_xb1_m0_p2" :
                    this.xb1.solo.stats.matches_played = rawStat.getValue();
                    break;
                case "br_placetop1_xb1_m0_p2" :
                    this.xb1.solo.stats.wins = rawStat.getValue();
                    break;
                case "br_placetop10_xb1_m0_p2" :
                    this.xb1.solo.stats.top10 = rawStat.getValue();
                    break;
                case "br_placetop25_xb1_m0_p2" :
                    this.xb1.solo.stats.top25 = rawStat.getValue();
                    break;
                case "br_minutesplayed_xb1_m0_p2" :
                    this.xb1.solo.stats.minutes_played = rawStat.getValue();
                    break;
                case "br_score_xb1_m0_p10" :
                    this.xb1.duo.stats.score = rawStat.getValue();
                    break;
                case "br_kills_xb1_m0_p10" :
                    this.xb1.duo.stats.kills = rawStat.getValue();
                    break;
                case "br_lastmodified_xb1_m0_p10" :
                    break;
                case "br_matchesplayed_xb1_m0_p10" :
                    this.xb1.duo.stats.matches_played = rawStat.getValue();
                    break;
                case "br_placetop1_xb1_m0_p10" :
                    this.xb1.duo.stats.wins = rawStat.getValue();
                    break;
                case "br_placetop5_xb1_m0_p10" :
                    this.xb1.duo.stats.top5 = rawStat.getValue();
                    break;
                case "br_placetop12_xb1_m0_p10" :
                    this.xb1.duo.stats.top12 = rawStat.getValue();
                    break;
                case "br_minutesplayed_xb1_m0_p10" :
                    this.xb1.duo.stats.minutes_played = rawStat.getValue();
                    break;
                case "br_score_xb1_m0_p9" :
                    this.xb1.squad.stats.score = rawStat.getValue();
                    break;
                case "br_kills_xb1_m0_p9" :
                    this.xb1.squad.stats.kills = rawStat.getValue();
                    break;
                case "br_lastmodified_xb1_m0_p9" :
                    break;
                case "br_matchesplayed_xb1_m0_p9" :
                    this.xb1.squad.stats.matches_played = rawStat.getValue();
                    break;
                case "br_placetop1_xb1_m0_p9" :
                    this.xb1.squad.stats.wins = rawStat.getValue();
                    break;
                case "br_placetop3_xb1_m0_p9" :
                    this.xb1.squad.stats.top3 = rawStat.getValue();
                    break;
                case "br_placetop6_xb1_m0_p9" :
                    this.xb1.squad.stats.top6 = rawStat.getValue();
                    break;
                case "br_minutesplayed_xb1_m0_p9" :
                    this.xb1.squad.stats.minutes_played = rawStat.getValue();
                    break;
                default : break;
            }
        }
        if(!hasPCStats){
            this.pc = null;
        } else {
            this.pc.calculateData();
        }
        if(!hasPS4Stats){
            this.ps4 = null;
        } else {
            this.ps4.calculateData();
        }
        if(!hasXboxStats){
            this.xb1 = null;
        } else {
            this.xb1.calculateData();
        }
    }

    public Pc getPc() {
        return pc;
    }

    public PS4 getPs4() {
        return ps4;
    }

    public XB1 getXb1() {
        return xb1;
    }

}