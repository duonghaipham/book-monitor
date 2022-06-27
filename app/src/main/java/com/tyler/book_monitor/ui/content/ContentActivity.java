package com.tyler.book_monitor.ui.content;

import android.annotation.SuppressLint;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.tyler.book_monitor.R;
import com.tyler.book_monitor.data.models.Chapter;
import com.tyler.book_monitor.helpers.DominantColor;
import com.tyler.book_monitor.helpers.OnSwipeTouchListener;
import com.tyler.book_monitor.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class ContentActivity extends BaseActivity implements ContentContract.View, ChapterChoicesFragment.IChapterChoice {

    private ContentContract.Presenter presenter;

    private ConstraintLayout clContent;
    private LinearLayout llCover;
    private ImageButton ibPrev;
    private ImageButton ibNext;
    private BottomNavigationView bnvAdjustment;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        
        presenter = new ContentPresenter(this);

        clContent = findViewById(R.id.cl_content);
        llCover = findViewById(R.id.ll_cover);
        ibPrev = findViewById(R.id.ib_prev);
        ibNext = findViewById(R.id.ib_next);
        bnvAdjustment = findViewById(R.id.bnv_adjustment);

        clContent.setOnTouchListener(new OnSwipeTouchListener(this) {
            public void onSwipeLeft() {
                presenter.jumpNextChapter();
            }

            public void onSwipeRight() {
                presenter.jumpPreviousChapter();
            }
        });

        ibPrev.setOnClickListener(v -> {
            presenter.jumpPreviousChapter();
        });

        ibNext.setOnClickListener(v -> {
            presenter.jumpNextChapter();
        });

        bnvAdjustment.setOnItemSelectedListener(v -> {
            switch (v.getItemId()) {
                case R.id.bnvi_chapter:
                    presenter.showChapterChoices();
                    return true;
                case R.id.bnvi_settings:
                    presenter.showSettings();
                    return true;
            }

            return false;
        });

        presenter.initialize(this);
        presenter.loadContent();
    }

    @Override
    public void onInitialize(int color) {
        GradientDrawable gd = DominantColor.getDominantColorGradient(color);

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(color);

        llCover.setBackground(gd);
    }

    @Override
    public void onLoadContent(String chapter, String content) {
        Bundle bundle = new Bundle();
        bundle.putString("chapter", chapter);
        bundle.putString("content", content);

        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fl_content, ContentFragment.class, bundle)
                .commit();
    }

    @Override
    public void onShowChapterChoices(ArrayList<String> chapterChoices) {
        ChapterChoicesFragment fragment = new ChapterChoicesFragment();

        Bundle bundle = new Bundle();
        bundle.putStringArrayList("chapterChoices", chapterChoices);

        fragment.setArguments(bundle);
        fragment.show(getSupportFragmentManager(), "chapter_choices");
    }

    @Override
    public void onShowSettings() {
        SettingsContentFragment fragment = new SettingsContentFragment();

        fragment.show(getSupportFragmentManager(), "settings");
    }

    @Override
    public void onDataPass(String chapterName, int chapterNumber) {
        presenter.jumpToChapter(chapterName, chapterNumber);
    }
}