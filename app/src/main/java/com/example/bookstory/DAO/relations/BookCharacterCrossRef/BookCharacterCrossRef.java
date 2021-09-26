package com.example.bookstory.DAO.relations.BookCharacterCrossRef;

import androidx.annotation.NonNull;
import androidx.room.Entity;

@Entity(primaryKeys = {"bookId", "characterName"})
public class BookCharacterCrossRef {
    public long bookId;
    @NonNull
    public String characterName;

    public BookCharacterCrossRef(long bookId, @NonNull String characterName) {
        this.bookId = bookId;
        this.characterName = characterName;
    }
}
