package com.example.bookstory.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.bookstory.DAO.relations.BookAuthorCrossRef.BookAuthorCrossRef;
import com.example.bookstory.DAO.relations.BookAuthorCrossRef.BookWithAuthors;
import com.example.bookstory.DAO.relations.BookCharacterCrossRef.BookCharacterCrossRef;
import com.example.bookstory.DAO.relations.BookCharacterCrossRef.BookWithCharacters;

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

    @Delete
    void deleteAuthor(Author author);

    @Delete
    void deleteBook(Book book);

    @Delete
    void deleteCharacter(Character character);

    @Delete
    void deleteBookAuthorCrossRef(BookAuthorCrossRef bookAuthorCrossRef);

    @Delete
    void deleteBookCharacterCrossRef(BookCharacterCrossRef bookCharacterCrossRefs);

    @Update
    void updateAuthor(Author author);

    @Update
    void updateBook(Book book);

    @Update
    void updateCharacter(Character character);

    @Update
    void updateBookAuthorCrossRef(BookAuthorCrossRef bookAuthorCrossRef);

    @Update
    void updateBookCharacterCrossRef(BookCharacterCrossRef bookCharacterCrossRefs);

    @Transaction
    @Query("SELECT * FROM book")
    List<Book> getBooks();

    @Transaction
    @Query("SELECT * FROM author")
    List<Author> getAuthors();

    @Transaction
    @Query("SELECT * FROM author WHERE authorName = :authorName")
    Author getAuthorByName(String authorName);

    @Transaction
    @Query("SELECT * FROM character")
    List<Character> getCharacters();

    @Transaction
    @Query("SELECT * FROM character WHERE characterName = :characterName")
    Character getCharacterByName(String characterName);

    @Transaction
    @Query("SELECT * FROM book")
    List<BookWithAuthors> getBooksWithAuthors();

    @Transaction
    @Query("SELECT * FROM book WHERE bookId = :bookId")
    BookWithAuthors getBookWithAuthor(int bookId);

    @Transaction
    @Query("SELECT * FROM book WHERE bookId = :bookId")
    BookWithCharacters getBookWithCharacter(int bookId);
}
