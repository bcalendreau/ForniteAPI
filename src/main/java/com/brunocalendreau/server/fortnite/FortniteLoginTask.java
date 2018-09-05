package com.brunocalendreau.server.fortnite;

import com.brunocalendreau.SpringConfiguration;
import com.brunocalendreau.server.event.TokenUpdatedEvent;
import com.brunocalendreau.server.network.OAuth2Configuration;
import com.brunocalendreau.server.network.OAuthClient;
import com.brunocalendreau.server.network.OAuthConstants;
import com.brunocalendreau.server.network.Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.io.IOException;

/*
 *  Handles the login to Epic Server. Can be called periodically
 */
@Component
public final class FortniteLoginTask implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(FortniteLoginTask.class);

    private static final String FORTNITE_BASE_URL = "https://account-public-service-prod03.ol.epicgames.com/account/api";
    private static final String OAUTH_TOKEN_URL_ENDING = "/oauth/token";
    private static final String OAUTH_EXCHANGE_URL_ENDING = "/oauth/exchange";
    private static final String LAUNCHER_CLIENT_ID = "34a02cf8f4414e29b15921876da36f9a";
    private static final String LAUNCHER_CLIENT_SECRET = "daafbccc737745039dffe53d94fc76cf";
    private static final String FORTNITE_CLIENT_ID = "ec684b8c687f479fadea3cb2ad83f5c6";
    private static final String FORTNITE_CLIENT_SECRET = "e1f31c211f28413186262d37a13fc84d";

    private final OAuthClient oAuthClient;
    private final SpringConfiguration cfg;
    private final ApplicationEventPublisher publisher;
    private Token token = null;

    @Autowired
    public FortniteLoginTask(OAuthClient oAuthclient, SpringConfiguration cfg, ApplicationEventPublisher publisher) {
        this.oAuthClient = oAuthclient;
        this.cfg = cfg;
        this.publisher = publisher;
    }

    @Override
    public synchronized void run() {
        try {
            if (token == null || Token.isRefreshExpired(token)) {
                logger.info("Getting first token");
                getToken();
            } else {
                logger.info("Getting refresh token");
                refreshToken();
            }
            if (token == null || token.getAccess_token() == null || !token.getAccess_token().startsWith("eg1"))) {
                throw new Exception("Malformed token");
            } else {
                logger.info("Token retrieved successfully");
                publisher.publishEvent(new TokenUpdatedEvent(this, token));
            }
        } catch (Exception e) {
            logger.error("Error while getting token", e);
            token = null;
        }
    }

    private synchronized void getToken() throws IOException {
        OAuth2Configuration passwordConfig = new OAuth2Configuration.Builder()
                .withCredentials(cfg.getLogin(), cfg.getPassword())
                .withClientId(LAUNCHER_CLIENT_ID, LAUNCHER_CLIENT_SECRET)
                .setGrantType(OAuthConstants.PASSWORD)
                .build();
        token = oAuthClient.fetchToken(passwordConfig, FORTNITE_BASE_URL + OAUTH_TOKEN_URL_ENDING);

        OAuth2Configuration accessConfig = new OAuth2Configuration.Builder()
                .withToken(token.getAccess_token())
                .setGrantType(OAuthConstants.BEARER)
                .build();
        token = oAuthClient.fetchToken(accessConfig, FORTNITE_BASE_URL + OAUTH_EXCHANGE_URL_ENDING);

        OAuth2Configuration fortniteConfig = new OAuth2Configuration.Builder()
                .withToken(token.getCode())
                .withClientId(FORTNITE_CLIENT_ID, FORTNITE_CLIENT_SECRET)
                .setGrantType(OAuthConstants.EXCHANGE_CODE)
                .build();
        token = oAuthClient.fetchToken(fortniteConfig, FORTNITE_BASE_URL + OAUTH_TOKEN_URL_ENDING);
    }

    private void refreshToken() throws IOException {
        OAuth2Configuration refreshConfig = new OAuth2Configuration.Builder()
                .withToken(token.getRefresh_token())
                .withClientId(FORTNITE_CLIENT_ID, FORTNITE_CLIENT_SECRET)
                .setGrantType(OAuthConstants.REFRESH_TOKEN)
                .build();

        token = oAuthClient.fetchToken(refreshConfig, FORTNITE_BASE_URL + OAUTH_TOKEN_URL_ENDING);
    }
}
