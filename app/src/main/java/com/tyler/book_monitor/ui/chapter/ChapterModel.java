package com.tyler.book_monitor.ui.chapter;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.tyler.book_monitor.data.models.Book;
import com.tyler.book_monitor.data.models.Chapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

public class ChapterModel implements ChapterContract.Model {

    private final FirebaseFirestore db;

    public ChapterModel() {
        db = FirebaseFirestore.getInstance();
    }

    @Override
    public void loadContent(String bookId, OnLoadContentListener listener) {
        db.collection("books")
                .document(bookId)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();

                        if (document.exists()) {
                            List<String> categories = new ArrayList<>();
                            Book book = new Book(
                                    document.getId(),
                                    document.get("name").toString(),
                                    document.get("author").toString(),
                                    document.get("avatar").toString(),
                                    document.get("introduction").toString(),
                                    categories
                            );

                            List<HashMap<String, String>> mapChapters = (ArrayList<HashMap<String, String>>) document.get("chapters");
                            List<Chapter> chapters = new Vector<>();
                            for (HashMap<String, String> mapChapter : mapChapters) {
                                chapters.add(new Chapter(
                                        mapChapter.get("title"),
                                        mapChapter.get("content")
                                ));
                            }

                            listener.onSuccess(book, chapters);
                        } else {
                            listener.onFailure("Document does not exist!");
                        }
                    } else {
                        listener.onFailure("Error getting document!");
                    }
                });
    }
}
