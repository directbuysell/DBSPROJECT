package com.rto.vehicle.info.challan.fuel.olxproject.API;

import com.rto.vehicle.info.challan.fuel.olxproject.model.CityModel;
import com.rto.vehicle.info.challan.fuel.olxproject.model.MonthsModel;
import com.rto.vehicle.info.challan.fuel.olxproject.model.Other.BrandModel;
import com.rto.vehicle.info.challan.fuel.olxproject.model.Other.CarModel;
import com.rto.vehicle.info.challan.fuel.olxproject.model.Other.VariantModel;
import com.rto.vehicle.info.challan.fuel.olxproject.model.OwnersModel;
import com.rto.vehicle.info.challan.fuel.olxproject.model.PincodeModel;
import com.rto.vehicle.info.challan.fuel.olxproject.model.YearModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {


    @GET("/api/CarConfig/cities")
    Call<CityModel> searchCities(@Query("city") String query);


    @GET("/api/CarConfig/pincode")
    Call<PincodeModel> searchCitiespinCode(@Query("CityId") int query);

    @GET("/api/CarConfig/years")
    Call<YearModel> registrationyear();

    @GET("/api/CarConfig/months")
    Call<MonthsModel> registrationmonths();

    @GET("/api/CarConfig/owners")
    Call<OwnersModel> registrationOwners();


    @GET("/api/CarConfig/makes")
    Call<BrandModel> carBrand();

    @GET("/api/CarConfig/models")
    Call<CarModel> searchcarModel(@Query("MakeId") int i, @Query("Year") int query);

    @GET("/api/CarConfig/versions")
    Call<VariantModel> searchVariantModel(@Query("ModelId") int i, @Query("Year") int query);


}
