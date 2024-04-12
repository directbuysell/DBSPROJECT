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


    public static String getMemberName() {
        return sharedPreferences.getString("memberName", "");
    }

    public static String getTotalPF() {
        return sharedPreferences.getString("totalPf", "");
    }

    public static String getEmployerContribution() {
        return sharedPreferences.getString("employerContribution", "");
    }

    public static String getlastUpdated() {
        return sharedPreferences.getString("lastUpdated", "");
    }

    public static String getpfPulldate() {
        return sharedPreferences.getString("pfPulldate", "");
    }

    public static String getlatestPfContribution() {
        return sharedPreferences.getString("latestPfContribution", "");
    }

    public static String getemployerContributionLatest() {
        return sharedPreferences.getString("employerContributionLatest", "");
    }

    public static String getStatusMsg() {
        return sharedPreferences.getString("StatusMsg", "");
    }

    public static String getpensionShareLatest() {
        return sharedPreferences.getString("pensionShareLatest", "");
    }

    public static String getemployeeContribution() {
        return sharedPreferences.getString("employeeContribution", "");
    }

    public static String getlatestDepositDate() {
        return sharedPreferences.getString("latestDepositDate", "");
    }

    public static String getcompanyName() {
        return sharedPreferences.getString("companyName", "");
    }

    public static String getnsdlRetryEnabled() {
        return sharedPreferences.getString("nsdlRetryEnabled", "");
    }

    public static String getpanStatus() {
        return sharedPreferences.getString("panStatus", "");
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

    public static boolean getIsProcess2Done() {
        return sharedPreferences.getBoolean("isProcess2Done", false);
    }

    public static boolean getIsProcess3Done() {
        return sharedPreferences.getBoolean("isProcess3Done", false);
    }

    public static Bitmap getSelfie() {
        byte[] decode = Base64.decode(sharedPreferences.getString("selfie", ""), Base64.NO_PADDING | Base64.URL_SAFE | Base64.NO_WRAP);
        return BitmapFactory.decodeByteArray(decode, 0, decode.length);
    }

    public static boolean getIntro() {
        return sharedPreferences.getBoolean("intro", false);
    }


    public static boolean getLanguage() {
        return sharedPreferences.getBoolean("Language", false);
    }


    public static boolean getPanCheck() {
        return sharedPreferences.getBoolean("pancheck", false);
    }

    public static String getCountryLang() {
        return sharedPreferences.getString("CountryLang", "");
    }

    public static int getLangPos() {
        return sharedPreferences.getInt("LangPos", 1);
    }


    public static boolean getPermission() {
        return sharedPreferences.getBoolean("Permission", false);
    }

    public static String getpriorityTag() {
        return sharedPreferences.getString("Tag", "google");


    }

    public static String getprivacyPolicy() {
        return sharedPreferences.getString("Policy", "www.google.com");
    }


    public static int getclick() {
        return sharedPreferences.getInt("click", 1);


    }
}
