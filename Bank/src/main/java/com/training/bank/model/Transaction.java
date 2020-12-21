package com.training.bank.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Transaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long trans_id;
	private String transferAccNo;
	private String transferType;
	private String ifsc;
	private Double amount;
	public Long getTrans_id() {
		return trans_id;
	}
	public void setTrans_id(Long trans_id) {
		this.trans_id = trans_id;
	}
	public String getTransferAccNo() {
		return transferAccNo;
	}
	public void setTransferAccNo(String transferAccNo) {
		this.transferAccNo = transferAccNo;
	}
	public String getTransferType() {
		return transferType;
	}
	public void setTransferType(String transferType) {
		this.transferType = transferType;
	}
	public String getIfsc() {
		return ifsc;
	}
	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}

	
}
