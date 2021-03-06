package com.famaridon.redminengapi.rest.mapper;

import com.famaridon.redminengapi.rest.refs.IterationRef;
import com.famaridon.redminengapi.services.indicators.beans.Iteration;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RefMapper {

  IterationRef asIterationRef(Iteration iteration);

  Iteration asIteration(IterationRef iterationRef);

}
