package com.brunocalendreau.server.fortnite;

import com.brunocalendreau.model.servermodel.*;
import com.brunocalendreau.server.event.TokenUpdatedEvent;
import com.brunocalendreau.server.network.OAuthClient;
import com.brunocalendreau.server.network.Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.ws.rs.HttpMethod;
import java.io.IOException;

/*
 *  Handles all the calls to Epic Servers
 *  Listens to all token updates
 */
@Component
public final class FortniteDataCaller implements ApplicationListener<TokenUpdatedEvent> {

    private final static Logger logger = LoggerFactory.getLogger(FortniteLoginTask.class);

    private final static String LOOKUP_FROM_USERNAME_URL = "https://persona-public-service-prod06.ol.epicgames.com/persona/api/public/account/lookup?q=";
    private final static String STATS_BASE_URL = "https://fortnite-public-service-prod11.ol.epicgames.com/fortnite/api/stats/accountId/";
    private final static String LEADERBOARD_BASE_URL = "https://fortnite-public-service-prod11.ol.epicgames.com/fortnite/api/leaderboards/type/global/stat/";
    private final static String LOOKUP_FROM_ID_URL = "https://account-public-service-prod03.ol.epicgames.com/account/api/public/account";

    private Token token;
    private final OAuthClient client;

    @Autowired
    public FortniteDataCaller(OAuthClient client) {
        this.client = client;
    }

    private String getStatsUrl(String accountID, String window) {
        return STATS_BASE_URL + accountID + "/bulk/window/" + window;
    }

    /*
     * @param gameMode : the stats you want
     * Example : br_placetop1_pc_m0_p2
     */
    private String getLeaderboardUrl(String gameMode) {
        return LEADERBOARD_BASE_URL + gameMode + "/window/weekly?ownertype=1&pageNumber=0&itemsPerPage=10";
    }

    public StatsRaw[] fetchPlayerStats(String nickname, String window) {
        StatsRaw[] statsRaws = new StatsRaw[0];
        try {
            SimpleLookup lookup = (SimpleLookup) client.getObjectWithToken(LOOKUP_FROM_USERNAME_URL + nickname, token.getAccess_token(), SimpleLookup.class, HttpMethod.GET);
            statsRaws = (StatsRaw[]) client.getObjectWithToken(getStatsUrl(lookup.getId(), window), token.getAccess_token(), StatsRaw[].class, HttpMethod.GET);
        } catch (IOException e) {
            logger.error("Error with StatsRaw data query", e);
        }
        return statsRaws;
    }

    public LeaderboardRaw fetchLeaderboard(String gameMode) {
        LeaderboardRaw leaderboardRaw = new LeaderboardRaw();
        try {
            leaderboardRaw = (LeaderboardRaw) client.getObjectWithToken(getLeaderboardUrl(gameMode), token.getAccess_token(), LeaderboardRaw.class, HttpMethod.POST);
        } catch (IOException e) {
            logger.error("Error with LeaderboardRaw data query", e);
        }
        return leaderboardRaw;
    }

    public DisplayName[] fetchDisplayNames(LeaderboardRaw leaderboardRaw){
        DisplayName[] displayNames = new DisplayName[0];

        if (leaderboardRaw == null || leaderboardRaw.getEntries() == null) {
            return displayNames;
        }

        String request = "?";
        for(Account account : leaderboardRaw.getEntries()){
            request = String.join(request, "&accountId=" + account.getAccountId().replaceAll("-", ""));
        }
        try {
            displayNames = (DisplayName[]) client.getObjectWithToken(LOOKUP_FROM_ID_URL + request, token.getAccess_token(),  DisplayName[].class, HttpMethod.GET);
        } catch (IOException e) {
            logger.error("Error with DisplayNames data query", e);
        }
        return displayNames;
    }

    @Override
    public void onApplicationEvent(TokenUpdatedEvent tokenUpdatedEvent) {
        this.token = tokenUpdatedEvent.getToken();
    }
}
