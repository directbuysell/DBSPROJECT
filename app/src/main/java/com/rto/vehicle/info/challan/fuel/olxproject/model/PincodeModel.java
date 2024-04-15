package com.rto.vehicle.info.challan.fuel.olxproject.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class PincodeModel {

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

		@SerializedName("AreaName")
		private String areaName;

		@SerializedName("AreaId")
		private int areaId;

		@SerializedName("Pincode")
		private String pincode;

		public DataItem(String pincode2, int areaId2, String areaName2) {
			areaName = areaName2;
			areaId = areaId2;
			pincode = pincode2;
		}

		public String getAreaName(){
			return areaName;
		}

		public int getAreaId(){
			return areaId;
		}

		public String getPincode(){
			return pincode;
		}
	}
}