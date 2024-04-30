package com.rto.vehicle.info.challan.fuel.dbsproject.comman;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    public static List<File> getDrawableFiles(Context context, int[] drawableIds) {
        List<File> drawableFiles = new ArrayList<>();

        for (int drawableId : drawableIds) {
            String drawableName = context.getResources().getResourceEntryName(drawableId);
            Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), drawableId);

            File file = saveBitmapToFile(context, bitmap, drawableName);
            if (file != null) {
                drawableFiles.add(file);
            }
        }

        return drawableFiles;
    }

    private static File saveBitmapToFile(Context context, Bitmap bitmap, String fileName) {
        File directory = new File(context.getFilesDir(), "drawable_files");
        if (!directory.exists()) {
            directory.mkdirs();
        }

        File file = new File(directory, fileName + ".png");
        FileOutputStream outputStream = null;

        try {
            outputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
            return file;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}