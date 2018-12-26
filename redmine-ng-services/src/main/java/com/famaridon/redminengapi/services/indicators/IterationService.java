package com.famaridon.redminengapi.services.indicators;

import com.famaridon.redminengapi.services.indicators.beans.Iteration;

import java.util.List;

public interface IterationService {
	
	List<Iteration> findAll();
	
	Iteration create(Iteration iteration);
	
	Iteration findById(Long id);
	
	void update(Iteration iteration);
}
