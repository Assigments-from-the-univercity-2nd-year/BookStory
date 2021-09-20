package com.example.bookstory.DAO.relations.BookAuthorCrossRef;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Junction;
import androidx.room.Relation;

import com.example.bookstory.DAO.Author;
import com.example.bookstory.DAO.Book;

import java.util.List;

@Entity
public class AuthorWithBooks {
    @Embedded
    public Author author;

    @Relation(
            parentColumn = "authorName",
            entityColumn = "bookId",
            associateBy = @Junction(BookAuthorCrossRef.class)
    )
    public List<Book> books;
}
