package com.example.bookstory.DAO.relations.BookAuthorCrossRef;

import androidx.annotation.NonNull;
import androidx.room.Entity;

@Entity(primaryKeys = {"bookId", "authorName"})
public class BookAuthorCrossRef {
    public long bookId;
    @NonNull
    public String authorName;

    public BookAuthorCrossRef(long bookId, @NonNull String authorName) {
        this.bookId = bookId;
        this.authorName = authorName;
    }
}
