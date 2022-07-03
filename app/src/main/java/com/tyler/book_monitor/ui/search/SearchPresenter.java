package com.tyler.book_monitor.ui.search;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.tyler.book_monitor.data.models.Author;
import com.tyler.book_monitor.data.models.Book;
import com.tyler.book_monitor.data.models.GeneralObject;
import com.tyler.book_monitor.data.prefs.SettingsManager;
import com.tyler.book_monitor.ui.author.AuthorActivity;
import com.tyler.book_monitor.ui.cover.CoverActivity;

import java.util.List;

public class SearchPresenter implements SearchContract.Presenter {

    private final Context context;
    private final SearchContract.View view;
    private final SearchContract.Model model;

    private List<GeneralObject> mObjects;

    public SearchPresenter(Context context, SearchContract.View view) {
        this.context = context;
        this.view = view;
        this.model = new SearchModel();
    }

    @Override
    public void initialize() {
        int themeMode = SettingsManager.getThemeMode(context);

        view.onInitialize(themeMode);
    }

    @Override
    public void search(String query) {
        model.search(query, new SearchModel.OnLoadContentListener() {
            @Override
            public void onSuccess(List<GeneralObject> objects) {
                mObjects = objects;

                view.onSearch(objects);
            }

            @Override
            public void onFailure(String message) {
                view.onSearch(null);
            }
        });
    }

    @Override
    public void toObjectActivity(int position) {
        Intent intent;
        Bundle bundle = new Bundle();

        GeneralObject object = mObjects.get(position);

        if (object instanceof Book) {
            bundle.putString("bookId", object.getId());
            intent = new Intent(context, CoverActivity.class);
        } else {
            bundle.putString("authorId", object.getId());
            bundle.putString("authorName", ((Author)object).getName());
            intent = new Intent(context, AuthorActivity.class);
        }

        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
