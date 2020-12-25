package com.training.bank.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Deposit {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	private Double deposit_amount;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	private Date date;
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public Double getDeposit_amount() {
		return deposit_amount;
	}
	public void setDeposit_amount(Double deposit_amount) {
		this.deposit_amount = deposit_amount;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
}
