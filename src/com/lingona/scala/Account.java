package com.lingona.scala;

import java.math.BigDecimal;
import java.util.List;

class Address1 {
	
}

class Calculator {
	BigDecimal calculate(Account account, BigDecimal precision){
		return new BigDecimal(0);
	}	
}

enum Status {
	OPEN,
}

enum AccountType {
}

//Account.java
public class Account {
	
	private List<String>  names;
	private String        number;
	private List<Address1> addresses;
	private BigDecimal    interest;
	private Status        status;
	private AccountType   accountType;

	public Account(String name, String number, Address1 address) {
		this.number = number;
	 //..
	}

	//.. standard getters
	
	public void calculate(BigDecimal precision, Calculator c) {
		interest = c.calculate(this, precision);
	}
	
	public boolean isOpen() {
		return status.equals(Status.OPEN);
	}
	
	public List<Address1> getAddresses() {
		return addresses;
	}
	
	public List<String> getNames() {
		return names;
	}
	
	public BigDecimal getInterest() {
		return interest;
	}
	
	public AccountType getAccountType() {
		return accountType;
	}
	
	void addName(String name) {
		
	}
	
	void addAddress(Address1 adress) {
		
	}
}
