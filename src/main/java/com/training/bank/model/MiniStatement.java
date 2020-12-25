package com.training.bank.model;

import org.springframework.stereotype.Component;

@Component
public class MiniStatement {
	
	private String transferAccNo;
	private String transferType;
	private Double amount;
	private Double withdraw_amount;
	private Double deposit_amount;
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
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Double getWithdraw_amount() {
		return withdraw_amount;
	}
	public void setWithdraw_amount(Double withdraw_amount) {
		this.withdraw_amount = withdraw_amount;
	}
	public Double getDeposit_amount() {
		return deposit_amount;
	}
	public void setDeposit_amount(Double deposit_amount) {
		this.deposit_amount = deposit_amount;
	}
	
	
	

}
