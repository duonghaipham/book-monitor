package com.tyler.book_monitor.helpers;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;

public class DominantColor {

    private int color;
    private Bitmap bitmap;

    public DominantColor(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public int getDominantColor() {
        Bitmap newBitmap = Bitmap.createScaledBitmap(bitmap, 1, 1, true);

        color = newBitmap.getPixel(0, 0);
        newBitmap.recycle();

        return color;
    }

    public GradientDrawable getDominantColorGradient() {
        GradientDrawable gd = new GradientDrawable(
                GradientDrawable.Orientation.TOP_BOTTOM,
                new int[]{color, Color.WHITE}
        );

        return gd;
    }
}
