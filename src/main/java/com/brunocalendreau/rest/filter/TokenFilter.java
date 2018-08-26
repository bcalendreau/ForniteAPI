package com.brunocalendreau.rest.filter;

import com.brunocalendreau.SpringConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;

/* Simple token authentication filter if needed
 * Uncomment to activate ! */
@Provider
@Component
public class TokenFilter implements ContainerRequestFilter {

    private final static Logger logger = LoggerFactory.getLogger(TokenFilter.class);

    private final static String AUTHORIZATION_HEADER = "Authorization";
    private final static String AUTHORIZATION_HEADER_PREFIX = "Bearer ";
    private final SpringConfiguration cfg;

    @Autowired
    public TokenFilter(SpringConfiguration cfg) {
        this.cfg = cfg;
    }

    @Override
    public void filter(ContainerRequestContext containerRequestContext) {

//        MultivaluedMap<String, String> headers = containerRequestContext.getHeaders();
//        if (headers != null && !headers.isEmpty()) {
//            List<String> authHeaders = headers.get(AUTHORIZATION_HEADER);
//            if (authHeaders != null && authHeaders.size() > 0 && authHeaders.get(0) != null) {
//                String token = authHeaders.get(0);
//                token = token.replaceAll(AUTHORIZATION_HEADER_PREFIX, "");
//                token = Base64.decodeAsString(token);
//                String servertoken = cfg.getToken();
//                if (!token.equals(servertoken)) {
//                    Response response = Response.status(Response.Status.NETWORK_AUTHENTICATION_REQUIRED)
//                            .entity(new ErrorMessage("Invalid token", 498))
//                            .build();
//                    containerRequestContext.abortWith(response);
//                    logger.error("Wrong token entered : " + token);
//                }
//            } else {
//                Response response = Response.status(Response.Status.NETWORK_AUTHENTICATION_REQUIRED)
//                        .entity(new ErrorMessage("Authentication required", 511))
//                        .build();
//                containerRequestContext.abortWith(response);
//             }
//        }
    }

}