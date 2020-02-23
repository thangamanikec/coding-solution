package com.vanhack.energyconsumption.web;

import java.util.List;

/**
 * Data Transfer Object for Consumptions Reports of 
 * 	All villages tagged with a Counter
 * 
 * @Created By Thangamani Palanivel
 */
public class ConsumptionReports {
	
	private List<EachVillageReport> villages;
	
	protected ConsumptionReports(){}

	public List<EachVillageReport> getVillages() {
		return villages;
	}

	public void setVillages(List<EachVillageReport> villages) {
		this.villages = villages;
	}

}
