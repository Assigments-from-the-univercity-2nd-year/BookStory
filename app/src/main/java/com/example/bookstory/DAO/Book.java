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

    public Book(String bookName, int numberOfPages, int yearOfPublication, String annotation) {
        this.bookName = bookName;
        this.numberOfPages = numberOfPages;
        this.yearOfPublication = yearOfPublication;
        this.annotation = annotation;
    }
}
