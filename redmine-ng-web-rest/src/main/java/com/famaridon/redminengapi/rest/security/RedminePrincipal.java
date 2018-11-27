package com.famaridon.redminengapi.rest.security;

import com.famaridon.redminengapi.services.redmine.rest.client.beans.User;

import javax.ws.rs.core.SecurityContext;
import java.io.Serializable;
import java.security.Principal;

public class RedminePrincipal implements Principal, Serializable {

    private final String login;

    public RedminePrincipal(User user) {
        this.login = user.getLogin();
    }

    @Override
    public String getName() {
        return this.login;
    }
}
