package com.tyler.book_monitor.ui.main;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.switchmaterial.SwitchMaterial;
import com.tyler.book_monitor.R;
import com.tyler.book_monitor.data.models.SettingGlobal;

public class SettingsFragment extends DialogFragment {

    public interface ISettingsGlobal {
        void onDataPass(SettingGlobal setting);
    }

    private ISettingsGlobal mSettingsGlobal;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.fragment_settings, null);
        builder.setView(view);

        int language = getArguments().getInt("language");
        int themeMode = getArguments().getInt("themeMode");

        Spinner spnLanguages = view.findViewById(R.id.spn_language);
        SwitchMaterial swtDarkMode = view.findViewById(R.id.swt_dark_mode);

        spnLanguages.setSelection(language);
        swtDarkMode.setChecked(themeMode == 1);

        builder.setPositiveButton(getResources().getString(R.string.ok), (dialog, which) -> {
            mSettingsGlobal.onDataPass(new SettingGlobal(
                    spnLanguages.getSelectedItemPosition(),
                    swtDarkMode.isChecked() ? 1 : 0
            ));

            dismiss();
        });

        builder.setNegativeButton(getResources().getString(R.string.cancel), (dialog, which) -> {
            dismiss();
        });

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            mSettingsGlobal = (SettingsFragment.ISettingsGlobal) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context + " must implement ISettingsGlobal");
        }
    }
}