package com.tyler.book_monitor.ui.cover;

import android.content.Context;

public class CoverContract {
    public interface View {
        void onInitialize(int themeMode);
    }

    public interface Presenter {
        void toChapterActivity(Context context);
        void initialize(Context context);
    }
}
