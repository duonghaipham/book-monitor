package com.tyler.book_monitor.ui.splash;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;

import com.tyler.book_monitor.ui.main.MainActivity;

public class SplashPresenter implements SplashContract.Presenter {

    public static final int SPLASH_TIME_OUT = 1250;

    private Activity activity;
    private SplashContract.View view;

    public SplashPresenter(Activity activity, SplashContract.View view) {
        this.activity = activity;
        this.view = view;
    }

    @Override
    public void toMainActivity() {
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(activity, MainActivity.class);
            activity.startActivity(intent);
            activity.finish();
        }, SPLASH_TIME_OUT);
    }
}
