package com.famaridon.redminengapi.services.redmine.rest.client.beans;

import java.util.ArrayList;
import java.util.List;

public class ArrayCustomField extends CustomField<List<String>> {
	
	public ArrayCustomField() {
		super();
		this.value = new ArrayList<>();
		this.multiple = true;
	}
}
