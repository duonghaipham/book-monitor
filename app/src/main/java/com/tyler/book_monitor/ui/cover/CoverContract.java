package com.tyler.book_monitor.ui.cover;

public class CoverContract {
    public interface View {
        void onInitialize(int themeMode);
    }

    public interface Presenter {
        void toChapterActivity();
        void initialize();
    }
}
