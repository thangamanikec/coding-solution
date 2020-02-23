package com.vanhack.energyconsumption.web;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.vanhack.energyconsumption.EnergyConsumptionException;
import com.vanhack.energyconsumption.domain.Counter;
import com.vanhack.energyconsumption.domain.Village;
import com.vanhack.energyconsumption.repository.CounterRepository;
import com.vanhack.energyconsumption.repository.VillageRepository;
import com.vanhack.energyconsumption.service.ConsumptionReportService;

/**
 * Energy Consumption Report Controller
 * providing details of counter & its village
 * 
 * @Created By Thangamani Palanivel
 */
@RestController
@RequestMapping(path = "/energyConsumption")
public class ConsumptionReportController {
	
	private static final Long ZERO = Long.valueOf(0);

	private CounterRepository counterRepository;

	private VillageRepository villageRepository;
	
	private ConsumptionReportService consumptionReportService;

	protected ConsumptionReportController() {}

	@Autowired
	public ConsumptionReportController(CounterRepository counterRepository, VillageRepository villageRepository, ConsumptionReportService consumptionReportService) {
		this.counterRepository = counterRepository;
		this.villageRepository = villageRepository;
		this.consumptionReportService = consumptionReportService;
	}

	/**
	 * Create a Counter Callback Consumption Record
	 *
	 * @param counterRecord
	 * @throws EnergyConsumptionException 
	 */
	@RequestMapping(method = RequestMethod.POST, path = "/counter_callback")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<String> createCounterCallback(@RequestBody @Validated Counter counterRecord, Errors errors) 
			throws EnergyConsumptionException, MethodArgumentNotValidException {
		if (errors.hasErrors()) {
			throw new EnergyConsumptionException("Request not provided properly");
	    }
		counterRepository.save(counterRecord);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	/**
	 * Create/Update a Village Record
	 *
	 * @param counterRecord
	 * @throws EnergyConsumptionException 
	 */
	@RequestMapping(method = RequestMethod.POST, path = "/villageDetailsUpdate")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<String> createVillage(@RequestBody @Validated Village villageRecord, Errors errors) 
			throws EnergyConsumptionException, MethodArgumentNotValidException {
		if (errors.hasErrors()) {
			throw new EnergyConsumptionException("Request not provided properly");
	    }
		consumptionReportService.addVillageDetails(villageRecord.getVillageId(), villageRecord.getVillageName(), villageRecord.getCounterId());
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * To get the details of each Counter
	 *
	 * @param counterId
	 * @return Counter details
	 * @throws EnergyConsumptionException 
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/counters/{counterId}")
	@ResponseStatus(HttpStatus.OK)
	public CounterDetails getCounterDetails(@PathVariable(value = "counterId") Long counterId) 
			throws EnergyConsumptionException {
		Village village = verifyCounter(counterId);
		return new CounterDetails(counterId, village);
	}

	/**
	 * To get the Consumption Report Details for past 24 hrs (Default)
	 * 	for each Village
	 *
	 * @param counterId
	 * @return Counter details
	 * @throws EnergyConsumptionException 
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/consumption_report")
	public ConsumptionReports getCounterReports(@RequestParam(name = "duration") String duration) 
			throws EnergyConsumptionException {
		Integer hrs;
		try {
			hrs = Integer.parseInt(duration.replace("h",""));
		}catch(Exception e) {
			throw new EnergyConsumptionException("Request not provided properly");
		}
		ConsumptionReports reports = new ConsumptionReports();
		List<EachVillageReport> villages = new ArrayList<>();
		
		List<Entry<String, Double>> list = calculateConsumerReportForEachVillage(
				counterRepository.findAllCounterConsumptionReadingBetweenHours(asDate(hrs.longValue()), asDate(ZERO)));
		for (Entry<String, Double> entry : list) {
			EachVillageReport eachReport = new EachVillageReport();
			eachReport.setVillageName(entry.getKey());
			eachReport.setConsumption(entry.getValue());
			villages.add(eachReport);
		}
		reports.setVillages(villages);
		return reports;

	}
	
	/**
	 * Calculates Consumption of each Village
	 * 
	 * @param List<EachVillageReport>
	 * @return List<Entry<String, Double>>
	 */
	public List<Entry<String, Double>> calculateConsumerReportForEachVillage(List<EachVillageReport> list) {
		Map<String, Double> calculatedVillageMap = list.stream().collect(Collectors.groupingBy(
				EachVillageReport::getVillageName, Collectors.summingDouble(EachVillageReport::getConsumption)));

		return calculatedVillageMap.entrySet().stream().collect(Collectors.toList());
	}

	/**
	 * Verify and return the Village given a counterId.
	 *
	 * @param counterId
	 * @return the found Village
	 * @throws EnergyConsumptionException
	 *             if no Village found.
	 */
	private Village verifyCounter(Long counterId) throws EnergyConsumptionException {
		return villageRepository.findByCounterId(counterId)
				.orElseThrow(() -> new EnergyConsumptionException("Counter " + counterId + " does not exist "));
	}
	
	/**
	 * Returns the UTC Date
	 * 
	 * @param hoursBefore
	 * @return date
	 */
	private static Date asDate(long hoursBefore) {
		OffsetDateTime utc = OffsetDateTime.now(ZoneOffset.UTC).minusHours(hoursBefore);
        return Date.from(utc.toInstant());
    }
	
}
