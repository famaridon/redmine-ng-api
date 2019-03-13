package com.famaridon.redminengapi.services.indicators.impl.releasenote;

import java.util.ArrayList;

public class VersionList extends ArrayList {
  public VersionTemp get(int index)
  {
    return (VersionTemp) super.get(index);
  }

  public void set(int index, VersionTemp value)
  {
    super.set(index, value);
  }
}
