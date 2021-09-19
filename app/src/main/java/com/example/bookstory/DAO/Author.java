package com.example.bookstory.DAO;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Author {
    @PrimaryKey(autoGenerate = false)
    public String authorName;
}
