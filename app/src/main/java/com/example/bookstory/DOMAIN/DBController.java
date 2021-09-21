package com.example.bookstory.DOMAIN;

import android.content.Context;

import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.example.bookstory.DAO.AppDatabase;
import com.example.bookstory.DAO.Author;
import com.example.bookstory.DAO.Book;
import com.example.bookstory.DAO.Character;
import com.example.bookstory.DAO.DomainDAO;
import com.example.bookstory.DAO.relations.BookAuthorCrossRef.BookAuthorCrossRef;
import com.example.bookstory.DAO.relations.BookAuthorCrossRef.BookWithAuthors;
import com.example.bookstory.DAO.relations.BookCharacterCrossRef.BookCharacterCrossRef;

import java.util.List;

public class DBController {

    Context context;
    private DomainDAO domainDAO;

    public DBController(Context context) {
        this.context = context;
        domainDAO = AppDatabase.getInstance(context).domainDAO();
    }

    public void insertAuthor(Author... authors){
        domainDAO.insertAuthor(authors);
    }

    public void insertBook(Book... books){
        domainDAO.insertBook(books);
    }

    public void insertCharacter(Character... characters){
        domainDAO.insertCharacter(characters);
    }

    public void insertBookAuthorCrossRef(BookAuthorCrossRef... bookAuthorCrossRef){
        domainDAO.insertBookAuthorCrossRef(bookAuthorCrossRef);
    }

    public void insertBookCharacterCrossRef(BookCharacterCrossRef... bookCharacterCrossRefs){
        domainDAO.insertBookCharacterCrossRef(bookCharacterCrossRefs);
    }

    public List<BookWithAuthors> getBooksWithAuthors(){
        return domainDAO.getBooksWithAuthors();
    }

    public BookWithAuthors getBookWithAuthor(int bookId) {
        return domainDAO.getBookWithAuthor(bookId);
    }

    public BookWithAuthors getBookWithCharacter(int bookId) {
        return domainDAO.getBookWithCharacter(bookId);
    }
}
