package com.tyler.book_monitor.ui.cover;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.tyler.book_monitor.R;
import com.tyler.book_monitor.data.models.Book;
import com.tyler.book_monitor.helpers.DominantColor;
import com.tyler.book_monitor.ui.base.BaseActivity;

public class CoverActivity extends BaseActivity implements CoverContract.View {

    private CoverContract.Presenter presenter;

    private String bookId;

    private LinearLayout llCover;
    private ImageView ivCover;
    private TextView tvBookName;
    private TextView tvBookAuthor;
    private TextView tvBookIntroduction;
    private Button btnToggleDownload;
    private Button btnReadOnline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cover);

        llCover = findViewById(R.id.ll_cover);
        ivCover = findViewById(R.id.iv_cover);
        tvBookName = findViewById(R.id.tv_book_name);
        tvBookAuthor = findViewById(R.id.tv_book_author);
        tvBookIntroduction = findViewById(R.id.tv_book_introduction);
        btnToggleDownload= findViewById(R.id.btn_toggle_download);
        btnReadOnline = findViewById(R.id.btn_read_online);

        presenter = new CoverPresenter(this, this);

        presenter.loadContent();

        btnToggleDownload.setOnClickListener(v -> {
            presenter.toggleDownload();
        });

        btnReadOnline.setOnClickListener(v -> {
            presenter.toChapterActivity(bookId);
        });
    }

    @Override
    public void onLoadContent(int themeMode, Book book, boolean isDownloaded) {
        Target target = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                ivCover.setImageBitmap(bitmap);

                if (themeMode == 0) { // light mode
                    DominantColor dominantColor = new DominantColor(bitmap);
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

        bookId = book.getId();

        tvBookName.setText(book.getTitle());
        tvBookAuthor.setText(book.getAuthor());
        tvBookIntroduction.setText(book.getIntroduction().replace("\\n", "\n"));

        setBtnToggleDownloadText(isDownloaded);
    }

    @Override
    public void toggleDownload(boolean isDownloaded) {
        setBtnToggleDownloadText(isDownloaded);
    }

    // for not repeated block of code
    private void setBtnToggleDownloadText(boolean isDownloaded) {
        if (isDownloaded)
            btnToggleDownload.setText(R.string.delete_download);
        else
            btnToggleDownload.setText(R.string.download);
    }
}