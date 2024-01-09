package com.example.springapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Facility {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long facilityId;
	private String facilityDescription;
	private Integer price;
	public Facility() {
		super();
	}
	public Facility(String facilityDescription, Integer price) {
		super();
		this.facilityDescription = facilityDescription;
		this.price = price;
	}
	public Facility(Long facilityId, String facilityDescription, Integer price) {
		super();
		this.facilityId = facilityId;
		this.facilityDescription = facilityDescription;
		this.price = price;
	}
	public Long getFacilityId() {
		return facilityId;
	}
	public void setFacilityId(Long facilityId) {
		this.facilityId = facilityId;
	}
	public String getFacilityDescription() {
		return facilityDescription;
	}
	public void setFacilityDescription(String facilityDescription) {
		this.facilityDescription = facilityDescription;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
    @Override
    public String toString() {
        return "Facility [facilityDescription=" + facilityDescription + ", facilityId=" + facilityId + ", price="
                + price + "]";
    }
	

}
