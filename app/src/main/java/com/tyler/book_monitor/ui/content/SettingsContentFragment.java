package com.tyler.book_monitor.ui.content;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.tyler.book_monitor.R;
import com.tyler.book_monitor.adapters.FontAdapter;
import com.tyler.book_monitor.data.models.Font;
import com.tyler.book_monitor.data.models.SettingContent;

import java.util.List;
import java.util.Vector;


public class SettingsContentFragment extends DialogFragment {

    public interface ISettingsContent {
        void onDataPass(SettingContent setting);
    }

    private ISettingsContent mSettingsContent;

    private int mFont;
    private int mFontSize;
    private boolean mNavigation;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings_content, container, false);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.fragment_settings_content, null);
        builder.setView(view);

        mFont = getArguments().getInt("font");
        mFontSize = getArguments().getInt("fontSize");
        mNavigation = getArguments().getBoolean("navigation");

        Spinner spnFont = view.findViewById(R.id.spn_font);
        TextView tvSample = view.findViewById(R.id.tv_sample);
        Spinner spnFontSize = view.findViewById(R.id.spn_font_size);
        CheckBox cbNavigation = view.findViewById(R.id.cb_navigation);

        List<Font> fonts = new Vector<>();
        fonts.add(new Font("Roboto", "roboto_condensed_regular"));
        fonts.add(new Font("Nunito", "nunito_regular"));
        fonts.add(new Font("EB Garamond", "eb_garamond"));
        fonts.add(new Font("Noto Serif", "noto_serif"));
        fonts.add(new Font("Patrick Hand", "patrick_hand"));

        spnFont.setAdapter(new FontAdapter(getActivity(), fonts));

        spnFont.setSelection(mFont);
        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "font/" + fonts.get(mFont).getActualName() + ".ttf");
        tvSample.setTypeface(typeface);

        spnFontSize.setSelection(((ArrayAdapter<String>)spnFontSize.getAdapter()).getPosition(String.valueOf(mFontSize)));

        cbNavigation.setChecked(mNavigation);

        spnFont.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Font font = (Font) parent.getItemAtPosition(position);
                Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "font/" + font.getActualName() + ".ttf");
                tvSample.setTypeface(typeface);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        builder.setPositiveButton(getResources().getString(R.string.ok), (dialog, which) -> {
            mSettingsContent.onDataPass(
                    new SettingContent(
                            spnFont.getSelectedItemPosition(),
                            Integer.parseInt(spnFontSize.getSelectedItem().toString()),
                            cbNavigation.isChecked()
                    )
            );

            dismiss();
        });

        builder.setNegativeButton(getResources().getString(R.string.cancel), (dialog, which) -> dialog.dismiss());

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            mSettingsContent = (ISettingsContent) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context + " must implement ISettingsContent");
        }
    }
}