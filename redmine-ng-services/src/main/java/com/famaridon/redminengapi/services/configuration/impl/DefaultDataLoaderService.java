package com.famaridon.redminengapi.services.configuration.impl;

import com.famaridon.redminengapi.domain.entities.IterationEntity;
import com.famaridon.redminengapi.domain.repositories.IterationRepository;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.transaction.Transactional;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Startup
@Singleton
public class DefaultDataLoaderService {
	
	private static final Charset CHARSET = StandardCharsets.UTF_8;
	private static final CSVFormat CSV_FORMAT = CSVFormat.RFC4180.withFirstRecordAsHeader();
	
	@EJB
	private IterationRepository iterationRepository;

	
	@PostConstruct
	@Transactional(Transactional.TxType.REQUIRED)
	protected void startup() {
		
		try {
			this.loadIterations();
			
		} catch (IOException | ParseException e) {
			throw new IllegalStateException("unable to load default data.",e);
		}
	}
	
	private void loadIterations() throws IOException, ParseException {
		CSVParser csvParser = CSVParser.parse(DefaultDataLoaderService.class.getResource("/default/iterations.csv"), CHARSET, CSV_FORMAT);
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM-dd-uuuu");
		
		for (CSVRecord record : csvParser) {
			Long number = Long.parseLong(record.get("number"));
			String name = record.get("name");
			LocalDate start = LocalDate.parse(record.get("start"), dateTimeFormatter);
			LocalDate end = LocalDate.parse(record.get("end"), dateTimeFormatter);
			this.addIteration(number, name, start, end);
		}
	}
	
	private void addIteration(Long number, String name, LocalDate start, LocalDate end) {
		Optional<IterationEntity> exist = this.iterationRepository.findByNumber(number);
		IterationEntity entity = exist.orElseGet(IterationEntity::new);
		entity.setNumber(number);
		entity.setName(name);
		entity.setStart(start);
		entity.setEnd(end);
		this.iterationRepository.save(entity);
	}
	
}
