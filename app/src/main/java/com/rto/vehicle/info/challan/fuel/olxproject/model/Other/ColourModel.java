package com.rto.vehicle.info.challan.fuel.olxproject.model.Other;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ColourModel {

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

		@SerializedName("displayName")
		private String displayName;

		@SerializedName("name")
		private String name;

		@SerializedName("value")
		private String value;

		public DataItem(String name1, String value1, String displayName1) {
			displayName = displayName1;
			name = name1;
			value = value1;
		}

		public String getDisplayName(){
			return displayName;
		}

		public String getName(){
			return name;
		}

		public String getValue(){
			return value;
		}
	}
}