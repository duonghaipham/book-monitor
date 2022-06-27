package com.tyler.book_monitor.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tyler.book_monitor.R;
import com.tyler.book_monitor.data.models.Font;

import java.util.List;

public class FontAdapter extends BaseAdapter {

    private final Activity activity;
    private final List<Font> fonts;

    public FontAdapter(Activity activity, List<Font> fonts) {
        this.activity = activity;
        this.fonts = fonts;
    }

    @Override
    public int getCount() {
        return fonts.size();
    }

    @Override
    public Object getItem(int position) {
        return fonts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = activity.getLayoutInflater();
        convertView = inflater.inflate(R.layout.item_font, null);

        TextView tvFont = convertView.findViewById(R.id.tv_font);
        tvFont.setText(fonts.get(position).getPerformedName());
        tvFont.setTag(fonts.get(position).getActualName());

        return convertView;
    }
}
