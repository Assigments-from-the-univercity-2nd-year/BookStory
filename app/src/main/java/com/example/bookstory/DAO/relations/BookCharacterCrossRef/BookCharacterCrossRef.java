package com.example.bookstory.DAO.relations.BookCharacterCrossRef;

import androidx.room.Entity;

@Entity(primaryKeys = {"bookId", "characterName"})
public class BookCharacterCrossRef {
    public int bookId;
    public String characterName;
}
