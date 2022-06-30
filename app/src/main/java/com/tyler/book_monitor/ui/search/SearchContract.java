package com.tyler.book_monitor.ui.search;

import com.tyler.book_monitor.data.models.IObject;

import java.util.List;

public class SearchContract {
    public interface View {
        void onInitialize(int themeMode);
        void onSearch(List<IObject> results);
    }

    public interface Presenter {
        void initialize();
        void search(String query);
        void toObjectActivity(IObject object);
    }
}
