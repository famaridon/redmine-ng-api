package com.famaridon.redminengapi.rest.security;

import com.famaridon.redminengapi.services.redmine.rest.client.beans.User;

import javax.ws.rs.core.SecurityContext;
import java.security.Principal;
import java.util.List;

public class RedmineSecurityContext implements SecurityContext {

    private final Principal principal;
    private final List<String> roles;
    private final boolean secure;

    public RedmineSecurityContext(User user, List<String> roles, boolean secure) {
        this.principal = new RedminePrincipal(user);
        this.secure = secure;
        this.roles = roles;
    }

    @Override
    public Principal getUserPrincipal() {
        return this.principal;
    }

    @Override
    public boolean isUserInRole(String s) {
        return this.roles.contains(s);
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
