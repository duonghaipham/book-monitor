package com.tyler.book_monitor.ui.author;

import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.tyler.book_monitor.R;
import com.tyler.book_monitor.adapters.BookAdapter;
import com.tyler.book_monitor.data.models.Book;
import com.tyler.book_monitor.helpers.IBookClick;
import com.tyler.book_monitor.ui.base.BaseActivity;

import java.util.List;

public class AuthorActivity extends BaseActivity implements AuthorContract.View, IBookClick {

    private AuthorContract.Presenter presenter;

    private RecyclerView rvAuthorBooks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author);

        rvAuthorBooks = findViewById(R.id.rv_author_books);

        presenter = new AuthorPresenter(this);

        presenter.loadContent();
    }

    @Override
    public void onLoadContent(List<Book> books) {
        BookAdapter bookAdapter = new BookAdapter(this, books, this);

        rvAuthorBooks.setAdapter(bookAdapter);

        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(this);
        layoutManager.setFlexDirection(FlexDirection.ROW);
        layoutManager.setJustifyContent(JustifyContent.CENTER);
        layoutManager.setFlexWrap(FlexWrap.WRAP);

        rvAuthorBooks.setLayoutManager(layoutManager);
    }

    @Override
    public void onBookClick(int position) {
        presenter.toCoverActivity(this);
    }
}