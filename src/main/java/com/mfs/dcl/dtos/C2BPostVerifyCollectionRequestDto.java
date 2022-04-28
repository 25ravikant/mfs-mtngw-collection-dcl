package com.mfs.dcl.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;


/* C2BPostVerifyCollectionRequestDto class
 *
 * since 28-04-2022
 */

//Json Annotations for Null Fields
@JsonInclude(JsonInclude.Include.NON_NULL)
public class C2BPostVerifyCollectionRequestDto {
	
	private CollectionDto collection;
	
	private CollectionRecipientDto recipient;
	
	private CollectionSenderDto sender;
	
	private ThirdPartyWalletDto third_party_wallet;
	
	private String timestamp;


	public CollectionDto getCollection() {

		return collection;
	}

	public void setCollection(CollectionDto collection)
	{
		this.collection = collection;
	}

	public CollectionRecipientDto getRecipient() {

		return recipient;
	}

	public void setRecipient(CollectionRecipientDto recipient) {
		this.recipient = recipient;
	}

	public CollectionSenderDto getSender() {
		return sender;
	}

	public void setSender(CollectionSenderDto sender) {
		this.sender = sender;
	}

	public ThirdPartyWalletDto getThird_party_wallet() {
		return third_party_wallet;
	}

	public void setThird_party_wallet(ThirdPartyWalletDto third_party_wallet) {
		this.third_party_wallet = third_party_wallet;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	//toString Method
	@Override
	public String toString() {
		return "C2BPostVerifyCollectionRequestDto [collection=" + collection + ", recipient=" + recipient + ", sender="
				+ sender + ", third_party_wallet=" + third_party_wallet + ", timestamp=" + timestamp + "]";
	}
	
	

}
