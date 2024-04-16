package com.rto.vehicle.info.challan.fuel.olxproject.model.Other;

import com.google.gson.annotations.SerializedName;

public class PriceRangeModel {

	@SerializedName("Message")
	private String message;

	@SerializedName("Data")
	private Data data;

	@SerializedName("StatusCode")
	private int statusCode;

	public String getMessage(){
		return message;
	}

	public Data getData(){
		return data;
	}

	public int getStatusCode(){
		return statusCode;
	}

	public static class Data{

		@SerializedName("minPrice")
		private int minPrice;

		@SerializedName("maxPrice")
		private int maxPrice;

		public int getMinPrice(){
			return minPrice;
		}

		public int getMaxPrice(){
			return maxPrice;
		}
	}
}