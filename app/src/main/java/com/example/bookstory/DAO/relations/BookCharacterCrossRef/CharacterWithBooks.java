package com.example.bookstory.DAO.relations.BookCharacterCrossRef;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Junction;
import androidx.room.Relation;

import com.example.bookstory.DAO.Book;
import com.example.bookstory.DAO.Character;
import com.example.bookstory.DAO.relations.BookAuthorCrossRef.BookAuthorCrossRef;

import java.util.List;

@Entity
public class CharacterWithBooks {
    @Embedded
    public Character character;

    @Relation(
            parentColumn = "characterName",
            entityColumn = "bookId",
            associateBy = @Junction(BookAuthorCrossRef.class)
    )
    public List<Book> books;
}
