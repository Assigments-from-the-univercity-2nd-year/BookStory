package com.example.bookstory.DAO.relations.BookAuthorCrossRef;

import androidx.room.Entity;

@Entity(primaryKeys = {"bookId", "authorName"})
public class BookAuthorCrossRef {
    public int bookId;
    public String authorName;
}
