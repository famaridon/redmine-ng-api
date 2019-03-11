package com.famaridon.redminengapi.services.indicators;

import java.io.IOException;

public interface SimpleIndicatorService {
  
  Long countOpenIssueByCategoryAndIteration(String apiKey, Long categoryId, Long iteration) throws IOException;
}
