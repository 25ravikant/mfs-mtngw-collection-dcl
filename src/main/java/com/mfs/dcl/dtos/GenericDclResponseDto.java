package com.mfs.dcl.dtos;
import java.util.Map;

/*
 * GenericDclResponseDto Class for Response
 *
 */

public class GenericDclResponseDto {

	 private String code;    //its for code e.g=100,256
	 private String status;  //its status like success or fail
	 private String message;
	 private Map<String,String> additionalParam;		

	 //Default Constructor
	 public GenericDclResponseDto(){
		 
	 }

	 //Parameterize Constructor
	public GenericDclResponseDto(String code, String status, String message, Map<String, String> additionalParam) {
		super();
		this.code = code;
		this.status = status;
		this.message = message;
		this.additionalParam = additionalParam;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Map<String, String> getAdditionalParam() {
		return additionalParam;
	}

	public void setAdditionalParam(Map<String, String> additionalParam) {
		this.additionalParam = additionalParam;
	}
	 
	
}
