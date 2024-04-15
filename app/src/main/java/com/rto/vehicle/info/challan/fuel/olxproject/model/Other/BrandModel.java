package com.rto.vehicle.info.challan.fuel.olxproject.model.Other;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class BrandModel {

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

	public static class MakesItem{

		@SerializedName("CompName")
		private String compName;

		@SerializedName("CompId")
		private int compId;

		public String getCompName(){
			return compName;
		}

		public int getCompId(){
			return compId;
		}
	}

	public static class DataItem{

		@SerializedName("GroupName")
		private String groupName;

		@SerializedName("Makes")
		private List<MakesItem> makes;

		public String getGroupName(){
			return groupName;
		}

		public List<MakesItem> getMakes(){
			return makes;
		}
	}
}