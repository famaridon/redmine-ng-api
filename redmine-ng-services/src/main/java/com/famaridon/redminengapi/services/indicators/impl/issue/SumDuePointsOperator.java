package com.famaridon.redminengapi.services.indicators.impl.issue;

import com.famaridon.redminengapi.services.redmine.rest.client.beans.CustomField;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Issue;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.Optional;

public class SumDuePointsOperator implements IssueOperator<BigDecimal> {

  private final Long developmentCostField;

  public SumDuePointsOperator(Long developmentCostField) {
    this.developmentCostField = developmentCostField;
  }

  @Override
  public BigDecimal operation(BigDecimal previous, Issue issue) {
    Optional<CustomField> customField = issue.findCustomFields(developmentCostField);
    if (customField.isPresent() && StringUtils.isNotBlank(
        (CharSequence) customField.get().getValue())) {
      BigDecimal dueRatio = BigDecimal.ONE
          .subtract(new BigDecimal(issue.getDoneRatio()).divide(new BigDecimal(100)));
      return previous.add(dueRatio.multiply(new BigDecimal((String) customField.get().getValue())));
    }
    return previous;
  }
}
