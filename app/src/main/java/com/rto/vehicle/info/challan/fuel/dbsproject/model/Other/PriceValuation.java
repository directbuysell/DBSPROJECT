package com.rto.vehicle.info.challan.fuel.dbsproject.model.Other;

import com.google.gson.annotations.SerializedName;

public class PriceValuation {

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

		@SerializedName("price")
		private int price;

		@SerializedName("fair")
		private int fair;

		@SerializedName("good")
		private int good;

		public int getPrice(){
			return price;
		}

		public int getFair(){
			return fair;
		}

		public int getGood(){
			return good;
		}
	}
}