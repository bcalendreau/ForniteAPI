package com.brunocalendreau.server.network;

public class OAuth2Configuration {

    private String username;
    private String password;
    private String clientId;
    private String clientSecret;
    private String grantType;
    private String token;

    private OAuth2Configuration(String username, String password, String clientId, String clientSecret, String grantType, String token) {
        this.username = username;
        this.password = password;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.grantType = grantType;
        this.token = token;
    }

    public static class Builder{
        private String username;
        private String password;
        private String clientId;
        private String clientSecret;
        private String grantType;
        private String token;


        public Builder withCredentials(String username, String password) {
            this.username = username;
            this.password = password;
            return this;
        }

        public Builder withClientId(String clientId, String clientSecret) {
            this.clientId = clientId;
            this.clientSecret = clientSecret;
            return this;
        }

        public Builder setGrantType(String grantType) {
            this.grantType = grantType;
            return this;
        }

        public Builder withToken(String token) {
            this.token = token;
            return this;
        }

        public Builder(){}

        public OAuth2Configuration build(){
            return new OAuth2Configuration(username, password, clientId, clientSecret, grantType, token);
        }
    }

    String getUsername() { return username; }

    String getPassword() {
        return password;
    }

    String getClientId() {
        return clientId;
    }

    String getClientSecret() {
        return clientSecret;
    }

    String getGrantType() {
        return grantType;
    }

    String getToken() {
        return token;
    }
}
