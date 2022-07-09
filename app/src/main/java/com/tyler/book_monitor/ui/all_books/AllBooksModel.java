package com.tyler.book_monitor.ui.all_books;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.tyler.book_monitor.data.models.Book;

import java.util.List;
import java.util.Vector;

public class AllBooksModel implements AllBooksContract.Model {

    private final FirebaseFirestore db;

    private List<String> mRefs;

    public AllBooksModel() {
        this.db = FirebaseFirestore.getInstance();
    }

    @Override
    public void loadContent(OnLoadContentListener listener) {
        Task<QuerySnapshot> task1 = db.collection("books")
                .whereEqualTo("isPublished", true)
                .orderBy(FieldPath.documentId(), Query.Direction.ASCENDING)
//                .orderBy("name", Query.Direction.DESCENDING)
                .limit(1)
                .get();

        Task<DocumentSnapshot> task2 = db.collection("information")
                .document("index")
                .get();

        Tasks.whenAllSuccess(task1, task2).addOnSuccessListener(objects -> {
            List<Book> books = new Vector<>();
            ((QuerySnapshot)objects.get(0)).getDocuments().forEach(document -> {
                books.add(new Book(
                        document.getId(),
                        document.get("name").toString(),
                        document.get("author").toString(),
                        document.get("avatar").toString(),
                        document.get("introduction").toString(),
                        (List<String>)document.get("categories")
                ));
            });

            mRefs = (List<String>)((DocumentSnapshot)objects.get(1)).get("refs");

            List<String> pages = new Vector<>();
            int counter = mRefs.size();
            for (int i = 0; i < Math.ceil(counter / 1); i++) {
                pages.add(String.valueOf(i + 1));
            }

            listener.onSuccess(books, pages);
        });
    }

    @Override
    public void loadContentByPage(String criterion, int page, OnLoadContentByPageListener listener) {
        db.collection("books")
                .whereEqualTo("isPublished", true)
                .orderBy(FieldPath.documentId())
                .startAt(mRefs.get(page / 1))
                .limit(1)
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
