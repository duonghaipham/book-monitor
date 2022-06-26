package com.tyler.book_monitor.ui.content;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.tyler.book_monitor.R;
import com.tyler.book_monitor.helpers.DominantColor;

public class ContentActivity extends AppCompatActivity {

    private LinearLayout llCover;
    private ImageView ivCover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        getSupportActionBar().hide();

        llCover = findViewById(R.id.ll_cover);
        ivCover = findViewById(R.id.iv_cover);

        Bitmap cover = ((BitmapDrawable) ivCover.getDrawable()).getBitmap();

        DominantColor dominantColor = new DominantColor(cover);
        int color = dominantColor.getDominantColor();
        GradientDrawable gd = dominantColor.getDominantColorGradient();

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(color);

        llCover.setBackground(gd);
    }
}