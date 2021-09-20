package com.example.bookstory.DAO;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Book {
    @PrimaryKey(autoGenerate = true)
    public int bookId;

    public String bookName;
    public int numberOfPages;
    public int yearOfPublication;
    public String annotation;
}
