package com.tyler.book_monitor.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.tyler.book_monitor.R;

public class AboutFragment extends DialogFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView tvVersion = view.findViewById(R.id.tv_version);
        TextView tvDeveloper = view.findViewById(R.id.tv_developer);

        tvVersion.setText(String.format(getString(R.string.version), "1.0.0"));
        tvDeveloper.setText(String.format(getString(R.string.developer), "Tyler"));
    }
}