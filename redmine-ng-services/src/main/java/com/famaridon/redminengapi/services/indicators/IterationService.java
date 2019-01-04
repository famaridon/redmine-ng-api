package com.famaridon.redminengapi.services.indicators;

import com.famaridon.redminengapi.services.indicators.beans.Iteration;

import java.util.List;
import java.util.Optional;

public interface IterationService {
	
	List<Iteration> findAll();
	
	Iteration create(Iteration iteration);
	
	Iteration findById(Long id);
	
	/**
	 * find the current iteration.
	 * @return the found iteration
	 */
	Optional<Iteration> findCurrent();
	
	void update(Iteration iteration);
}
