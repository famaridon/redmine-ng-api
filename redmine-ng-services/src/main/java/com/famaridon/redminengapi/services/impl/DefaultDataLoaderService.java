package com.famaridon.redminengapi.services.impl;

import com.famaridon.redminengapi.domain.entities.TrackerEntity;
import com.famaridon.redminengapi.domain.repositories.TrackerRepository;
import com.famaridon.redminengapi.services.ConfigurationService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@Startup
@Singleton
public class DefaultDataLoaderService {
	
	private static final Logger LOG = LoggerFactory.getLogger(DefaultDataLoaderService.class);
	
	@EJB
	private ConfigurationService configurationService;
	
	@EJB
	private TrackerRepository trackerRepository;
	
	@PostConstruct
	@Transactional(Transactional.TxType.REQUIRED)
	protected void startup() {
		Charset charset = Charset.forName("UTF-8");
		try {
			CSVParser csvParser = CSVParser.parse(DefaultDataLoaderService.class.getResource("/default/trackers.csv"), StandardCharsets.UTF_8, CSVFormat.RFC4180.withFirstRecordAsHeader());
			for (CSVRecord record : csvParser) {
				this.addTracker(Long.parseLong(record.get("externalId")), record.get("name"));
			}
		} catch (IOException e) {
			throw new IllegalStateException("", e);
		}
		
	}
	
	private void addTracker(Long externalId, String name) {
		TrackerEntity trackerEntity = new TrackerEntity();
		trackerEntity.setExternalId(externalId);
		trackerEntity.setName(name);
		LOG.info("adding : ", this.trackerRepository.save(trackerEntity));
	}
	
}
