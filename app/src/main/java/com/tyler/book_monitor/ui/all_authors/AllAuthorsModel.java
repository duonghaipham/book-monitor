package com.tyler.book_monitor.ui.all_authors;

import com.google.firebase.firestore.FirebaseFirestore;
import com.tyler.book_monitor.data.models.Author;

import java.util.List;
import java.util.Vector;

public class AllAuthorsModel implements AllAuthorsContract.Model {

    private final FirebaseFirestore db;

    public AllAuthorsModel() {
        db = FirebaseFirestore.getInstance();
    }

    @Override
    public void loadContent(AllAuthorsContract.Model.OnLoadContentListener listener) {
        db.collection("authors").get().addOnSuccessListener(querySnapshot -> {
            List<Author> authors = new Vector<>();
            querySnapshot.getDocuments().forEach(document -> {
                authors.add(new Author(
                        document.getId(),
                        document.get("name").toString(),
                        document.get("avatar").toString(),
                        document.get("introduction").toString()
                ));
            });
            listener.onSuccess(authors);
        });
    }
}
