package com.rto.vehicle.info.challan.fuel.olxproject.comman;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

public class SharePrefs {

    public static SharedPreferences.Editor editor;
    public static SharedPreferences sharedPreferences;


    public static void init() {
        SharedPreferences sharedPreferences2 = GlobalAcesss.getContext().getSharedPreferences("olx", 0);
        sharedPreferences = sharedPreferences2;
        editor = sharedPreferences2.edit();
    }

    public static void update() {
        sharedPreferences = GlobalAcesss.getContext().getSharedPreferences(
                "olx", 0);
    }


    public static void editor(String str, String str2) {
        editor.putString(str, str2);
        editor.apply();
    }

    public static void editor(String str, int i) {
        editor.putInt(str, i);
        editor.apply();
    }

    public static void editor(String str, boolean z) {
        editor.putBoolean(str, z);
        editor.apply();
    }

    public static void clear() {
        editor.clear();
        editor.apply();
    }


    public static boolean getRating() {
        return sharedPreferences.getBoolean("israteus", false);
    }

    public static int getAdCount() {
        return sharedPreferences.getInt("AdCount", 1);
    }

    @SuppressLint("LongLogTag")
    public static boolean getIsPermissionGranted() {

        // Log.e("===getIsPermissionGranted", sharedPreferences.getBoolean("isPermissionGranted", false) + "");
        return sharedPreferences.getBoolean("isPermissionGranted", false);
    }

    public static String getName() {
        return sharedPreferences.getString("name", "");
    }


    public static String getCityName() {
        return sharedPreferences.getString("cityname", "");
    }

    public static String getCitypincode() {
        return sharedPreferences.getString("cityPincode", "");
    }

    public static int getCityid() {
        return sharedPreferences.getInt("Cityid", 1);
    }


    public static int getRegistrationyear() {
        return sharedPreferences.getInt("Regyear", 2024);
    }


    public static int getcarbrand() {
        return sharedPreferences.getInt("carbrand", 1);
    }

    public static int getcarModel_ID() {
        return sharedPreferences.getInt("carmodel_ID", 1);
    }

    public static int getvaeiantID() {
        return sharedPreferences.getInt("vaeiantID", 1);
    }

    public static String getRegistrationMonths() {
        return sharedPreferences.getString("RegMonths", "Jan");
    }

    public static String getCarvaeiant() {
        return sharedPreferences.getString("Carvaeiant", "");
    }

    public static String getfuletype() {
        return sharedPreferences.getString("FuleType", "");
    }

    public static String getTransmission() {
        return sharedPreferences.getString("Transmission", "");
    }

    public static String getCarModel_Name() {
        return sharedPreferences.getString("carmodel_name", "");
    }

    public static String getOwnertype() {
        return sharedPreferences.getString("OwnerType", "1");
    }

    public static String getkm() {
        return sharedPreferences.getString("KM", "100000");
    }


    public static String getFather() {
        return sharedPreferences.getString("father", "");
    }

    public static String getMiddle() {
        return sharedPreferences.getString("middle", "");
    }

    public static String getNumber() {
        return sharedPreferences.getString("number", "");
    }

    public static String getDateOfBirth() {
        return sharedPreferences.getString("dateOfBirth", "");
    }

    public static String gettoken() {
        return sharedPreferences.getString("Token", "");
    }

    public static String getGender() {
        return sharedPreferences.getString("gender", "");
    }

    public static String getpanNumber() {
        return sharedPreferences.getString("panNumber", "");
    }

    public static String getEmail() {
        return sharedPreferences.getString("email", "");
    }

    public static boolean getlogin() {
        return sharedPreferences.getBoolean("login", false);
    }


}
