package com.brunocalendreau.server.event;

import com.brunocalendreau.server.network.Token;
import org.springframework.context.ApplicationEvent;

public class TokenUpdatedEvent extends ApplicationEvent {

    private final Token token;

    public TokenUpdatedEvent(Object source, Token token) {
        super(source);
        this.token = token;
    }

    public Token getToken() {
        return token;
    }
}
