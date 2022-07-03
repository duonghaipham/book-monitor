package com.tyler.book_monitor.ui.all_books;

import com.google.firebase.firestore.FirebaseFirestore;
import com.tyler.book_monitor.data.models.Book;

import java.util.List;
import java.util.Vector;

public class AllBooksModel implements AllBooksContract.Model {

    private final FirebaseFirestore db;

    public AllBooksModel() {
        this.db = FirebaseFirestore.getInstance();
    }

    @Override
    public void loadContent(OnLoadContentListener listener) {
        db.collection("books")
                .whereEqualTo("isPublished", true)
                .get()
                .addOnSuccessListener(querySnapshot -> {
            List<Book> books = new Vector<>();
            querySnapshot.getDocuments().forEach(document -> {
                books.add(new Book(
                        document.getId(),
                        document.get("name").toString(),
                        document.get("author").toString(),
                        document.get("avatar").toString(),
                        document.get("introduction").toString(),
                        (List<String>)document.get("categories")
                ));
            });
            listener.onSuccess(books);
        });
    }
}
