package com.brunocalendreau;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ComponentScan(basePackages = {"com.brunocalendreau"})
@PropertySource("classpath:application.properties")
public class SpringConfiguration {

    @Value("${fortnite.login}")
    private String login;

    @Value("${fortnite.password}")
    private String password;

    @Value("${token}")
    private String token;

    @Bean(name = "login")
    public String getLogin() {
        return login;
    }

    @Bean(name = "password")
    public String getPassword() {
        return password;
    }

    @Bean(name = "token")
    public String getToken() {
        return token;
    }
}
