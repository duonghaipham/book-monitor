package com.tyler.book_monitor.ui.content;

import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tyler.book_monitor.R;

public class ContentFragment extends Fragment {

    private TextView tvChapterName;
    private TextView tvContent;

    private int mFont;
    private int mFontSize;

    private String mChapter;
    private String mContent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mFont = getArguments().getInt("font");
        mFontSize = getArguments().getInt("fontSize");

        mChapter = getArguments().getString("chapter");
        mContent = getArguments().getString("content");

        return inflater.inflate(R.layout.fragment_content, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvChapterName = view.findViewById(R.id.tv_chapter_name);
        tvContent = view.findViewById(R.id.tv_content);

        String[] fonts = getResources().getStringArray(R.array.fonts);

        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "font/" + fonts[mFont] + ".ttf");
        tvContent.setTypeface(typeface);
        tvContent.setTextSize(mFontSize);

        tvChapterName.setText(mChapter);
        tvContent.setText(mContent);
    }
}