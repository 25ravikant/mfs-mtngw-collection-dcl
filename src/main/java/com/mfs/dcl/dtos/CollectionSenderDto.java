package com.mfs.dcl.dtos;

/*
 * CollectionSenderDto Class for Sender
 *
 * @since 28-04-2022
 */

public class CollectionSenderDto {
	
	private String account_number;
	
	private String first_name;
	
	private String last_name;

	public String getAccount_number() {
		return account_number;
	}

	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	//toString Method
	@Override
	public String toString() {
		return "C2BPostCreateCollectionRequestSenderDto [account_number=" + account_number + ", first_name="
				+ first_name + ", last_name=" + last_name + "]";
	}
	
	

}
