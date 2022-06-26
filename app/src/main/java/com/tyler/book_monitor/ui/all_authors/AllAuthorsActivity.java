package com.tyler.book_monitor.ui.all_authors;

import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.flexbox.AlignContent;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.tyler.book_monitor.R;
import com.tyler.book_monitor.adapters.AuthorAdapter;
import com.tyler.book_monitor.data.models.Author;
import com.tyler.book_monitor.helpers.IAuthorClick;
import com.tyler.book_monitor.ui.base.BaseActivity;

import java.util.List;

public class AllAuthorsActivity extends BaseActivity implements AllAuthorsContract.View, IAuthorClick {

    private AllAuthorsContract.Presenter presenter;

    private RecyclerView rvAllAuthors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_authors);

        rvAllAuthors = findViewById(R.id.rv_all_authors);

        presenter = new AllAuthorsPresenter(this);

        presenter.loadContent();
    }

    @Override
    public void onLoadContent(List<Author> authors) {
        AuthorAdapter authorAdapter = new AuthorAdapter(this, authors, this);

        rvAllAuthors.setAdapter(authorAdapter);

        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(this);
        layoutManager.setFlexDirection(FlexDirection.ROW);
        layoutManager.setJustifyContent(JustifyContent.SPACE_BETWEEN);
        layoutManager.setFlexWrap(FlexWrap.WRAP);

        rvAllAuthors.setLayoutManager(layoutManager);
    }

    @Override
    public void onAuthorClick(int position) {
        presenter.toAuthorActivity(this);
    }
}