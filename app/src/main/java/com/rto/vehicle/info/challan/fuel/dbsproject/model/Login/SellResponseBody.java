package com.rto.vehicle.info.challan.fuel.dbsproject.model.Login;

import com.google.gson.annotations.SerializedName;

public class SellResponseBody {

	@SerializedName("Message")
	private String message;

	@SerializedName("StatusText")
	private String statusText;

	@SerializedName("StatusCode")
	private int statusCode;

	public String getMessage(){
		return message;
	}

	public String getStatusText(){
		return statusText;
	}

	public int getStatusCode(){
		return statusCode;
	}
}