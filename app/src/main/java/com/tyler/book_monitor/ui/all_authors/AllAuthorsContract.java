package com.tyler.book_monitor.ui.all_authors;

import com.tyler.book_monitor.data.models.Author;

import java.util.List;

public class AllAuthorsContract {
    public interface View {
        void onLoadContent(List<Author> authors);
    }

    public interface Presenter {
        void loadContent();
        void toAuthorActivity(int position);
    }

    public interface Model {
        interface OnLoadContentListener {
            void onSuccess(List<Author> authors);
            void onFailure(String message);
        }
        void loadContent(OnLoadContentListener listener);
    }
}
