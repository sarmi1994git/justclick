package com.justclick.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long id;
	private String identification;
	private String firstname;
	private String lastname;
	private String email;
	private String username;
	@Column(name = "max_value")
	private Integer maxValue;
	@Column(name = "current_value")
	private Integer currentValue;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getIdentification() {
		return identification;
	}
	public void setIdentification(String identification) {
		this.identification = identification;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getMaxValue() {
		return maxValue;
	}
	public void setMaxValue(Integer maxValue) {
		this.maxValue = maxValue;
	}
	public Integer getCurrentValue() {
		return currentValue;
	}
	public void setCurrentValue(Integer currentValue) {
		this.currentValue = currentValue;
	}
	
	
	

}
