package com.tyler.book_monitor.ui.cover;

import com.google.firebase.firestore.FirebaseFirestore;
import com.tyler.book_monitor.data.models.Book;

import java.util.ArrayList;
import java.util.List;

public class CoverModel implements CoverContract.Model {

    private final FirebaseFirestore db;

    public CoverModel() {
        db = FirebaseFirestore.getInstance();
    }

    @Override
    public void loadContent(String bookId, OnLoadContentListener listener) {
        db.collection("books")
                .document(bookId)
                .get()
                .addOnSuccessListener(document -> {
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

                        listener.onSuccess(book);
                    } else {
                        listener.onFailure("Document does not exist!");
                    }
                });
    }
}
