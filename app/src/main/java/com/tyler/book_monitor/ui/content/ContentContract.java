package com.tyler.book_monitor.ui.content;

import android.app.Activity;

import java.util.ArrayList;

public class ContentContract {
    public interface View {
        void onLoadContent(int color, String chapter, String content);
        void onShowChapterChoices(ArrayList<String> chapterChoices);
    }

    public interface Presenter {
        void loadContent(Activity activity);
        void showChapterChoices();
    }
}
