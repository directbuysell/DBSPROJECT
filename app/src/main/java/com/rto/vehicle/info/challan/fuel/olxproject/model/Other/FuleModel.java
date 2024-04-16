package com.rto.vehicle.info.challan.fuel.olxproject.model.Other;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class FuleModel {

	@SerializedName("Message")
	private String message;

	@SerializedName("Data")
	private List<String> data;

	@SerializedName("StatusCode")
	private int statusCode;

	public String getMessage(){
		return message;
	}

	public List<String> getData(){
		return data;
	}

	public int getStatusCode(){
		return statusCode;
	}
}