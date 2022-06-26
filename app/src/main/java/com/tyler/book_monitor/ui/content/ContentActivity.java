package com.tyler.book_monitor.ui.content;

import android.annotation.SuppressLint;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.tyler.book_monitor.R;
import com.tyler.book_monitor.data.models.Chapter;
import com.tyler.book_monitor.helpers.DominantColor;
import com.tyler.book_monitor.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class ContentActivity extends BaseActivity implements ContentContract.View {

    private ContentContract.Presenter presenter;
    
    private LinearLayout llCover;
    private BottomNavigationView bnvAdjustment;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        
        presenter = new ContentPresenter(this);

        llCover = findViewById(R.id.ll_cover);

        bnvAdjustment = findViewById(R.id.bnv_adjustment);

        bnvAdjustment.setOnItemReselectedListener(v -> {
            switch (v.getItemId()) {
                case R.id.bnvi_chapter:
                    presenter.showChapterChoices();
                    break;
                case R.id.bnvi_text:
//                    presenter.adjustContrast();
                    break;
            }
        });
        
        presenter.loadContent(this);
    }

    @Override
    public void onLoadContent(int color, String chapter, String content) {
        GradientDrawable gd = DominantColor.getDominantColorGradient(color);

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(color);

        llCover.setBackground(gd);

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
}