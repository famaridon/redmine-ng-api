package com.famaridon.redminengapi.services.redmine.rest.client.beans;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public abstract class CustomField<T> extends AbstractRedmineBean {
	
	protected T value;
	protected boolean multiple;
	
	public T getValue() {
		return this.value;
	}
	
	public void setValue(T value) {
		this.value = value;
	}
	
	public boolean isMultiple() {
		return this.multiple;
	}
	
	public void setMultiple(boolean multiple) {
		this.multiple = multiple;
	}
	
	@Override
	public boolean equals(Object o)
	{
		if (this == o) {
			return true;
		}
		
		if (!(o instanceof CustomField)) {
			return false;
		}
		
		CustomField<?> that = (CustomField<?>)o;
		
		return new EqualsBuilder().appendSuper(super.equals(o)).append(multiple, that.multiple).append(value, that.value).isEquals();
	}
	
	@Override
	public int hashCode()
	{
		return new HashCodeBuilder(17, 37).appendSuper(super.hashCode()).append(value).append(multiple).toHashCode();
	}
}
