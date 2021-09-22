package com.example.bookstory.DAO.relations.BookCharacterCrossRef;

import androidx.annotation.NonNull;
import androidx.room.Entity;

@Entity(primaryKeys = {"bookId", "characterName"})
public class BookCharacterCrossRef {
    public int bookId;
    @NonNull
    public String characterName;

    public BookCharacterCrossRef(int bookId, @NonNull String characterName) {
        this.bookId = bookId;
        this.characterName = characterName;
    }
}
