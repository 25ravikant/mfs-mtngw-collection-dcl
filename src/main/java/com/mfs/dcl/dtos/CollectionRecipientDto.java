package com.mfs.dcl.dtos;

/*
 * CollectionRecipientDto Class for Recipient
 *
 * @since 28-04-2022
 */

public class CollectionRecipientDto {
	
	private String first_name;
	
	private String last_name;
	
	private String business_short_code;

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

	public String getBusiness_short_code() {
		return business_short_code;
	}

	public void setBusiness_short_code(String business_short_code) {
		this.business_short_code = business_short_code;
	}

	//toString Method
	@Override
	public String toString() {
		return "C2BRecipientDto [first_name=" + first_name + ", last_name=" + last_name + ", business_short_code="
				+ business_short_code + "]";
	}
	


}
