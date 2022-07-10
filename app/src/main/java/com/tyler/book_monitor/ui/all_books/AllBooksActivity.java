package com.tyler.book_monitor.ui.all_books;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.tyler.book_monitor.R;
import com.tyler.book_monitor.data.models.Book;
import com.tyler.book_monitor.ui.adapters.PaginationAdapter;
import com.tyler.book_monitor.ui.adapters.PagingBookAdapter;
import com.tyler.book_monitor.ui.base.BaseActivity;

import java.util.List;

public class AllBooksActivity extends BaseActivity implements AllBooksContract.View, PagingBookAdapter.IPagingBookClick, PaginationAdapter.IPaginationClick {

    private AllBooksContract.Presenter presenter;

    private RecyclerView rvBooks;
    private RecyclerView rvPages;

    private List<Book> mBooks;
    private PagingBookAdapter mPagingBookAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_books);

        rvBooks = findViewById(R.id.rv_books);
        rvPages = findViewById(R.id.rv_pages);

        presenter = new AllBooksPresenter(this, this);

        presenter.loadContent();
    }

    @Override
    public void onLoadContent(List<Book> books, List<String> pages) {
        mBooks = books;

        mPagingBookAdapter = new PagingBookAdapter(this, mBooks, this);
        rvBooks.setAdapter(mPagingBookAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvBooks.setLayoutManager(layoutManager);

        PaginationAdapter paginationAdapter = new PaginationAdapter(this, pages, this);
        rvPages.setAdapter(paginationAdapter);

        FlexboxLayoutManager paginationLayoutManager = new FlexboxLayoutManager(this);
        paginationLayoutManager.setJustifyContent(JustifyContent.CENTER);
        rvPages.setLayoutManager(paginationLayoutManager);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onLoadContentByPage(List<Book> books) {
        mBooks.clear();
        mBooks.addAll(books);
        mPagingBookAdapter.notifyDataSetChanged();
    }

    @Override
    public void onPaginationClick(int position) {
        presenter.loadContentByPage(position);
    }

    @Override
    public void onBookClick(String bookId) {
        presenter.toCoverActivity(bookId);
    }
}