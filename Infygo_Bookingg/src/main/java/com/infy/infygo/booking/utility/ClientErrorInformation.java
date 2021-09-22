package com.infy.infygo.booking.utility;

public class ClientErrorInformation {

	int errorCode;
	String errorMsg="error";

	public void setErrorCode(int value) {
		errorCode = value;

	}

	public void setMessage(String msg) {
		errorMsg = msg;

	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public ClientErrorInformation(int badRequest, String errorMsg) {
		super();
		this.errorCode = badRequest;
		this.errorMsg = errorMsg;
	}

	public ClientErrorInformation() {
		super();
	}
}
