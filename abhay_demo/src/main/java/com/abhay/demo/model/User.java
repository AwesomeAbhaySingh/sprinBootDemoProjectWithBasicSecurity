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
@Table(name="Users")
public class User {

	private long idUsers;
	private String name;
	private String mobile;
	private String password;
	private String address;
	private String authKey;
	private boolean status;
	private Date created_on;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idUsers", unique = true, nullable = false)
	public long getIdUsers() {
		return idUsers;
	}
	public void setIdUsers(long idUsers) {
		this.idUsers = idUsers;
	}
	
	@Column(name = "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "mobile")
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	@Column(name = "password")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name = "address")
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Column(name = "status")
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "time", length = 19, nullable = false)
	public Date getCreated_on() {
		return created_on;
	}
	public void setCreated_on(Date created_on) {
		this.created_on = created_on;
	}
	
	@Column(name = "authKey")
	public String getAuthKey() {
		return authKey;
	}
	public void setAuthKey(String authKey) {
		this.authKey = authKey;
	}
	
	
	
}
