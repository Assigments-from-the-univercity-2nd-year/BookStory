package com.example.bookstory.DAO.relations.BookCharacterCrossRef;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Junction;
import androidx.room.Relation;

import com.example.bookstory.DAO.Book;
import com.example.bookstory.DAO.Character;

import java.util.List;

@Entity
public class BookWithCharacters {
    @Embedded
    public Book book;

    @Relation(
            parentColumn = "bookId",
            entityColumn = "characterName",
            associateBy = @Junction(BookCharacterCrossRef.class)
    )
    public List<Character> characters;
}
