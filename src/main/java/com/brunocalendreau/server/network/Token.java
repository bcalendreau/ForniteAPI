package com.brunocalendreau.server.network;

import java.time.Duration;
import java.time.Instant;

public class Token {

    private Long expires_in;
    private String expires_at;
    private String refresh_expires_at;
    private String token_type;
    private String refresh_token;
    private String access_token;
    private String account_id;
    private String client_id;

    //For exchange token
    private String code;

    public Token() { }

    public static boolean isRefreshExpired(Token token) {
        if (token.getRefresh_expires_at() == null || token.getRefresh_expires_at().isEmpty()) { return true; }
        Instant instant = Instant.parse(token.getRefresh_expires_at());
        Duration duration = Duration.between(Instant.now(), instant);
        return Math.abs(duration.toMinutes()) < 1;
    }

    public String getCode() {
        return code;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public String getAccess_token() {
        return access_token;
    }

    public String getAccount_id() {
        return account_id;
    }

    public String getClient_id() {
        return client_id;
    }

    public Long getExpires_in() {
        return expires_in;
    }

    public String getExpires_at() {
        return expires_at;
    }

    public String getRefresh_expires_at() {
        return refresh_expires_at;
    }

    public String getToken_type() {
        return token_type;
    }
}