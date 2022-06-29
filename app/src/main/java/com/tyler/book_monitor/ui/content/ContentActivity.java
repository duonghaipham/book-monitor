package com.tyler.book_monitor.ui.content;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.tyler.book_monitor.R;
import com.tyler.book_monitor.data.models.SettingContent;
import com.tyler.book_monitor.helpers.DominantColor;
import com.tyler.book_monitor.helpers.OnSwipeTouchListener;
import com.tyler.book_monitor.ui.base.BaseActivity;

import java.util.ArrayList;

public class ContentActivity extends BaseActivity
        implements ContentContract.View, ChapterChoicesFragment.IChapterChoice, SettingsContentFragment.ISettingsContent {

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
                presenter.jumpNextChapter(ContentActivity.this);
            }

            public void onSwipeRight() {
                presenter.jumpPreviousChapter(ContentActivity.this);
            }
        });

        ibPrev.setOnClickListener(v -> {
            presenter.jumpPreviousChapter(this);
        });

        ibNext.setOnClickListener(v -> {
            presenter.jumpNextChapter(this);
        });

        bnvAdjustment.setOnItemSelectedListener(v -> {
            switch (v.getItemId()) {
                case R.id.bnvi_chapter:
                    presenter.showChapterChoices();
                    return true;
                case R.id.bnvi_settings:
                    presenter.showSettings(this);
                    return true;
            }

            return false;
        });

        presenter.initialize(this);
        presenter.loadContent(this);
    }

    @Override
    public void onInitialize(int themeMode, int color) {
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        if (themeMode == 1) {
            window.setStatusBarColor(Color.parseColor("#242527"));
        }
        else {
            GradientDrawable gd = DominantColor.getDominantColorGradient(color);
            llCover.setBackground(gd);
        }
    }

    @Override
    public void onLoadContent(SettingContent setting, String chapter, String content) {
        Bundle bundle = new Bundle();
        bundle.putString("chapter", chapter);
        bundle.putString("content", content);
        bundle.putInt("font", setting.getFont());
        bundle.putInt("fontSize", setting.getFontSize());

        ibPrev.setVisibility(setting.getNavigation() ? View.VISIBLE : View.GONE);
        ibNext.setVisibility(setting.getNavigation() ? View.VISIBLE : View.GONE);

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
    public void onShowSettings(SettingContent setting) {
        SettingsContentFragment fragment = new SettingsContentFragment();

        Bundle bundle = new Bundle();
        bundle.putInt("font", setting.getFont());
        bundle.putInt("fontSize", setting.getFontSize());
        bundle.putBoolean("navigation", setting.getNavigation());

        fragment.setArguments(bundle);
        fragment.show(getSupportFragmentManager(), "settings");
    }

    @Override
    public void onSaveSettings(SettingContent setting) {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fl_content);

        String[] fonts = getResources().getStringArray(R.array.fonts);

        TextView tvContent = fragment.getView().findViewById(R.id.tv_content);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "font/" + fonts[setting.getFont()] + ".ttf");
        tvContent.setTypeface(typeface);
        tvContent.setTextSize(setting.getFontSize());

        ibPrev.setVisibility(setting.getNavigation() ? View.VISIBLE : View.GONE);
        ibNext.setVisibility(setting.getNavigation() ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onDataPass(String chapterName, int chapterNumber) {
        presenter.jumpToChapter(this, chapterName, chapterNumber);
    }

    @Override
    public void onDataPass(SettingContent setting) {
        presenter.saveSettings(this, setting);
    }
}