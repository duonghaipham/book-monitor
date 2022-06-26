package com.tyler.book_monitor.ui.base;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();
    }
}