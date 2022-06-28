package com.tyler.book_monitor.ui.main;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tyler.book_monitor.R;
import com.tyler.book_monitor.adapters.AuthorAdapter;
import com.tyler.book_monitor.adapters.BookAdapter;
import com.tyler.book_monitor.data.models.Author;
import com.tyler.book_monitor.data.models.Book;
import com.tyler.book_monitor.helpers.IAuthorClick;
import com.tyler.book_monitor.helpers.IBookClick;
import com.tyler.book_monitor.ui.base.BaseActivity;

import java.util.List;

public class MainActivity extends BaseActivity implements MainContract.View, IAuthorClick, IBookClick {

    private MainContract.Presenter presenter;

    private ImageButton ibSearch;
    private ImageButton ibMenu;
    private RecyclerView rvPopularAuthors;
    private RecyclerView rvContinuedBooks;
    private TextView tvViewAllPopularAuthors;
    private TextView tvViewAllContinueReading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ibSearch = findViewById(R.id.ib_search);
        ibMenu = findViewById(R.id.ib_menu);
        rvPopularAuthors = findViewById(R.id.rv_popular_authors);
        rvContinuedBooks = findViewById(R.id.rv_continued_books);
        tvViewAllPopularAuthors = findViewById(R.id.tv_view_all_popular_authors);
        tvViewAllContinueReading = findViewById(R.id.tv_view_all_continue_reading);

        presenter = new MainPresenter(this);

        presenter.loadContent();

        ibSearch.setOnClickListener(v -> presenter.toSearchActivity(this));

        tvViewAllPopularAuthors.setOnClickListener(v -> presenter.viewAllPopularAuthors(this));

        tvViewAllContinueReading.setOnClickListener(v -> presenter.viewAllContinueReading(this));
    }

    @Override
    public void onLoadContent(List<Book> continuedBooks, List<Author> authors) {
        BookAdapter adapter = new BookAdapter(this, continuedBooks, this);

        rvContinuedBooks.setAdapter(adapter);
        rvContinuedBooks.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        AuthorAdapter authorAdapter = new AuthorAdapter(this, authors, this);

        rvPopularAuthors.setAdapter(authorAdapter);
        rvPopularAuthors.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }

    @Override
    public void onAuthorClick(int position) {
        presenter.toAuthorActivity(this);
    }

    @Override
    public void onBookClick(int position) {
        presenter.toCoverActivity(this);
    }
}