package com.vanhack.energyconsumption.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import com.vanhack.energyconsumption.domain.Village;

/**
 * Village Repository Interface
 * 
 * @Created By Thangamani Palanivel
 */
public interface VillageRepository extends CrudRepository<Village, Long>{
	
	Optional<Village> findByCounterId(Long counterId);

	@Override
	@RestResource(exported=false)
	void delete(Village arg0);

	@Override
	@RestResource(exported=false)
	void deleteAll();

	@Override
	@RestResource(exported=false)
	void deleteAll(Iterable<? extends Village> arg0);

	@Override
	@RestResource(exported=false)
	void deleteById(Long arg0);

	@Override
	@RestResource(exported=false)
	boolean existsById(Long arg0);

	@Override
	@RestResource(exported=false)
	<S extends Village> Iterable<S> saveAll(Iterable<S> arg0);
	
}
