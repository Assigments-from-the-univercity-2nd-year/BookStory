package com.example.bookstory.DAO;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Book {
    @PrimaryKey(autoGenerate = true)
    int bookId;

    String bookName;
    int numberOfPages;
    int yearOfPublication;
    String annotation;
}
