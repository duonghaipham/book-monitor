package com.tyler.book_monitor.ui.archive;

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

public class ArchiveActivity extends BaseActivity implements ArchiveContract.View, PersonalBookAdapter.IPersonalBookClick {

    private ArchivePresenter presenter;

    private RecyclerView rvArchivedBooks;
    private TextView tvNoArchivedBooks;

    private PersonalBookAdapter mAdapter;
    private List<Book> mBooks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archive);

        rvArchivedBooks = findViewById(R.id.rv_archived_books);
        tvNoArchivedBooks = findViewById(R.id.tv_no_archived_books);

        presenter = new ArchivePresenter(this, this);

        presenter.loadContent();
    }

    @Override
    public void onLoadContent(List<Book> archivedBooks) {
        if (archivedBooks.size() > 0) {
            rvArchivedBooks.setVisibility(View.VISIBLE);
            tvNoArchivedBooks.setVisibility(View.GONE);

            mBooks = archivedBooks;
            mAdapter = new PersonalBookAdapter(this, mBooks, this);

            rvArchivedBooks.setAdapter(mAdapter);
            rvArchivedBooks.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        } else {
            rvArchivedBooks.setVisibility(View.GONE);
            tvNoArchivedBooks.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onRemoveClick(int position, Book book) {
        presenter.removeBookFromArchive(book.getId());

        mBooks.remove(position);
        mAdapter.notifyItemRemoved(position);
    }

    @Override
    public void onViewClick(String bookId) {
        presenter.toCoverActivity(bookId);
    }
}