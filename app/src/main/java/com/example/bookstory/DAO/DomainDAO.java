package com.example.bookstory.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.bookstory.DAO.relations.BookAuthorCrossRef.BookAuthorCrossRef;
import com.example.bookstory.DAO.relations.BookAuthorCrossRef.BookWithAuthors;
import com.example.bookstory.DAO.relations.BookCharacterCrossRef.BookCharacterCrossRef;

import java.util.List;

@Dao
public interface DomainDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAuthor(Author... authors);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertBook(Book... books);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertCharacter(Character... characters);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertBookAuthorCrossRef(BookAuthorCrossRef... bookAuthorCrossRef);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertBookCharacterCrossRef(BookCharacterCrossRef... bookCharacterCrossRefs);

    @Transaction
    @Query("SELECT * FROM book")
    List<Book> getBooks();

    @Transaction
    @Query("SELECT * FROM book")
    List<BookWithAuthors> getBooksWithAuthors();

    @Transaction
    @Query("SELECT * FROM book WHERE bookId = :bookId")
    BookWithAuthors getBookWithAuthor(int bookId);

    @Transaction
    @Query("SELECT * FROM book WHERE bookId = :bookId")
    BookWithAuthors getBookWithCharacter(int bookId);
}
