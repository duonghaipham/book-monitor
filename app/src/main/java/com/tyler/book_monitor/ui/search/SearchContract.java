package com.tyler.book_monitor.ui.search;

import com.tyler.book_monitor.data.models.GeneralObject;

import java.util.List;

public class SearchContract {
    public interface View {
        void onInitialize(int themeMode);
        void onSearch(List<GeneralObject> results);
    }

    public interface Presenter {
        void initialize();
        void search(String query);
        void toObjectActivity(int position);
    }

    public interface Model {
        interface OnLoadContentListener {
            void onSuccess(List<GeneralObject> objects);
            void onFailure(String message);
        }
        void search(String query, OnLoadContentListener listener);
    }
}
