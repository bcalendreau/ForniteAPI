package com.brunocalendreau.server.network;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.UriBuilder;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/*
 * Network client utils built around a HttpUrlConnection client, faster !
 */
@Service
public class OAuthClient {

    private final static Logger logger = LoggerFactory.getLogger(OAuthClient.class);
    private final static ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public Token fetchToken(OAuth2Configuration client, String link) throws IOException {

        HttpURLConnection urlConnection = null;
        URL url;

        int responseCode;

        String grant_type = client.getGrantType();

        logger.info("Start getting Token grant type " + grant_type);

        try {
            url = new URL(link);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setAllowUserInteraction(false);
            urlConnection.setConnectTimeout(100000);
            urlConnection.setReadTimeout(100000);

            switch (grant_type) {
                case OAuthConstants.EXCHANGE_CODE:
                case OAuthConstants.REFRESH_TOKEN:
                case OAuthConstants.PASSWORD:
                    urlConnection.setRequestProperty(OAuthConstants.AUTHORIZATION, getBasicAuthorizationHeader(
                            client.getClientId(),
                            client.getClientSecret()));
                    urlConnection.setRequestMethod("POST");
                    urlConnection.setDoOutput(true);
                    urlConnection.setUseCaches(false);
                    break;
                case OAuthConstants.BEARER:
                    urlConnection.setRequestProperty(OAuthConstants.AUTHORIZATION, getAuthorizationHeaderForAccessToken(client.getToken()));
                    urlConnection.setRequestMethod("GET");
                    break;
                default:
                    break;
            }

            UriBuilder builder = null;

            switch (grant_type) {
                case OAuthConstants.EXCHANGE_CODE:
                    builder = UriBuilder.fromUri("")
                            .queryParam(OAuthConstants.GRANT_TYPE, grant_type)
                            .queryParam(OAuthConstants.EXCHANGE_CODE, client.getToken())
                            .queryParam(OAuthConstants.INCLUDEPERMS, "true")
                            .queryParam(OAuthConstants.TOKEN_TYPE, "eg1");
                    break;
                case OAuthConstants.REFRESH_TOKEN:
                    builder = UriBuilder.fromUri("")
                            .queryParam(OAuthConstants.GRANT_TYPE, grant_type)
                            .queryParam(OAuthConstants.REFRESH_TOKEN, client.getToken())
                            .queryParam(OAuthConstants.INCLUDEPERMS, "true");
                    break;
                case OAuthConstants.PASSWORD:
                    builder = UriBuilder.fromUri("")
                            .queryParam(OAuthConstants.GRANT_TYPE, grant_type)
                            .queryParam(OAuthConstants.USERNAME,
                                    client.getUsername())
                            .queryParam(OAuthConstants.PASSWORD, client.getPassword())
                            .queryParam(OAuthConstants.INCLUDEPERMS, "true");
                    break;
                default:
                    break;
            }

            if (builder != null) {
                String query = builder.build().getQuery();
                OutputStream os = urlConnection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, StandardCharsets.UTF_8));
                writer.write(query);
                writer.flush();
                writer.close();
                os.close();
            }

            urlConnection.connect();
            responseCode = urlConnection.getResponseCode();

            /*
             * Uncomment to debug
             */
            //System.out.println("response code get token" + responseCode);
            //System.out.println(getResponseString(urlConnection));\

            if (responseCode != 200) {
                logger.warn("Error while trying to get Token\n"
                        + "\nMessage : " + urlConnection.getResponseMessage()
                        + "\nCode : " + responseCode);
            }
            return mapper.readValue(urlConnection.getInputStream(), Token.class);
        }
        finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
    }

    public Object getObjectWithToken(String URL, String token, Class<?> klass, String httpmethod) throws IOException {

        HttpURLConnection urlConnection = null;
        URL url;
        int responseCode;

        try {

            url = new URL(URL.replaceAll(" ", "%20"));

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setAllowUserInteraction(false);
            urlConnection.setConnectTimeout(100000);
            urlConnection.setReadTimeout(100000);
            if(token != null){
                urlConnection.setRequestProperty(OAuthConstants.AUTHORIZATION, getAuthorizationHeaderForAccessToken(token));
            }
            urlConnection.setRequestMethod(httpmethod);

            urlConnection.connect();
            responseCode = urlConnection.getResponseCode();

            /*
             * Uncomment to debug
             */
             //System.out.println(getResponseString(urlConnection));

            if (responseCode != 200) {
                logger.warn("Error while trying to get Object of type : "+ klass
                        + "\nMessage : " + urlConnection.getResponseMessage()
                        + "\nCode : " + responseCode);
            }
            return mapper.readValue(urlConnection.getInputStream(), klass);
        }
        finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
    }

    private static String getAuthorizationHeaderForAccessToken(String accessToken) {
        return OAuthConstants.BEARER + " " + accessToken;
    }

    private static String getBasicAuthorizationHeader(String username,
                                                      String password) {
        return OAuthConstants.BASIC + " "
                + encodeCredentials(username, password);
    }

    private static String encodeCredentials(String username, String password) {
        String cred = username + ":" + password;
        return Base64.getEncoder().encodeToString(cred.getBytes());
    }

    /* * * * * * * * * * * * * *
     * UNCOMMENT FOR DEBUGGING *
     * * * * * * * * * * * * * *

    private static String getResponseString(HttpURLConnection conn) {
        StringBuilder responseBuilder = new StringBuilder();
        try {
            responseBuilder.append(conn.getResponseCode())
                    .append(" ")
                    .append(conn.getResponseMessage())
                    .append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<String, List<String>> map = conn.getHeaderFields();
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            if (entry.getKey() == null)
                continue;
            responseBuilder.append(entry.getKey())
                    .append(": ");

            List<String> headerValues = entry.getValue();
            Iterator<String> it = headerValues.iterator();
            if (it.hasNext()) {
                responseBuilder.append(it.next());

                while (it.hasNext()) {
                    responseBuilder.append(", ")
                            .append(it.next());
                }
            }

            responseBuilder.append("\n");
        }

        return responseBuilder.toString();
    } */
}