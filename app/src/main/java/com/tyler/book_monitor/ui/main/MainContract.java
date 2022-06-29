package com.tyler.book_monitor.ui.main;

import android.content.Context;

import com.tyler.book_monitor.data.models.Author;
import com.tyler.book_monitor.data.models.Book;
import com.tyler.book_monitor.data.models.SettingGlobal;

import java.util.List;

public class MainContract {
    public interface View {
        void onLoadContent(List<Book> continuedBooks, List<Author> authors);
        void onShowSettings(SettingGlobal setting);
        void onShowAbout();
    }

    public interface Presenter {
        void loadContent();
        void toSearchActivity(Context context);
        void toAuthorActivity(Context context);
        void toCoverActivity(Context context);
        void viewAllPopularAuthors(Context context);
        void viewAllContinueReading(Context context);
        void showSettings(Context context);
        void saveSettings(Context context, SettingGlobal setting);
        void showAbout();
    }
}
