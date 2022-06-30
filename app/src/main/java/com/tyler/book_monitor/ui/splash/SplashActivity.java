package com.tyler.book_monitor.ui.splash;

import android.os.Bundle;

import com.airbnb.lottie.LottieAnimationView;
import com.tyler.book_monitor.R;
import com.tyler.book_monitor.ui.base.BaseActivity;

public class SplashActivity extends BaseActivity implements SplashContract.View {

    private SplashPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        presenter = new SplashPresenter(this, this);

        LottieAnimationView lavSplash = findViewById(R.id.lav_splash);

//        lavSplash.animate().setDuration(SplashPresenter.SPLASH_TIME_OUT);

        presenter.toMainActivity();
    }
}