package com.rto.vehicle.info.challan.fuel.dbsproject.comman;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class MultipartUtil {

    public static MultipartBody.Part createPartFromFileUri(Context context, Uri fileUri, String partName) {
        File file = new File(fileUri.getPath());
        RequestBody requestBody = RequestBody.create(MediaType.parse(getMimeType(context, fileUri)), file);
        return MultipartBody.Part.createFormData(partName, file.getName(), requestBody);
    }

    private static String getMimeType(Context context, Uri uri) {
        ContentResolver contentResolver = context.getContentResolver();
        String mimeType = contentResolver.getType(uri);
        return mimeType;
    }



}