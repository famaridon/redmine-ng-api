package com.famaridon.redminengapi.services.configuration.impl;

import com.famaridon.redminengapi.domain.entities.IterationEntity;
import com.famaridon.redminengapi.domain.entities.PriorityEntity;
import com.famaridon.redminengapi.domain.entities.StatusEntity;
import com.famaridon.redminengapi.domain.entities.TrackerEntity;
import com.famaridon.redminengapi.domain.entities.WorkflowEntity;
import com.famaridon.redminengapi.domain.repositories.IterationRepository;
import com.famaridon.redminengapi.domain.repositories.PriorityRepository;
import com.famaridon.redminengapi.domain.repositories.StatusRepository;
import com.famaridon.redminengapi.domain.repositories.TrackerRepository;
import com.famaridon.redminengapi.domain.repositories.WorkflowRepository;
import com.famaridon.redminengapi.services.configuration.ConfigurationService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;
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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Optional;

@Startup
@Singleton
public class DefaultDataLoaderService {
	
	private static final Logger LOG = LoggerFactory.getLogger(DefaultDataLoaderService.class);
	private static final Charset CHARSET = StandardCharsets.UTF_8;
	private static final CSVFormat CSV_FORMAT = CSVFormat.RFC4180.withFirstRecordAsHeader();
	
	@EJB
	private ConfigurationService configurationService;
	
	@EJB
	private IterationRepository iterationRepository;
	
	@EJB
	private TrackerRepository trackerRepository;
	
	@EJB
	private StatusRepository statusRepository;
	
	@EJB
	private WorkflowRepository workflowRepository;
	
	@EJB
	private PriorityRepository priorityRepository;
	
	@PostConstruct
	@Transactional(Transactional.TxType.REQUIRED)
	protected void startup() {
		
		try {
			this.loadIterations();
			// this.loadStatus();
			// this.loadTrakers();
			// this.loadWorkflows();
			// this.loadPriorities();
			
		} catch (IOException | ParseException e) {
			throw new IllegalStateException("unable to load default data.",e);
		}
		
	}
	
	private void loadIterations() throws IOException, ParseException {
		CSVParser csvParser = CSVParser.parse(DefaultDataLoaderService.class.getResource("/default/iterations.csv"), CHARSET, CSV_FORMAT);
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM-dd-uuuu");
		
		for (CSVRecord record : csvParser) {
			String name = record.get("name");
			LocalDate start = LocalDate.parse(record.get("start"), dateTimeFormatter);
			LocalDate end = LocalDate.parse(record.get("end"), dateTimeFormatter);
			this.addIteration(name, start, end);
		}
	}
	
	private void addIteration(String name, LocalDate start, LocalDate end) {
		Optional<IterationEntity> exist = this.iterationRepository.findByName(name);
		IterationEntity entity = exist.orElseGet(IterationEntity::new);
		entity.setName(name);
		entity.setStart(start);
		entity.setEnd(end);
		this.iterationRepository.save(entity);
	}
	
	private void loadPriorities() throws IOException {
		CSVParser csvParser = CSVParser.parse(DefaultDataLoaderService.class.getResource("/default/priorities.csv"), CHARSET, CSV_FORMAT);
		for (CSVRecord record : csvParser) {
			this.addPriority(Long.parseLong(record.get("externalId")), record.get("name"));
		}
	}
	
	private void addPriority(long externalId, String name) {
		Optional<PriorityEntity> exist = this.priorityRepository.findByExternalId(externalId);
		PriorityEntity entity = exist.orElseGet(PriorityEntity::new);
		entity.setExternalId(externalId);
		entity.setName(name);
		this.priorityRepository.save(entity);
	}
	
	private void loadWorkflows() throws IOException {
		for (TrackerEntity trackerEntity : this.trackerRepository.findAll()) {
			LOG.info("Start importing workflow for tracker : {}", trackerEntity);
			CSVParser csvParser = CSVParser.parse(DefaultDataLoaderService.class.getResource("/default/workflows/" + trackerEntity.getExternalId() + ".csv"), CHARSET, CSV_FORMAT);
			for (CSVRecord record : csvParser) {
				String statusId = record.get("status").trim();
				StatusEntity statusEntity = null;
				if(StringUtils.isNotBlank(statusId)){
					Optional<StatusEntity> exist = this.statusRepository.findByExternalId(Long.parseLong(statusId));
					if(!exist.isPresent()) {
						LOG.warn("No status found for external id : {}", statusId);
						continue;
					}
					statusEntity = exist.get();
				}
				
				WorkflowEntity workflowEntity;
				Optional<WorkflowEntity> exist = this.workflowRepository.findByTrackerAndStatus(trackerEntity, Optional.ofNullable(statusEntity));
				workflowEntity = exist.orElseGet(WorkflowEntity::new);
				
				workflowEntity.setName(statusEntity == null ? "Nouvelle demande" : statusEntity.getName());
				workflowEntity.setStatus(statusEntity);
				workflowEntity.setTracker(trackerEntity);
				
				for (Map.Entry<String, Integer> header : csvParser.getHeaderMap().entrySet()) {
					if("status".equals(header.getKey())){
						continue;
					}
					
					if("x".equalsIgnoreCase(record.get(header.getValue()))) {
						Optional<StatusEntity> statusToLinkExist = this.statusRepository.findByExternalId(Long.parseLong(header.getKey()));
						statusToLinkExist.ifPresent(statusEntityToLink -> {
							trackerEntity.getStatusSet().add(statusEntityToLink);
							workflowEntity.getAvailableStatusList().add(statusEntityToLink);
						});
					}
				}
				
				this.workflowRepository.save(workflowEntity);
			}
			this.trackerRepository.save(trackerEntity);
		}
	}
	
	private void loadStatus() throws IOException {
		
		CSVParser csvParser = CSVParser.parse(DefaultDataLoaderService.class.getResource("/default/status.csv"), CHARSET, CSV_FORMAT);
		for (CSVRecord record : csvParser) {
			this.addStatus(Long.parseLong(record.get("externalId")), record.get("name"));
		}
	}
	
	private void addStatus(Long externalId, String name) {
		Optional<StatusEntity> exist = this.statusRepository.findByExternalId(externalId);
		StatusEntity statusEntity = exist.orElseGet(StatusEntity::new);
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
		Optional<TrackerEntity> exist = this.trackerRepository.findByExternalId(externalId);
		TrackerEntity trackerEntity = exist.orElseGet(TrackerEntity::new);
		trackerEntity.setExternalId(externalId);
		trackerEntity.setName(name);
		this.trackerRepository.save(trackerEntity);
	}
	
}
