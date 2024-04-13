package com.rto.vehicle.info.challan.fuel.olxproject.API;

import com.rto.vehicle.info.challan.fuel.olxproject.model.CityModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {


    @GET("/api/CarConfig/cities")
    Call<CityModel> searchCities(@Query("city") String query);







}
