package com.famaridon.redminengapi.services.indicators.impl.issue;

import com.famaridon.redminengapi.services.redmine.rest.client.beans.CustomField;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Issue;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.StringCustomField;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SumDuePointsOperatorUTest {
	
	@Test
	public void operationWithCustomField() {
		IssueOperator<BigDecimal> operator = new SumDuePointsOperator(1L);
		Issue issue = mock(Issue.class);
		CustomField<String> customField = new StringCustomField();
		customField.setValue("10");
		customField.setId(1L);
		customField.setName("cf_1");
		when(issue.findCustomFields(1L)).thenReturn(Optional.of(customField));
		when(issue.getDoneRatio()).thenReturn(10);
		BigDecimal result = operator.operation(BigDecimal.ZERO,issue);
		assertTrue(BigDecimal.valueOf(9).compareTo(result) == 0);
	}
	
	@Test
	public void operationWithoutCustomField() {
		IssueOperator<BigDecimal>  operator = new SumDuePointsOperator(1L);
		Issue issue = mock(Issue.class);
		when(issue.findCustomFields(1L)).thenReturn(Optional.empty());
		BigDecimal result = operator.operation(BigDecimal.ZERO,issue);
		assertTrue(BigDecimal.ZERO.compareTo(result) == 0);
	}
}