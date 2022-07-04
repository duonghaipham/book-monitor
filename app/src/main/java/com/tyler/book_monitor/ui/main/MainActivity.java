package com.tyler.book_monitor.ui.main;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.tyler.book_monitor.R;
import com.tyler.book_monitor.ui.adapters.AuthorAdapter;
import com.tyler.book_monitor.ui.adapters.BookAdapter;
import com.tyler.book_monitor.data.models.Author;
import com.tyler.book_monitor.data.models.Book;
import com.tyler.book_monitor.data.models.SettingGlobal;
import com.tyler.book_monitor.ui.base.BaseActivity;

import java.util.List;
import java.util.Locale;

public class MainActivity extends BaseActivity implements MainContract.View, AuthorAdapter.IAuthorClick, BookAdapter.IBookClick, SettingsFragment.ISettingsGlobal {

    private MainContract.Presenter presenter;

    private DrawerLayout dlConfiguration;
    private NavigationView nvConfiguration;
    private ImageButton ibSearch;
    private ImageButton ibArchive;
    private ImageButton ibDownload;
    private ImageButton ibMenu;
    private RecyclerView rvPopularAuthors;
    private RecyclerView rvContinuedBooks;
    private TextView tvViewAllPopularAuthors;
    private TextView tvViewAllContinueReading;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dlConfiguration = findViewById(R.id.dl_configuration);
        nvConfiguration = findViewById(R.id.nv_configuration);
        ibSearch = findViewById(R.id.ib_search);
        ibArchive = findViewById(R.id.ib_archive);
        ibDownload = findViewById(R.id.ib_download);
        ibMenu = findViewById(R.id.ib_menu);
        rvPopularAuthors = findViewById(R.id.rv_popular_authors);
        rvContinuedBooks = findViewById(R.id.rv_continued_books);
        tvViewAllPopularAuthors = findViewById(R.id.tv_view_all_popular_authors);
        tvViewAllContinueReading = findViewById(R.id.tv_view_all_continue_reading);

        presenter = new MainPresenter(this, this);

        presenter.loadContent();

        ibSearch.setOnClickListener(v -> presenter.toSearchActivity());

        ibArchive.setOnClickListener(v -> presenter.toArchiveActivity());

        ibDownload.setOnClickListener(v -> presenter.toDownloadActivity());

        ibMenu.setOnClickListener(v -> dlConfiguration.openDrawer(Gravity.LEFT));

        tvViewAllPopularAuthors.setOnClickListener(v -> presenter.viewAllPopularAuthors());

        tvViewAllContinueReading.setOnClickListener(v -> presenter.viewAllContinueReading());

        nvConfiguration.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.action_settings:
                    presenter.showSettings();
                    break;
                case R.id.action_about:
                    presenter.showAbout();
                    break;
            }
            return true;
        });
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
    public void onShowSettings(SettingGlobal setting) {
        SettingsFragment fragment = new SettingsFragment();

        Bundle bundle = new Bundle();
        bundle.putInt("language", setting.getLanguage());
        bundle.putInt("themeMode", setting.getThemeMode());

        fragment.setArguments(bundle);
        fragment.show(getSupportFragmentManager(), "settings");
    }

    @Override
    public void onShowAbout() {
        AboutFragment fragment = new AboutFragment();

        fragment.show(getSupportFragmentManager(), "about");
    }

    @Override
    public void onAuthorClick(int position) {
        presenter.toAuthorActivity(position);
    }

    @Override
    public void onBookClick(int position) {
        presenter.toCoverActivity(position);
    }

    @Override
    public void onDataPass(SettingGlobal setting) {
        int modeNight = setting.getThemeMode() == 1 ?
                AppCompatDelegate.MODE_NIGHT_YES :
                AppCompatDelegate.MODE_NIGHT_NO;
        AppCompatDelegate.setDefaultNightMode(modeNight);

        String[] languageCodes = getResources().getStringArray(R.array.language_codes);

        Locale locale = new Locale(languageCodes[setting.getLanguage()]);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());

        recreate();

        presenter.saveSettings(setting);
    }
}