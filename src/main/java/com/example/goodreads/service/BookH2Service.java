package com.example.goodreads.service;

import java.util.*;
import com.example.goodreads.model.Book;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import com.example.goodreads.model.BookRowMapper;
import com.example.goodreads.repository.BookRepository;

@Service
public class BookH2Service implements BookRepository {
    @Autowired
    private JdbcTemplate db;

    @Override
    public ArrayList<Book> getBooks() {
        List<Book> Books = db.query("Select * FROM book", new BookRowMapper());
        ArrayList<Book> books = new ArrayList<>(Books);
        return books;
    }

    @Override
    public Book getBookById(int bookId) {
        try {
            Book book = db.queryForObject("Select * FROM book where id = ?", new BookRowMapper(), bookId);
            return book;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Book addBook(Book book) {
        db.update("INSERT INTO book(name , imageUrl) values (?,?)", book.getName(), book.getImageUrl());
        Book savedBook = db.queryForObject("Select * FROM book where name =? and imageUrl= ?", new BookRowMapper(),
                book.getName(), book.getImageUrl());
        return savedBook;
    }

    @Override
    public Book updateBook(int bookId, Book book) {
        if (book.getName() != null) {
            db.update("Update book SET name = ? where id= ?", book.getName(), bookId);

        }
        if (book.getImageUrl() != null) {
            db.update("Update book set imageUrl=? where id =? ", book.getImageUrl(), bookId);
        }
        return getBookById(bookId);
    }

    @Override
    public void deleteBook(int bookId) {
        db.update("DELETE FROM book where id =? ", bookId);

    }

}