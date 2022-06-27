package com.tyler.book_monitor.ui.chapter;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tyler.book_monitor.R;
import com.tyler.book_monitor.adapters.ChapterAdapter;
import com.tyler.book_monitor.data.models.Chapter;
import com.tyler.book_monitor.helpers.DominantColor;
import com.tyler.book_monitor.helpers.IChapterClick;
import com.tyler.book_monitor.ui.base.BaseActivity;

import java.util.List;

public class ChapterActivity extends BaseActivity implements ChapterContract.View, IChapterClick {

    private ChapterContract.Presenter presenter;

    private int color;

    private LinearLayout llCover;
    private ImageView ivCover;
    private RecyclerView rvChapters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter);

        llCover = findViewById(R.id.ll_cover);
        ivCover = findViewById(R.id.iv_cover);
        rvChapters = findViewById(R.id.rv_chapters);

        presenter = new ChapterPresenter(this);

        presenter.loadContent();

        Bitmap cover = ((BitmapDrawable) ivCover.getDrawable()).getBitmap();

        DominantColor dominantColor = new DominantColor(cover);
        color = dominantColor.getDominantColor();
        GradientDrawable gd = dominantColor.getDominantColorGradient();

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(color);

        llCover.setBackground(gd);
    }

    @Override
    public void onLoadContent(List<Chapter> chapters) {
        ChapterAdapter adapter = new ChapterAdapter(this, chapters, this);

        rvChapters.setAdapter(adapter);
        rvChapters.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void onChapterClick(int position) {
        presenter.toContentActivity(this, color, position);
    }
}