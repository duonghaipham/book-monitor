package com.tyler.book_monitor.ui.splash;

import android.app.Activity;

public class SplashContract {
    public interface View {
    }

    public interface Presenter {
        void toMainActivity(Activity activity);
    }
}
