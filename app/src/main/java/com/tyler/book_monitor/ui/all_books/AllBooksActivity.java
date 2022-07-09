package com.tyler.book_monitor.ui.all_books;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tyler.book_monitor.R;
import com.tyler.book_monitor.data.models.Book;
import com.tyler.book_monitor.ui.adapters.BookAdapter;
import com.tyler.book_monitor.ui.adapters.PaginationAdapter;
import com.tyler.book_monitor.ui.adapters.PagingBookAdapter;
import com.tyler.book_monitor.ui.base.BaseActivity;

import java.util.List;

public class AllBooksActivity extends BaseActivity implements AllBooksContract.View, PagingBookAdapter.IPagingBookClick, PaginationAdapter.IPaginationClick {

    private AllBooksContract.Presenter presenter;

    private RecyclerView rvBooks;
    private RecyclerView rvPaginations;

    private List<Book> mBooks;
    private List<String> mPages;
    private PagingBookAdapter bookAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_books);

        rvBooks = findViewById(R.id.rv_books);
        rvPaginations = findViewById(R.id.rv_pages);

        presenter = new AllBooksPresenter(this, this);

        presenter.loadContent();
    }

    @Override
    public void onLoadContent(List<Book> books, List<String> pages) {
        mBooks = books;

        bookAdapter = new PagingBookAdapter(this, books, this);
        rvBooks.setAdapter(bookAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvBooks.setLayoutManager(layoutManager);

        PaginationAdapter paginationAdapter = new PaginationAdapter(this, pages, this);
        rvPaginations.setAdapter(paginationAdapter);
        LinearLayoutManager paginationLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rvPaginations.setLayoutManager(paginationLayoutManager);
    }

    @Override
    public void onLoadContentByPage(List<Book> books) {
        mBooks = books;
        rvBooks.setAdapter(new PagingBookAdapter(this, books, this));
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvBooks.setLayoutManager(layoutManager);
    }


    @Override
    public void onPaginationClick(int position) {
        presenter.loadContentByPage(mBooks.get(mBooks.size() - 1).getId(), position);
    }

    @Override
    public void onBookClick(String bookId) {
        presenter.toCoverActivity(bookId);
    }
}