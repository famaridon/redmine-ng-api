package com.famaridon.redminengapi.rest.security;

import com.famaridon.redminengapi.services.redmine.rest.client.beans.User;
import java.security.Principal;
import java.util.Collections;
import java.util.List;
import javax.ws.rs.core.SecurityContext;

public class RedmineSecurityContext implements SecurityContext {

  private final Principal principal;
  private final List<String> roles;
  private final boolean secure;

  public RedmineSecurityContext(User user, List<String> roles, boolean secure) {
    this.principal = new RedminePrincipal(user);
    this.secure = secure;
    this.roles = Collections.unmodifiableList(roles);
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

  public List<String> getRoles() {
    return this.roles;
  }
}
