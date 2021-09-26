package com.example.bookstory.DOMAIN;

import android.content.Context;

import com.example.bookstory.DAO.AppDatabase;
import com.example.bookstory.DAO.Author;
import com.example.bookstory.DAO.Book;
import com.example.bookstory.DAO.Character;
import com.example.bookstory.DAO.DomainDAO;
import com.example.bookstory.DAO.relations.BookAuthorCrossRef.BookAuthorCrossRef;
import com.example.bookstory.DAO.relations.BookAuthorCrossRef.BookWithAuthors;
import com.example.bookstory.DAO.relations.BookCharacterCrossRef.BookCharacterCrossRef;
import com.example.bookstory.DAO.relations.BookCharacterCrossRef.BookWithCharacters;

import java.util.ArrayList;
import java.util.List;

public class DBController {

    Context context;
    private DomainDAO domainDAO;

    public DBController(Context context) {
        this.context = context;
        domainDAO = AppDatabase.getInstance(context).domainDAO();
    }

    public void insertAuthor(Author author) {
        domainDAO.insertAuthor(author);
    }

    public long insertBook(Book book) {
        return domainDAO.insertBook(book);
    }

    public void insertCharacter(Character character) {
        domainDAO.insertCharacter(character);
    }

    public void insertBookAuthorCrossRef(BookAuthorCrossRef bookAuthorCrossRef) {
        domainDAO.insertBookAuthorCrossRef(bookAuthorCrossRef);
    }

    public void insertBookCharacterCrossRef(BookCharacterCrossRef bookCharacterCrossRef) {
        domainDAO.insertBookCharacterCrossRef(bookCharacterCrossRef);
    }

    public List<Author> getAuthors() {
        return domainDAO.getAuthors();
    }

    public Book getBooksById(long bookId) {
        return domainDAO.getBooksById(bookId);
    }

    public List<String> getAuthorNames() {
        List<String> authorNames = new ArrayList<>();
        for (Author author : getAuthors()) {
            authorNames.add(author.authorName);
        }
        return authorNames;
    }

    public Author getAuthorByName(String authorName) {
        return domainDAO.getAuthorByName(authorName);
    }

    public List<Character> getCharacters() {
        return domainDAO.getCharacters();
    }

    public Character getCharacterByName(String characterName) {
        return domainDAO.getCharacterByName(characterName);
    }

    public List<String> getCharacterNames() {
        List<String> characterNames = new ArrayList<>();
        for (Character character : getCharacters()) {
            characterNames.add(character.characterName);
        }
        return characterNames;
    }

    public List<BookWithAuthors> getBooksWithAuthors() {
        return domainDAO.getBooksWithAuthors();
    }

    public BookWithAuthors getBookWithAuthor(long bookId) {
        return domainDAO.getBookWithAuthor(bookId);
    }

    public BookWithCharacters getBookWithCharacter(long bookId) {
        return domainDAO.getBookWithCharacter(bookId);
    }
}
