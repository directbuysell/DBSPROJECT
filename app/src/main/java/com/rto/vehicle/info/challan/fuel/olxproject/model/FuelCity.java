package com.rto.vehicle.info.challan.fuel.olxproject.model;

import java.io.Serializable;

public class FuelCity implements Serializable {
    private String cityName;
    private int fuelCityId;

    
    private int id;
    private int stateId;
    private String stateName;

    public int getId() {
        return id;
    }

    public int getStateId() {
        return stateId;
    }

    public String getStateName() {
        return stateName;
    }

    public String getCityName() {
        return cityName;
    }

    public int getFuelCityId() {
        return fuelCityId;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FuelCity)) {
            return false;
        }
        FuelCity fuelCity = (FuelCity) obj;
        if (getId() != fuelCity.getId() || getStateId() != fuelCity.getStateId()) {
            return false;
        }
        return getCityName().equals(fuelCity.getCityName());
    }

    public int hashCode() {
        return (((getId() * 31) + getStateId()) * 31) + getCityName().hashCode();
    }

    public String toString() {
        return "FuelCity{id=" + id + ", stateId=" + stateId + ", stateName='" + stateName + "', cityName='" + cityName + "', fuelCityName='" + fuelCityId + "'}";
    }
}