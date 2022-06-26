package com.tyler.book_monitor.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tyler.book_monitor.R;
import com.tyler.book_monitor.data.models.Chapter;
import com.tyler.book_monitor.helpers.IChapterClick;

import java.util.List;

public class ChapterAdapter extends RecyclerView.Adapter<ChapterAdapter.ChapterViewHolder> {

    private LayoutInflater inflater;
    private List<Chapter> chapters;
    private IChapterClick chapterClick;

    public ChapterAdapter(Context context, List<Chapter> chapters, IChapterClick chapterClick) {
        this.inflater = LayoutInflater.from(context);
        this.chapters = chapters;
        this.chapterClick = chapterClick;
    }

    @NonNull
    @Override
    public ChapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_chapter, parent, false);
        return new ChapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChapterViewHolder holder, int position) {
        holder.tvChapterName.setText(chapters.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return chapters.size();
    }

    public class ChapterViewHolder extends RecyclerView.ViewHolder {

        private TextView tvChapterName;

        public ChapterViewHolder(@NonNull View itemView) {
            super(itemView);

            tvChapterName = itemView.findViewById(R.id.tv_chapter_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (chapterClick != null) {
                        if (getAdapterPosition() != RecyclerView.NO_POSITION) {
                            chapterClick.onChapterClick(getAdapterPosition());
                        }
                    }
                }
            });
        }
    }
}
