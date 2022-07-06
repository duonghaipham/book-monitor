package com.tyler.book_monitor.utils;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

public class ImageDownloader {
    private static ImageDownloader mInstance;

    private ImageDownloader() {}

    public static ImageDownloader getInstance() {
        if (mInstance == null) {
            mInstance = new ImageDownloader();
        }
        return mInstance;
    }

    public String saveToInternalStorage(Bitmap bitmapImage, Context context){
        ContextWrapper cw = new ContextWrapper(context);

        File directory = cw.getDir("images", Context.MODE_PRIVATE);
        File file = new File(directory, UUID.randomUUID().toString() + ".jpg");

        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            bitmapImage.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return file.getAbsolutePath();
    }
}
