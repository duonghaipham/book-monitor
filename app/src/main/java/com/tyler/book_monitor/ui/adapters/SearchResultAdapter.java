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
import com.tyler.book_monitor.data.models.Author;
import com.tyler.book_monitor.data.models.Book;
import com.tyler.book_monitor.data.models.GeneralObject;

import java.util.List;

public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.ViewHolder> {

    public interface IObjectClick {
        void onObjectClick(int position);
    }

    private final LayoutInflater inflater;
    private final List<GeneralObject> results;
    private final IObjectClick objectClick;

    public SearchResultAdapter(Context context, List<GeneralObject> results, IObjectClick objectClick) {
        this.inflater = LayoutInflater.from(context);
        this.results = results;
        this.objectClick = objectClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_search_result, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (results.get(position) instanceof Author) {
            Author author = (Author) results.get(position);
            holder.tvName.setText(author.getName());
            Picasso.get()
                    .load(author.getAvatar())
                    .resize(150, 150)
                    .centerCrop()
                    .into(holder.ivFigure);
        } else  {
            Book book = (Book) results.get(position);
            holder.tvName.setText(book.getTitle());
            holder.tvAddition.setText(book.getAuthor());
            Picasso.get()
                    .load(book.getCover())
                    .resize(150, 225)
                    .into(holder.ivFigure);
        }
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvName;
        private final ImageView ivFigure;
        private final TextView tvAddition;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivFigure = itemView.findViewById(R.id.iv_figure);
            tvName = itemView.findViewById(R.id.tv_name);
            tvAddition = itemView.findViewById(R.id.tv_addition);

            itemView.setOnClickListener(v -> {
                if (objectClick != null) {
                    int position = getAdapterPosition();

                    if (position != RecyclerView.NO_POSITION) {
                        objectClick.onObjectClick(position);
                    }
                }
            });
        }
    }
}