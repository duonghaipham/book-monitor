package com.tyler.book_monitor.ui.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tyler.book_monitor.R;
import com.tyler.book_monitor.data.models.Chapter;

import java.util.List;

public class ChapterAdapter extends RecyclerView.Adapter<ChapterAdapter.ChapterViewHolder> {

    public interface IChapterClick {
        void onChapterClick(int position);
    }

    private final LayoutInflater inflater;
    private final List<Chapter> chapters;
    private final IChapterClick chapterClick;

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

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ChapterViewHolder holder, int position) {
        String title = (position + 1) + ". " + chapters.get(position).getTitle();

        holder.tvChapterName.setText(title);
    }

    @Override
    public int getItemCount() {
        return chapters.size();
    }

    public class ChapterViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvChapterName;

        public ChapterViewHolder(@NonNull View itemView) {
            super(itemView);

            tvChapterName = itemView.findViewById(R.id.tv_chapter_name);

            itemView.setOnClickListener(v -> {
                if (chapterClick != null) {
                    if (getAdapterPosition() != RecyclerView.NO_POSITION) {
                        chapterClick.onChapterClick(getAdapterPosition());
                    }
                }
            });
        }
    }
}
