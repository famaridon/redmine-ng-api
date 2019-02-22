package com.famaridon.redminengapi.services.indicators.impl.releasenote;

public class IssueTemp {
  private String catIssue;
  private String subIssue;
  private long idIssue;

  public IssueTemp(String catIssues, String subIssues, long idIssues){
    catIssue = catIssues;
    subIssue = subIssues;
    idIssue = idIssues;
  }

   String getCatIssue() {
    return catIssue;
  }

  public void setCatIssue(String catIssue) {
    this.catIssue = catIssue;
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
