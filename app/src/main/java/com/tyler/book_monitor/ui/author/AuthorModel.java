package com.tyler.book_monitor.ui.author;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.tyler.book_monitor.data.models.Author;
import com.tyler.book_monitor.data.models.Book;

import java.util.List;
import java.util.Vector;

public class AuthorModel implements AuthorContract.Model {

    private final FirebaseFirestore db;

    public AuthorModel() {
        db = FirebaseFirestore.getInstance();
    }

    @Override
    public void loadContent(String authorId, String authorName, OnLoadContentListener listener) {
        Task<DocumentSnapshot> task1 = db.collection("authors").document(authorId).get();
        Task<QuerySnapshot> task2 = db.collection("books").whereEqualTo("author", authorName).get();

        Task allTasks = Tasks.whenAllSuccess(task1, task2).addOnSuccessListener(new OnSuccessListener<List<Object>>() {
            @Override
            public void onSuccess(List<Object> objects) {
                DocumentSnapshot documentAuthor = (DocumentSnapshot)objects.get(0);
                QuerySnapshot querySnapshot = (QuerySnapshot)objects.get(1);

                if (documentAuthor.exists()) {
                    Author author = new Author(
                            documentAuthor.getId(),
                            documentAuthor.get("name").toString(),
                            documentAuthor.get("avatar").toString(),
                            documentAuthor.get("introduction").toString()
                    );

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

                    listener.onSuccess(author, books);
                } else {
                    listener.onFailure("Document does not exist!");
                }
            }
        });
    }
}
