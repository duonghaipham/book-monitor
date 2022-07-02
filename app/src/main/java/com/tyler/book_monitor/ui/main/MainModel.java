package com.tyler.book_monitor.ui.main;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.tyler.book_monitor.data.models.Author;
import com.tyler.book_monitor.data.models.Book;

import java.util.List;
import java.util.Vector;

public class MainModel implements MainContract.Model {

    private final FirebaseFirestore db;

    public MainModel() {
        db = FirebaseFirestore.getInstance();
    }

    @Override
    public void loadContent(OnLoadContentListener listener) {
        Task<QuerySnapshot> task1 = db.collection("books").get();
        Task<QuerySnapshot> task2 = db.collection("authors").get();

        Tasks.whenAllSuccess(task1, task2).addOnSuccessListener(objects -> {
            List<Book> books = new Vector<>();
            ((QuerySnapshot)objects.get(0)).getDocuments().forEach(document -> {
                books.add(new Book(
                        document.getId(),
                        document.get("name").toString(),
                        document.get("author").toString(),
                        document.get("avatar").toString()
                ));
            });

            List<Author> authors = new Vector<>();
            ((QuerySnapshot)objects.get(1)).getDocuments().forEach(document -> {
                authors.add(new Author(
                        document.get("name").toString(),
                        document.get("avatar").toString()
                ));
            });

            listener.onSuccess(books, authors);
        });
    }
}
