package com.tyler.book_monitor.ui.author;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.squareup.picasso.Picasso;
import com.tyler.book_monitor.R;
import com.tyler.book_monitor.ui.adapters.BookAdapter;
import com.tyler.book_monitor.data.models.Author;
import com.tyler.book_monitor.data.models.Book;
import com.tyler.book_monitor.ui.base.BaseActivity;

import java.util.List;

public class AuthorActivity extends BaseActivity implements AuthorContract.View, BookAdapter.IBookClick {

    private AuthorContract.Presenter presenter;

    private ImageView ivAvatar;
    private TextView tvAuthorName;
    private TextView tvAuthorIntroduction;
    private RecyclerView rvAuthorBooks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author);

        ivAvatar = findViewById(R.id.iv_avatar);
        tvAuthorName = findViewById(R.id.tv_author_name);
        tvAuthorIntroduction = findViewById(R.id.tv_author_introduction);
        rvAuthorBooks = findViewById(R.id.rv_author_books);

        presenter = new AuthorPresenter(this, this);

        presenter.loadContent();
    }

    @Override
    public void onLoadContent(Author author, List<Book> books) {
        Picasso.get().load(author.getAvatar()).into(ivAvatar);
        tvAuthorName.setText(author.getName());
        tvAuthorIntroduction.setText(author.getIntroduction());

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
        presenter.toCoverActivity(position);
    }
}