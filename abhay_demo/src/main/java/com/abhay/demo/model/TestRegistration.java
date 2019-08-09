package com.abhay.demo.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="TestRegistration")
public class TestRegistration {

	private int idTestRegistration;
	private String name;
	private String city;
	private Date time;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idTestRegistration", unique = true, nullable = false)
	public int getIdTestRegistration() {
		return idTestRegistration;
	}
	public void setIdTestRegistration(int idTestRegistration) {
		this.idTestRegistration = idTestRegistration;
	}
	
	@Column(name = "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "city")
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "time", length = 19, nullable = false)
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	
	
	
}
