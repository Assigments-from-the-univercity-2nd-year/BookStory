package com.example.bookstory.DAO.relations.BookCharacterCrossRef;

import androidx.annotation.NonNull;
import androidx.room.Entity;

@Entity(primaryKeys = {"bookId", "characterName"})
public class BookCharacterCrossRef {
    public long bookId;
    @NonNull
    public String characterName;
    public TypeOfParticipation typeOfParticipation;

    public BookCharacterCrossRef(long bookId, @NonNull String characterName, TypeOfParticipation typeOfParticipation) {
        this.bookId = bookId;
        this.characterName = characterName;
        this.typeOfParticipation = typeOfParticipation;
    }
}
