package com.famaridon.redminengapi.services.indicators.impl.releasenote;

import java.util.ArrayList;

public class IssueList extends ArrayList {
  public IssueTemp get(int index)
  {
    return (IssueTemp) super.get(index);
  }

  public void set(int index, IssueTemp value)
  {
    super.set(index, value);
  }
}
