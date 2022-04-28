package com.mfs.dcl.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

/*
 * ThirdPartyWalletDto class
 * @since 28-04-2022
 */


@JsonInclude(JsonInclude.Include.NON_NULL)
public class ThirdPartyWalletDto {

	private String merchant_id;

	public String getMerchant_id() {
		return merchant_id;
	}

	public void setMerchant_id(String merchant_id) {
		this.merchant_id = merchant_id;
	}

	//toString Method
	@Override
	public String toString() {
		return "ThirdPartyWalletDto [merchant_id=" + merchant_id + "]";
	}

}
