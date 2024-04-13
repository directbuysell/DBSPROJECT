package com.rto.vehicle.info.challan.fuel.olxproject.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class CityModel {

	@SerializedName("Message")
	private String message;

	@SerializedName("Data")
	private List<DataItem> data;

	@SerializedName("StatusCode")
	private int statusCode;

	public String getMessage(){
		return message;
	}

	public List<DataItem> getData(){
		return data;
	}

	public int getStatusCode(){
		return statusCode;
	}

	public static class DataItem{

		@SerializedName("cityName")
		private String cityName;

		@SerializedName("stateName")
		private String stateName;

		@SerializedName("cityId")
		private int cityId;

		public DataItem(int cityId2, String cityName2, String stateName2) {
			this.cityId = cityId2;
			this.cityName = cityName2;
			this.stateName = stateName2;
		}

		public String getCityName(){
			return cityName;
		}

		public String getStateName(){
			return stateName;
		}

		public int getCityId(){
			return cityId;
		}
	}
}