package com.tyler.book_monitor.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tyler.book_monitor.R;
import com.tyler.book_monitor.data.models.Chapter;

import java.util.List;

public class ChapterChoiceAdapter extends BaseAdapter {

    private final Activity mActivity;
    private final List<Chapter> mChapterChoices;

    public ChapterChoiceAdapter(Activity activity, List<Chapter> chapterChoices) {
        mActivity = activity;
        mChapterChoices = chapterChoices;
    }

    @Override
    public int getCount() {
        return mChapterChoices.size();
    }

    @Override
    public Object getItem(int position) {
        return mChapterChoices.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint({"ViewHolder", "InflateParams"})
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = mActivity.getLayoutInflater();
        convertView = inflater.inflate(R.layout.item_chapter, null);

        TextView tvChapterName= convertView.findViewById(R.id.tv_chapter_name);

        String title = (position + 1) + ". " + mChapterChoices.get(position).getTitle();

        tvChapterName.setText(title);

        return convertView;
    }
}
