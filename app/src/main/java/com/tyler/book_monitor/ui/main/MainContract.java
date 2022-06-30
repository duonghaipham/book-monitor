package com.tyler.book_monitor.ui.main;

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
        void toArchiveActivity();
        void toSearchActivity();
        void toAuthorActivity();
        void toCoverActivity();
        void viewAllPopularAuthors();
        void viewAllContinueReading();
        void showSettings();
        void saveSettings(SettingGlobal setting);
        void showAbout();
    }
}
