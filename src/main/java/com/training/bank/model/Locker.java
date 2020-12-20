package com.training.bank.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Locker {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long locker_id;
	
	private boolean available;

	public Long getLocker_id() {
		return locker_id;
	}

	public void setLocker_id(Long locker_id) {
		this.locker_id = locker_id;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}
	
	
	

}
