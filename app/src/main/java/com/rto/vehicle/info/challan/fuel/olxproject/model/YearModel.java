package com.rto.vehicle.info.challan.fuel.olxproject.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class YearModel {

	@SerializedName("Message")
	private String message;

	@SerializedName("Data")
	private List<Integer> data;

	@SerializedName("StatusCode")
	private int statusCode;

	public String getMessage(){
		return message;
	}

	public List<Integer> getData(){
		return data;
	}

	public int getStatusCode(){
		return statusCode;
	}
}