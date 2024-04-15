package com.rto.vehicle.info.challan.fuel.olxproject.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class MonthsModel {

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