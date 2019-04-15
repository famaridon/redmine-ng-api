package com.famaridon.redminengapi.services.indicators.impl.releasenote;

import com.aspose.words.IMailMergeDataSource;
import com.aspose.words.ref.Ref;

public class TableDataSource implements IMailMergeDataSource {
  private final IssueList mIssue;
  private int mRecordIndex;

  public TableDataSource(IssueList issues) {
    mIssue = issues;

    // When the data source is initialized, it must be positioned before the first record.
    mRecordIndex = -1;

  }

  @Override
  public String getTableName() {
    return "issue";
  }

  @Override
  public boolean moveNext() {
    if (!isEof()) mRecordIndex++;

    return (!isEof());
  }

  @Override
  public boolean getValue(String fieldName, Ref<Object> fieldValue){
    if (fieldName.equals("Sub")) {
      fieldValue.set(mIssue.get(mRecordIndex).getSubIssue());
      return true;
    } else if (fieldName.equals("id")) {
      fieldValue.set(mIssue.get(mRecordIndex).getIdIssue());
      return true;
    }else
    {
      fieldValue.set(null);
      return false;
    }
  }

  @Override
  public IMailMergeDataSource getChildDataSource(String s) {
    return null;
  }
  private boolean isEof()
  {
    return (mRecordIndex >= mIssue.size());
  }
}
