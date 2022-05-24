package com.tyler.book_monitor;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class CoverActivity extends AppCompatActivity {

    private LinearLayout llCover;
    private ImageView ivCover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cover);

        getSupportActionBar().hide();

        llCover = findViewById(R.id.ll_cover);
        ivCover = findViewById(R.id.iv_cover);

        Bitmap cover = ((BitmapDrawable)ivCover.getDrawable()).getBitmap();

        int dominantColor = getDominantColor(cover);

        GradientDrawable gd = new GradientDrawable(
                GradientDrawable.Orientation.TOP_BOTTOM,
                new int[] {dominantColor, Color.WHITE}
        );

        llCover.setBackground(gd);
    }

    public static int getDominantColor(Bitmap bitmap) {
        Bitmap newBitmap = Bitmap.createScaledBitmap(bitmap, 1, 1, true);
        final int color = newBitmap.getPixel(0, 0);
        newBitmap.recycle();
        return color;
    }
}