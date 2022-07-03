package com.tyler.book_monitor.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tyler.book_monitor.R;
import com.tyler.book_monitor.data.models.Book;

import java.util.List;

public class ArchivedBookAdapter extends RecyclerView.Adapter<ArchivedBookAdapter.ViewHolder> {

    public interface IArchivedBookClick {
        void onRemoveClick(String bookId);
        void onViewClick(String bookId);
    }

    private LayoutInflater inflater;
    private List<Book> books;
    private IArchivedBookClick archivedBookClick;

    public ArchivedBookAdapter(Context context, List<Book> books, IArchivedBookClick archivedBookClick) {
        this.inflater = LayoutInflater.from(context);
        this.books = books;
        this.archivedBookClick = archivedBookClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_archived_book, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArchivedBookAdapter.ViewHolder holder, int position) {
        holder.tvTitle.setText(books.get(position).getTitle());
        if (books.get(position).getAuthor() != null) {
            holder.tvAuthor.setText(books.get(position).getAuthor());
        }
//        holder.ivCover.setImageResource(R.drawable.mock_book_cover);
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle;
        private TextView tvAuthor;
        private ImageView ivCover;
        private ImageButton ibRemove;

        public ViewHolder(View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tv_title);
            tvAuthor = itemView.findViewById(R.id.tv_author);
            ivCover = itemView.findViewById(R.id.iv_cover);
            ibRemove = itemView.findViewById(R.id.ib_remove);

            itemView.setOnClickListener(v -> {
                if (archivedBookClick != null) {
                    if (getAdapterPosition() != RecyclerView.NO_POSITION) {
                        archivedBookClick.onViewClick("Nhan vao nut view");
                    }
                }
            });

            ibRemove.setOnClickListener(v -> {
                if (archivedBookClick != null) {
                    if (getAdapterPosition() != RecyclerView.NO_POSITION) {
                        archivedBookClick.onRemoveClick("Nhan vao nut remove");
                    }
                }
            });
        }
    }
}
