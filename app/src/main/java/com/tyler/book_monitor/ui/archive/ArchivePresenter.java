package com.tyler.book_monitor.ui.archive;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.tyler.book_monitor.data.models.Book;
import com.tyler.book_monitor.ui.cover.CoverActivity;

import java.util.List;

public class ArchivePresenter implements ArchiveContract.Presenter {

    private final Context context;
    private final ArchiveContract.View view;
    private final ArchiveContract.Model model;

    private List<Book> mArchivedBooks;

    public ArchivePresenter(Context context, ArchiveContract.View view) {
        this.context = context;
        this.view = view;
        this.model = new ArchiveModel(context);
    }

    @Override
    public void loadContent() {
        model.loadContent(new ArchiveModel.OnLoadContentListener() {
            @Override
            public void onLoadContent(List<Book> archivedBooks) {
                mArchivedBooks = archivedBooks;

                view.onLoadContent(archivedBooks);
            }
        });
    }

    @Override
    public void toCoverActivity(String bookId) {
        Bundle bundle = new Bundle();
        bundle.putString("bookId", bookId);

        Intent intent = new Intent(context, CoverActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    public void removeBookFromArchive(String bookId) {
        model.removeBookFromArchive(bookId);
    }
}
