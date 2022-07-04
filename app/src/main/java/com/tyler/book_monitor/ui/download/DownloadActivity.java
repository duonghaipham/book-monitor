package com.tyler.book_monitor.ui.download;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tyler.book_monitor.R;
import com.tyler.book_monitor.data.models.Book;
import com.tyler.book_monitor.ui.adapters.PersonalBookAdapter;
import com.tyler.book_monitor.ui.base.BaseActivity;

import java.util.List;

public class DownloadActivity extends BaseActivity implements DownloadContract.View, PersonalBookAdapter.IPersonalBookClick {

    private DownloadPresenter presenter;

    private RecyclerView rvDownloadedBooks;
    private TextView tvNoDownloadedBooks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);

        rvDownloadedBooks = findViewById(R.id.rv_downloaded_books);
        tvNoDownloadedBooks = findViewById(R.id.tv_no_downloaded_books);

        presenter = new DownloadPresenter(this, this);

        presenter.loadContent();
    }

    @Override
    public void onLoadContent(List<Book> downloadedBooks) {
        if (downloadedBooks.size() > 0) {
            rvDownloadedBooks.setVisibility(View.VISIBLE);
            tvNoDownloadedBooks.setVisibility(View.GONE);

            PersonalBookAdapter adapter = new PersonalBookAdapter(this, downloadedBooks, this);

            rvDownloadedBooks.setAdapter(adapter);
            rvDownloadedBooks.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        } else {
            rvDownloadedBooks.setVisibility(View.GONE);
            tvNoDownloadedBooks.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onRemoveClick(String bookId) {
        presenter.removeBookFromDownload(bookId);
    }

    @Override
    public void onViewClick(String bookId) {
        presenter.toCoverActivity(bookId);
    }
}