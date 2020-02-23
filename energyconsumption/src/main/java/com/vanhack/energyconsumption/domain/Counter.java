package com.vanhack.energyconsumption.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

/**
 * Stores the Energy Consumption Record of each Counter
 * Table structure defined in file : /resources/schema.sql
 * By Default loaded with data from file: /resources/data.sql
 * 
 * @Created by Thangamani Palanivel
 */
@Entity
@Table(name="COUNTER")
public class Counter implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="c_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cId;
	
	@Column(name="counter_id")
	@NotNull
	private Long counterId;
	
	@Column(name="consumption")
	private Double consumption;
	
	@Column(name="consumption_time")
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date consumptionTime;
	
	public Counter() {}
	
	public Counter(Long counterId, Double consumption) {
		this.counterId = counterId;
		this.consumption = consumption;
	}

	public Long getcId() {
		return cId;
	}

	public void setcId(Long cId) {
		this.cId = cId;
	}

	public Long getCounterId() {
		return counterId;
	}

	public void setCounterId(Long counterId) {
		this.counterId = counterId;
	}

	public Double getConsumption() {
		return consumption;
	}

	public void setConsumption(Double consumption) {
		this.consumption = consumption;
	}

	public Date getConsumptionTime() {
		return consumptionTime;
	}

	public void setConsumptionTime(Date consumptionTime) {
		this.consumptionTime = consumptionTime;
	}

	@Override
	public String toString() {
		return "Counter [counterId=" + counterId + ", consumption=" + consumption + ", consumptionTime="
				+ consumptionTime + "]";
	}

}
