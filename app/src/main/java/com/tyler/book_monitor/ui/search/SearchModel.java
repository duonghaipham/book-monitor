package com.tyler.book_monitor.ui.search;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.tyler.book_monitor.data.models.Author;
import com.tyler.book_monitor.data.models.Book;
import com.tyler.book_monitor.data.models.GeneralObject;

import java.util.List;
import java.util.Vector;

public class SearchModel implements SearchContract.Model {

    private final FirebaseFirestore db;

    public SearchModel() {
        this.db = FirebaseFirestore.getInstance();
    }

    @Override
    public void search(String query, OnLoadContentListener listener) {
        Task<QuerySnapshot> task1 = db.collection("books")
                .whereGreaterThanOrEqualTo("name", query)
                .whereLessThanOrEqualTo("name", query + "\uf8ff")
                .get();
        Task<QuerySnapshot> task2 = db.collection("authors")
                .whereGreaterThanOrEqualTo("name", query)
                .whereLessThanOrEqualTo("name", query + "\uf8ff")
                .get();

        Task allTasks = Tasks.whenAllSuccess(task1, task2).addOnSuccessListener(objects -> {
            QuerySnapshot querySnapshot1 = (QuerySnapshot)objects.get(0);
            QuerySnapshot querySnapshot2 = (QuerySnapshot)objects.get(1);

            List<GeneralObject> resultObjects = new Vector<>();
            querySnapshot1.getDocuments().forEach(document -> {
                resultObjects.add(new Book(
                        document.getId(),
                        document.get("name").toString(),
                        document.get("author").toString(),
                        document.get("avatar").toString(),
                        document.get("introduction").toString(),
                        (List<String>)document.get("categories")
                ));
            });

            querySnapshot2.getDocuments().forEach(document -> {
                resultObjects.add(new Author(
                        document.getId(),
                        document.get("name").toString(),
                        document.get("avatar").toString(),
                        document.get("introduction").toString()
                ));
            });

            listener.onSuccess(resultObjects);
        });
    }
}
