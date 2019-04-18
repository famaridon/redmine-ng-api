package com.famaridon.redminengapi.services.indicators.impl.releasenote;

public class IssueTemp {
  private String subIssue;
  private long idIssue;

  public IssueTemp(String subIssues, long idIssues){
    subIssue = subIssues;
    idIssue = idIssues;
  }

   String getSubIssue() {
    return subIssue;
  }

  public void setSubIssue(String subIssue) {
    this.subIssue = subIssue;
  }

   long getIdIssue() {
    return idIssue;
  }

  public void setIdIssue(long idIssue) {
    this.idIssue = idIssue;
  }

}
