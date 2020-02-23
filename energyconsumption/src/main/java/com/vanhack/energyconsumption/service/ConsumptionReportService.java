package com.vanhack.energyconsumption.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vanhack.energyconsumption.EnergyConsumptionException;
import com.vanhack.energyconsumption.domain.Counter;
import com.vanhack.energyconsumption.domain.Village;
import com.vanhack.energyconsumption.repository.CounterRepository;
import com.vanhack.energyconsumption.repository.VillageRepository;

/**
 * Consumer Report Service 
 * 
 *  @Created By Thangamani Palanivel
 */
@Service
public class ConsumptionReportService {
	
	private VillageRepository villageRepository;
	private CounterRepository counterRepository;
	
	@Autowired
	private ConsumptionReportService(VillageRepository villageRepository, CounterRepository counterRepository){
		this.villageRepository = villageRepository;
		this.counterRepository = counterRepository;
	}
	
	/**
	 * Adds a new Entry of Counter
	 * 
	 * @param counterId
	 * @param consumption
	 * @return
	 * throws NumberFormatException
	 * throws EnergyConsumptionException
	 */
	public Counter addCounterDetails(long counterId, Double consumption) 
			throws NumberFormatException, EnergyConsumptionException{
		return counterRepository.save(new Counter(counterId, consumption));
	}
	
	public Iterable<Counter> lookUpAllCounter(){
		return counterRepository.findAll();
	}
		
	/**
	 * Add a Village Detail
	 * 
	 * @param villageId
	 * @param villageName
	 * @param counterId
	 * @return
	 * @throws EnergyConsumptionException
	 */
	public Village addVillageDetails(long villageId, String villageName, long counterId) 
			throws EnergyConsumptionException {
		Optional<Village> village = villageRepository.findByCounterId(counterId);
		if(!village.isPresent()) {
			return villageRepository.save(new Village(villageId, villageName, counterId));
		}else {
			throw new EnergyConsumptionException("Counter "+counterId
					+" already exist for Village "+village.get().getVillageName());
		}
		
	}
	
	public Iterable<Village> lookUpAllCounterFromVillage(){
		return villageRepository.findAll();
	}
		
}
