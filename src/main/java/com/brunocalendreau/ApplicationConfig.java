package com.brunocalendreau;

import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/webapi")
public class ApplicationConfig extends ResourceConfig {

    public ApplicationConfig() {
        packages("com.brunocalendreau");
    }

}
