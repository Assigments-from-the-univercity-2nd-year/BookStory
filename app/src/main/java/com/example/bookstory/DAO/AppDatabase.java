package com.example.bookstory.DAO;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

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
        version = 1,
        exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DomainDAO domainDAO();

    private static volatile AppDatabase INSTANCE = null;

    static AppDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            "app_database"
                    ).build();
                }
            }
        }
        return INSTANCE;
    }
}
