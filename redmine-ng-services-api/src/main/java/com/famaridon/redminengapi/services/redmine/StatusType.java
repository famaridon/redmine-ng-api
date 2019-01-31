package com.famaridon.redminengapi.services.redmine;

public enum StatusType {
  OPEN("open"), CLOSED("closed"), ALL("*");

  public final String filterValue;

  StatusType(String filterValue) {
    this.filterValue = filterValue;
  }
}
