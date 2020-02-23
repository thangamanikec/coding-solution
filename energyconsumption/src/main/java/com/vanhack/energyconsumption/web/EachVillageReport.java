package com.vanhack.energyconsumption.web;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Data Transfer Object for Each Village Consumptions Report
 * 
 * @Created By Thangamani Palanivel
 */
public class EachVillageReport {
	
	private static final int ROUND_TO_DIGIT = 2;
	
	private Long villageId;
	
	private String villageName;
	
	private Double consumption;
	
	private Long counterId;
	
	protected EachVillageReport() { }
	
	/**Constructor to fully initialize EachVillageReport
	 * 
	 * @param villageId
	 * @param villageName
	 * @param consumption
	 * @param counterId
	 */
	public EachVillageReport( Long villageId, String villageName, Double consumption, Long counterId) {
		this.villageId = villageId;
		this.villageName = villageName;
		this.consumption = consumption;
		this.counterId = counterId;
	}

	public String getVillageName() {
		return villageName;
	}

	public Double getConsumption() {
		return new BigDecimal(consumption).setScale(ROUND_TO_DIGIT, RoundingMode.HALF_UP).doubleValue();
	}

	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}

	public void setConsumption(Double consumption) {
		this.consumption = consumption;
	}

	@Override
	public String toString() {
		return "ConsumptionReport [villageId=" + villageId + ", villageName=" + villageName + ", consumption="
				+ consumption + ", counterId=" + counterId + "]";
	}	
	
}
