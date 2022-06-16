package com.tyler.book_monitor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.tyler.book_monitor.adapters.AuthorAdapter;
import com.tyler.book_monitor.adapters.BookAdapter;
import com.tyler.book_monitor.models.Author;
import com.tyler.book_monitor.models.Book;

import java.util.List;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvPopularAuthors;
    private RecyclerView rvContinuedBooks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        rvPopularAuthors = findViewById(R.id.rv_popular_authors);
        rvContinuedBooks = findViewById(R.id.rv_continued_books);

//        Intent intent = new Intent(this, CoverActivity.class);
//        startActivity(intent);

        List<Book> books = new Vector<>();
        books.add(new Book("The Da Vinci Code", "Dan Brown", R.drawable.mock_book_cover));
        books.add(new Book("Harry Potter and the chamber of secrets", "J.K Rowling", R.drawable.mock_book_cover_1));
        books.add(new Book("Harry Potter and the stone", "J.K Rowling", R.drawable.mock_book_cover_2));

        BookAdapter adapter = new BookAdapter(this, books);

        rvContinuedBooks.setAdapter(adapter);
        rvContinuedBooks.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));


        List<Author> authors = new Vector<>();
        authors.add(new Author("Dan Brown", ""));
        authors.add(new Author("J.K Rowling", ""));
        authors.add(new Author("Issac Newton", ""));
        authors.add(new Author("Malcolm Gladwell", ""));

        AuthorAdapter authorAdapter = new AuthorAdapter(this, authors);

        rvPopularAuthors.setAdapter(authorAdapter);
        rvPopularAuthors.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }
}