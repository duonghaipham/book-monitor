package com.tyler.book_monitor.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tyler.book_monitor.R;
import com.tyler.book_monitor.data.models.Author;
import com.tyler.book_monitor.helpers.IAuthorClick;

import java.util.List;

public class AuthorAdapter extends RecyclerView.Adapter<AuthorAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<Author> authors;
    private IAuthorClick authorClick;

    public AuthorAdapter(Context context, List<Author> authors, IAuthorClick authorClick) {
        this.inflater = LayoutInflater.from(context);
        this.authors = authors;
        this.authorClick = authorClick;
    }

    @NonNull
    @Override
    public AuthorAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_author, parent, false);
        return new AuthorAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AuthorAdapter.ViewHolder holder, int position) {
        holder.tvName.setText(authors.get(position).getName());
//        holder.ivCover.setImageResource(R.drawable.mock_book_cover);
    }

    @Override
    public int getItemCount() {
        return authors.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvName;
//        private ImageView ivAvatar;

        public ViewHolder(View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tv_name);
//            ivAvatar = itemView.findViewById(R.id.iv_avatar);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (authorClick != null) {
                        int position = getAdapterPosition();

                        if (position != RecyclerView.NO_POSITION) {
                            authorClick.onAuthorClick(position);
                        }
                    }
                }
            });
        }
    }
}
