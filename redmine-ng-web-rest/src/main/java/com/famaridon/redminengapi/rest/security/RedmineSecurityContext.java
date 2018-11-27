package com.famaridon.redminengapi.rest.security;

import com.famaridon.redminengapi.services.redmine.rest.client.beans.User;

import javax.ws.rs.core.SecurityContext;
import java.security.Principal;

public class RedmineSecurityContext implements SecurityContext {

    private final Principal principal;
    private final boolean secure;

    public RedmineSecurityContext(User user, boolean secure) {
        this.principal = new RedminePrincipal(user);
    this.secure = secure;
    }

    @Override
    public Principal getUserPrincipal() {
        return this.principal;
    }

    @Override
    public boolean isUserInRole(String s) {
        return false;
    }

    @Override
    public boolean isSecure() {
        return this.secure;
    }

    @Override
    public String getAuthenticationScheme() {
        return "X_REDMINE_API_KEY";
    }
}
