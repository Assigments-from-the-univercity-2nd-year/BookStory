package com.example.bookstory.DAO;

import androidx.room.Database;

import com.example.bookstory.DAO.relations.BookAuthorCrossRef.BookAuthorCrossRef;
import com.example.bookstory.DAO.relations.BookCharacterCrossRef.BookCharacterCrossRef;

@Database(
        entities = {
                Author.class,
                Book.class,
                Character.class,
                BookAuthorCrossRef.class,
                BookCharacterCrossRef.class
        },
        version = 1)
public abstract class AppDatabase implements DomainDAO {
        public abstract DomainDAO domainDAO();
}
