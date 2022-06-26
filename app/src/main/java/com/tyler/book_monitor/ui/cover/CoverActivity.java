package com.tyler.book_monitor.ui.cover;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.tyler.book_monitor.R;
import com.tyler.book_monitor.helpers.DominantColor;
import com.tyler.book_monitor.ui.base.BaseActivity;

public class CoverActivity extends BaseActivity implements CoverContract.View {

    private CoverContract.Presenter presenter;

    private LinearLayout llCover;
    private ImageView ivCover;

    private Button btnReadOffline;
    private Button btnReadOnline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cover);

        llCover = findViewById(R.id.ll_cover);
        ivCover = findViewById(R.id.iv_cover);
        btnReadOffline = findViewById(R.id.btn_read_offline);
        btnReadOnline = findViewById(R.id.btn_read_online);

        presenter = new CoverPresenter(this);

        Bitmap cover = ((BitmapDrawable) ivCover.getDrawable()).getBitmap();

        DominantColor dominantColor = new DominantColor(cover);
        int color = dominantColor.getDominantColor();
        GradientDrawable gd = dominantColor.getDominantColorGradient();

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(color);

        llCover.setBackground(gd);

        btnReadOnline.setOnClickListener(v -> {
            presenter.toChapterActivity(this);
        });
    }
}