package com.vanhack.energyconsumption.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Counter tagged to a village and its details
 * Table structure defined in file : /resources/schema.sql
 * By Default loaded with data from file: /resources/data.sql
 * 
 * @Created by Thangamani Palanivel
 */
@Entity
@Table(name="VILLAGE")
public class Village implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long vId;
	
	@Column(name="village_id")
	@NotNull
	private Long villageId;
	
	@Column(name="village_name")
	@Size(max = 255)
	private String villageName;
	
	@Column(name="counter_id")
	private Long counterId;
	
	public Village() {}
	
	public Village(Long villageId, String villageName, Long counterId) {
		this.villageId = villageId;
		this.villageName = villageName;
		this.counterId = counterId;
	}
	
	@JsonIgnore
	public Long getvId() {
		return vId;
	}

	public void setvId(Long vId) {
		this.vId = vId;
	}

	public Long getVillageId() {
		return villageId;
	}

	public void setVillageId(Long villageId) {
		this.villageId = villageId;
	}
	
	public String getVillageName() {
		return villageName;
	}

	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}
	
	public Long getCounterId() {
		return counterId;
	}

	public void setCounterId(Long counterId) {
		this.counterId = counterId;
	}
	
}
