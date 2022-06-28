package com.tyler.book_monitor.ui.search;

import android.content.Context;

import com.tyler.book_monitor.data.models.IObject;

import java.util.List;

public class SearchContract {
    public interface View {
        void onSearch(List<IObject> results);
    }

    public interface Presenter {
        void search(String query);
        void toObjectActivity(Context context, IObject object);
    }
}
