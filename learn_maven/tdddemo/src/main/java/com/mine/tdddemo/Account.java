package com.mine.tdddemo;

public class Account {
	
	private String id = "123456";
	private boolean status = true;
	private Zone zone = Zone.ZONE_1;
	private double balance = 0.00;

	public String getId() {
		return this.id;
	}

	public boolean getStatus() {
		return  this.status;
	}

	public Zone getZone() {
		return this.zone;
	}

	public double getBalance() {
		return this.balance;
	}

}
