package com.tyler.book_monitor.ui.all_books;

import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.tyler.book_monitor.R;
import com.tyler.book_monitor.ui.adapters.BookAdapter;
import com.tyler.book_monitor.data.models.Book;
import com.tyler.book_monitor.ui.base.BaseActivity;

import java.util.List;

public class AllBooksActivity extends BaseActivity implements AllBooksContract.View, BookAdapter.IBookClick {

    private AllBooksContract.Presenter presenter;

    private RecyclerView rvBooks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_books);

        rvBooks = findViewById(R.id.rv_books);

        presenter = new AllBooksPresenter(this, this);

        presenter.loadContent();
    }

    @Override
    public void onLoadContent(List<Book> books) {
        BookAdapter bookAdapter = new BookAdapter(this, books, this);

        rvBooks.setAdapter(bookAdapter);

        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(this);
        layoutManager.setFlexDirection(FlexDirection.ROW);
        layoutManager.setJustifyContent(JustifyContent.CENTER);
        layoutManager.setFlexWrap(FlexWrap.WRAP);

        rvBooks.setLayoutManager(layoutManager);
    }

    @Override
    public void onBookClick(int position) {
        presenter.toCoverActivity();
    }
}