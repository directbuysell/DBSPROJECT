package com.rto.vehicle.info.challan.fuel.olxproject.model.Other;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class CarModel {

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

		@SerializedName("ModelName")
		private String modelName;

		@SerializedName("ModelId")
		private int modelId;

		public DataItem(int modelId2, String modelName2) {
			modelName = modelName2;
			modelId = modelId2;
		}

        public String getModelName(){
			return modelName;
		}

		public int getModelId(){
			return modelId;
		}
	}


}