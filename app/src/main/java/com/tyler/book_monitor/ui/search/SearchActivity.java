package com.tyler.book_monitor.ui.search;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tyler.book_monitor.R;
import com.tyler.book_monitor.data.models.GeneralObject;
import com.tyler.book_monitor.ui.adapters.SearchResultAdapter;

import java.util.List;

public class SearchActivity extends AppCompatActivity implements SearchContract.View, SearchResultAdapter.IObjectClick {

    private SearchContract.Presenter presenter;

    private SearchView svEverything;
    private RecyclerView rvSearchResults;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        presenter = new SearchPresenter(this, this);

        rvSearchResults = findViewById(R.id.rv_search_results);

        actionBar = getSupportActionBar();
        svEverything = new SearchView(this);

        actionBar.setCustomView(svEverything);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

        svEverything.setFocusable(true);
        svEverything.setIconified(false);
        svEverything.setQueryHint(getString(R.string.search_hint));

        presenter.initialize();

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
    public void onInitialize(int themeMode) {
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        int statusBarColor = getColor(R.color.statusBarColor);
        ColorDrawable actionBarDrawable = new ColorDrawable(statusBarColor);

        window.setStatusBarColor(statusBarColor);
        svEverything.setBackgroundColor(statusBarColor);
        actionBar.setBackgroundDrawable(actionBarDrawable);
    }

    @Override
    public void onSearch(List<GeneralObject> results) {
        SearchResultAdapter adapter = new SearchResultAdapter(this, results, this);
        rvSearchResults.setAdapter(adapter);

        rvSearchResults.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void onObjectClick(int position) {
        presenter.toObjectActivity(position);
    }
}