package com.rto.vehicle.info.challan.fuel.olxproject.model.Other;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class VariantModel {

    @SerializedName("Message")
    private String message;

    @SerializedName("Data")
    private List<DataItem> data;

    @SerializedName("StatusCode")
    private int statusCode;

    public String getMessage() {
        return message;
    }

    public List<DataItem> getData() {
        return data;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public static class DataItem {

        @SerializedName("Transmission")
        private String transmission;

        @SerializedName("ID")
        private int iD;

        @SerializedName("FuelType")
        private String fuelType;

        @SerializedName("Name")
        private String name;

        public DataItem(int id2, String name2, String fuelType2, String transmission2) {
            iD = id2;
            name = name2;
            fuelType = fuelType2;
            transmission = transmission2;

        }

        public String getTransmission() {
            return transmission;
        }

        public int getID() {
            return iD;
        }

        public String getFuelType() {
            return fuelType;
        }

        public String getName() {
            return name;
        }
    }
}