package com.famaridon.redminengapi.rest.dto;

import java.util.ArrayList;
import java.util.List;

public class Paginable<T> {
	public long total_count;
	public long offset;
	public long limit;
	public List<T> elements = new ArrayList<>();
	
}