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

import com.squareup.picasso.Picasso;
import com.tyler.book_monitor.R;
import com.tyler.book_monitor.data.models.Book;

import java.util.List;

public class PagingBookAdapter extends RecyclerView.Adapter<PagingBookAdapter.ViewHolder> {

    public interface IPagingBookClick {
        void onBookClick(String bookId);
    }

    private final LayoutInflater inflater;
    private final List<Book> books;
    private final IPagingBookClick pagingBookClick;

    public PagingBookAdapter(Context context, List<Book> books, IPagingBookClick pagingBookClick) {
        this.inflater = LayoutInflater.from(context);
        this.books = books;
        this.pagingBookClick = pagingBookClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_personal_book, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PagingBookAdapter.ViewHolder holder, int position) {
        holder.tvTitle.setText(books.get(position).getTitle());
        holder.tvAuthor.setText(books.get(position).getAuthor());
        Picasso.get().load(books.get(position).getCover()).into(holder.ivCover);
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
            ImageButton ibRemove = itemView.findViewById(R.id.ib_remove);

            ibRemove.setVisibility(View.GONE);

            itemView.setOnClickListener(v -> {
                if (pagingBookClick != null) {
                    if (getAdapterPosition() != RecyclerView.NO_POSITION) {
                        pagingBookClick.onBookClick(books.get(getAdapterPosition()).getId());
                    }
                }
            });
        }
    }
}
