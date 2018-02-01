package com.famaridon.redminengapi.services.impl;

import com.famaridon.redminengapi.domain.entities.StatusEntity;
import com.famaridon.redminengapi.domain.entities.TrackerEntity;
import com.famaridon.redminengapi.domain.repositories.StatusRepository;
import com.famaridon.redminengapi.domain.repositories.TrackerRepository;
import com.famaridon.redminengapi.services.ConfigurationService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

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
	
	private static final Charset CHARSET = StandardCharsets.UTF_8;
	private static final CSVFormat CSV_FORMAT = CSVFormat.RFC4180.withFirstRecordAsHeader();
	
	@EJB
	private ConfigurationService configurationService;
	
	@EJB
	private TrackerRepository trackerRepository;
	
	@EJB
	private StatusRepository statusRepository;
	
	@PostConstruct
	@Transactional(Transactional.TxType.REQUIRED)
	protected void startup() {
		try {
			this.loadTrakers();
			this.loadStatus();
		} catch (IOException e) {
			throw new IllegalStateException("", e);
		}
		
	}
	
	private void loadStatus() throws IOException {
		
		CSVParser csvParser = CSVParser.parse(DefaultDataLoaderService.class.getResource("/default/status.csv"), CHARSET, CSV_FORMAT);
		for (CSVRecord record : csvParser) {
			this.addStatus(Long.parseLong(record.get("externalId")), record.get("name"));
		}
	}
	
	private void addStatus(Long externalId, String name) {
		StatusEntity statusEntity = this.statusRepository.findByExternalId(externalId);
		if(statusEntity == null ) {
			statusEntity = new StatusEntity();
		}
		statusEntity.setExternalId(externalId);
		statusEntity.setName(name);
		this.statusRepository.save(statusEntity);
	}
	
	private void loadTrakers() throws IOException {
		CSVParser csvParser = CSVParser.parse(DefaultDataLoaderService.class.getResource("/default/trackers.csv"), CHARSET, CSV_FORMAT);
		for (CSVRecord record : csvParser) {
			this.addTracker(Long.parseLong(record.get("externalId")), record.get("name"));
		}
	}
	
	private void addTracker(Long externalId, String name) {
		TrackerEntity trackerEntity = this.trackerRepository.findByExternalId(externalId);
		if(trackerEntity == null ) {
			trackerEntity = new TrackerEntity();
		}
		trackerEntity.setExternalId(externalId);
		trackerEntity.setName(name);
		this.trackerRepository.save(trackerEntity);
	}
	
}
