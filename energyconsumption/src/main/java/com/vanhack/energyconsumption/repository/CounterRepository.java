package com.vanhack.energyconsumption.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import com.vanhack.energyconsumption.domain.Counter;
import com.vanhack.energyconsumption.web.EachVillageReport;

/**
 * Counter Repository Interface
 * 
 * @Created By Thangamani Palanivel
 */
public interface CounterRepository extends CrudRepository<Counter, Long>{
	
	Optional<Counter> findByCounterId(long counterId);
	
	@Query("SELECT new com.vanhack.energyconsumption.web.EachVillageReport(v.villageId, v.villageName,"
			+ " SUM(c.consumption), c.counterId) FROM Counter c JOIN Village v ON c.counterId = v.counterId "
			+ "WHERE c.consumptionTime >= ?1 AND c.consumptionTime<= ?2 GROUP BY c.counterId")
	List<EachVillageReport> findAllCounterConsumptionReadingBetweenHours(Date beforeDate, Date afterDate);

	@Override
	@RestResource(exported=false)
	void delete(Counter arg0);

	@Override
	@RestResource(exported=false)
	void deleteAll();

	@Override
	@RestResource(exported=false)
	void deleteAll(Iterable<? extends Counter> arg0);

	@Override
	@RestResource(exported=false)
	void deleteById(Long arg0);

	@Override
	@RestResource(exported=false)
	<S extends Counter> S save(S arg0);

	@Override
	@RestResource(exported=false)
	<S extends Counter> Iterable<S> saveAll(Iterable<S> arg0);
	
}
