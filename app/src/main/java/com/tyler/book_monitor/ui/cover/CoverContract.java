package com.tyler.book_monitor.ui.cover;

import android.content.Context;

public class CoverContract {
    public interface View {
    }

    public interface Presenter {
        void toChapterActivity(Context context);
    }
}
