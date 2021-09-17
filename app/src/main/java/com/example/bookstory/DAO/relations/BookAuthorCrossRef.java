package com.example.bookstory.DAO.relations;

import androidx.room.Entity;

@Entity(primaryKeys = {"bookId", "authorName"})
public class BookAuthorCrossRef {
    int bookId;
    String authorName;
}
