package com.example.bookstory.DAO.relations.BookAuthorCrossRef;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Junction;
import androidx.room.Relation;

import com.example.bookstory.DAO.Author;
import com.example.bookstory.DAO.Book;

import java.util.List;

@Entity
public class BookWithAuthors {
    @Embedded
    public Book book;

    @Relation(
            parentColumn = "bookId",
            entityColumn = "authorName",
            associateBy = @Junction(BookAuthorCrossRef.class)
    )
    public List<Author> authors;

    public BookWithAuthors(Book book, List<Author> authors) {
        this.book = book;
        this.authors = authors;
    }
}
