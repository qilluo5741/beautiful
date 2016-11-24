package com.xtc.entity.dto;

public class AccountDto {
	//可用余额
    private double balance;
    //冻结余额
    private double freezeMoney;
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public double getFreezeMoney() {
		return freezeMoney;
	}
	public void setFreezeMoney(double freezeMoney) {
		this.freezeMoney = freezeMoney;
	}
    
}
