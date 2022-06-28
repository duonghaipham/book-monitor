package com.tyler.book_monitor.ui.splash;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;

import com.tyler.book_monitor.ui.main.MainActivity;

public class SplashPresenter implements SplashContract.Presenter {

    public static final int SPLASH_TIME_OUT = 1250;

    private SplashContract.View view;

    public SplashPresenter(SplashContract.View view) {
        this.view = view;
    }

    @Override
    public void toMainActivity(Activity activity) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(activity, MainActivity.class);
                activity.startActivity(intent);
                activity.finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
