package com.tyler.book_monitor.ui.all_authors;

import android.content.Context;

import com.tyler.book_monitor.data.models.Author;

import java.util.List;

public class AllAuthorsContract {
    public interface View {
        void onLoadContent(List<Author> authors);
    }

    public interface Presenter {
        void loadContent();
        void toAuthorActivity(Context context);
    }
}
