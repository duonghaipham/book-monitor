package com.tyler.book_monitor.ui.chapter;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.tyler.book_monitor.R;
import com.tyler.book_monitor.ui.adapters.ChapterAdapter;
import com.tyler.book_monitor.data.models.Book;
import com.tyler.book_monitor.data.models.Chapter;
import com.tyler.book_monitor.helpers.DominantColor;
import com.tyler.book_monitor.ui.base.BaseActivity;

import java.util.List;

public class ChapterActivity extends BaseActivity implements ChapterContract.View, ChapterAdapter.IChapterClick {

    private ChapterContract.Presenter presenter;

    private int color;

    private LinearLayout llCover;
    private ImageView ivCover;
    private TextView tvBookName;
    private RecyclerView rvChapters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter);

        llCover = findViewById(R.id.ll_cover);
        ivCover = findViewById(R.id.iv_cover);
        tvBookName = findViewById(R.id.tv_book_name);
        rvChapters = findViewById(R.id.rv_chapters);

        presenter = new ChapterPresenter(this, this);

        presenter.loadContent();
    }

    @Override
    public void onLoadContent(int themeMode, Book book, List<Chapter> chapters) {
        Target target = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                ivCover.setImageBitmap(bitmap);

                if (themeMode == 0) {
                    DominantColor dominantColor = new DominantColor(bitmap);
                    color = dominantColor.getDominantColor();

                    GradientDrawable gd = dominantColor.getDominantColorGradient();
                    llCover.setBackground(gd);
                }
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) { }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) { }
        };

        Picasso.get().load(book.getCover()).resize(480, 720).into(target);
        
        tvBookName.setText(book.getTitle());

        ChapterAdapter adapter = new ChapterAdapter(this, chapters, this);
        rvChapters.setAdapter(adapter);
        rvChapters.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void onChapterClick(int position) {
        presenter.toContentActivity(color, position);
    }
}