package com.tyler.book_monitor.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tyler.book_monitor.R;

import java.util.List;

public class PaginationAdapter extends RecyclerView.Adapter<PaginationAdapter.ViewHolder> {

    public interface IPaginationClick {
        void onPaginationClick(int position);
    }

    private final Context context;
    private final LayoutInflater inflater;
    private final List<String> pages;
    private final IPaginationClick paginationClick;
    private static int index = -1;

    public PaginationAdapter(Context context, List<String> pages, IPaginationClick paginationClick) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.pages = pages;
        this.paginationClick = paginationClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_pagination, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemView.setOnClickListener(v -> {
            index = holder.getAdapterPosition();
            notifyDataSetChanged();

            paginationClick.onPaginationClick(index);
        });

        if (index == position) {
            holder.tvPageNumber.setBackgroundColor(context.getColor(R.color.paginationSelectedColor));
        } else {
            holder.tvPageNumber.setBackgroundColor(context.getColor(R.color.primaryButtonColor));
        }
        holder.tvPageNumber.setText(pages.get(position));
    }

    @Override
    public int getItemCount() {
        return pages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvPageNumber;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvPageNumber = itemView.findViewById(R.id.tv_page_number);

            itemView.setOnClickListener(v -> {
                if (paginationClick != null) {
                    if (getAdapterPosition() != RecyclerView.NO_POSITION) {
                        paginationClick.onPaginationClick(getAdapterPosition());
                    }
                }
            });
        }
    }
}
