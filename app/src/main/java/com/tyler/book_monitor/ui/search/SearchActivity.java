package com.tyler.book_monitor.ui.search;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tyler.book_monitor.R;
import com.tyler.book_monitor.adapters.SearchResultAdapter;
import com.tyler.book_monitor.data.models.IObject;
import com.tyler.book_monitor.helpers.IObjectClick;

import java.util.List;

public class SearchActivity extends AppCompatActivity implements SearchContract.View, IObjectClick {

    private SearchContract.Presenter presenter;

    private SearchView svEverything;
    private RecyclerView rvSearchResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        presenter = new SearchPresenter(this);

        rvSearchResults = findViewById(R.id.rv_search_results);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(0xffEEEEEE));
        svEverything = new SearchView(this);

        actionBar.setCustomView(svEverything);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

        svEverything.setFocusable(true);
        svEverything.setIconified(false);
        svEverything.setQueryHint(getString(R.string.search_hint));

        svEverything.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                presenter.search(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    public void onSearch(List<IObject> results) {
        SearchResultAdapter adapter = new SearchResultAdapter(this, results, this);
        rvSearchResults.setAdapter(adapter);

        rvSearchResults.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void onObjectClick(IObject object) {
        presenter.toObjectActivity(this, object);
    }
}