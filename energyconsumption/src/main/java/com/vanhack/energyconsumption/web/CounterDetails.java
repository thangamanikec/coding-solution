package com.vanhack.energyconsumption.web;

import com.vanhack.energyconsumption.domain.Village;

/**
 * Data Transfer Object for Counter Details
 * 
 * @Created By Thangamani Palanivel
 */
public class CounterDetails {
	
	private Long id;
	
	private Village village;
	
	protected CounterDetails() {}
	
	/**Constructor to fully initialize CounterDetails
	 * 
	 * @param id
	 * @param village of Type Village
	 */
	public CounterDetails(Long id, Village village) {
		this.id = id;
		this.village = village;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Village getVillage() {
		return village;
	}

	public void setVillage(Village village) {
		this.village = village;
	}
	
}
