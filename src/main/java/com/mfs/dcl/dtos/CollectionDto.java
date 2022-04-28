package com.mfs.dcl.dtos;

/*
 * CollectionDto Class
 * @since 28-04-2022
 */


public class CollectionDto {
	
	private String amount;
	
	private String currency_code;
	
	private String country_code;
	
	private String third_party_transaction_id;
	
	private String collection_request_id;
	
	private String reference;

	

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getCurrency_code() {
		return currency_code;
	}

	public void setCurrency_code(String currency_code) {
		this.currency_code = currency_code;
	}

	public String getCountry_code() {
		return country_code;
	}

	public void setCountry_code(String country_code) {
		this.country_code = country_code;
	}

	public String getThird_party_transaction_id() {
		return third_party_transaction_id;
	}

	public void setThird_party_transaction_id(String third_party_transaction_id) {
		this.third_party_transaction_id = third_party_transaction_id;
	}

	public String getCollection_request_id() {
		return collection_request_id;
	}

	public void setCollection_request_id(String collection_request_id) {
		this.collection_request_id = collection_request_id;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	//toString Method
	@Override
	public String toString() {
		return "CollectionDto [amount=" + amount + ", currency_code=" + currency_code + ", country_code=" + country_code
				+ ", third_party_transaction_id=" + third_party_transaction_id + ", collection_request_id="
				+ collection_request_id + ", reference=" + reference + "]";
	}

	

	

	

}