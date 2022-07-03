package com.tyler.book_monitor.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.tyler.book_monitor.R;
import com.tyler.book_monitor.data.models.Book;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {

    public interface IBookClick {
        void onBookClick(int position);
    }

    private final LayoutInflater inflater;
    private final List<Book> books;
    private final IBookClick bookClick;

    public BookAdapter(Context context, List<Book> books, IBookClick bookClick) {
        this.inflater = LayoutInflater.from(context);
        this.books = books;
        this.bookClick = bookClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_book, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Picasso.get().load(books.get(position).getCover()).into(holder.ivCover);
        holder.tvTitle.setText(books.get(position).getTitle());
        if (books.get(position).getAuthor() != null) {
            holder.tvAuthor.setText(books.get(position).getAuthor());
        }
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvTitle;
        private final TextView tvAuthor;
        private final ImageView ivCover;

        public ViewHolder(View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tv_title);
            tvAuthor = itemView.findViewById(R.id.tv_author);
            ivCover = itemView.findViewById(R.id.iv_cover);

            itemView.setOnClickListener(v -> {
                if (bookClick != null) {
                    if (getAdapterPosition() != RecyclerView.NO_POSITION) {
                        bookClick.onBookClick(getAdapterPosition());
                    }
                }
            });
        }
    }
}
