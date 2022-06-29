package com.tyler.book_monitor.ui.search;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        presenter = new SearchPresenter(this);

        rvSearchResults = findViewById(R.id.rv_search_results);

        actionBar = getSupportActionBar();
        svEverything = new SearchView(this);

        actionBar.setCustomView(svEverything);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

        svEverything.setFocusable(true);
        svEverything.setIconified(false);
        svEverything.setQueryHint(getString(R.string.search_hint));

        presenter.initialize(this);

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

        if (themeMode == 1) {
            window.setStatusBarColor(Color.parseColor("#242527"));
            svEverything.setBackgroundColor(Color.parseColor("#242527"));
            actionBar.setBackgroundDrawable(new ColorDrawable(0xff242527));
        }
        else {
            window.setStatusBarColor(Color.parseColor("#AFAFAF"));
            svEverything.setBackgroundColor(Color.parseColor("#EEEEEE"));
            actionBar.setBackgroundDrawable(new ColorDrawable(0xffEEEEEE));
        }
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