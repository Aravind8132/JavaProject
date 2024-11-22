package com.example.goodreads;

import java.util.*;
import com.example.goodreads.Book;
import com.example.goodreads.BookRepository;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

public class BookService implements BookRepository {
    private HashMap<Integer, Book> hmap = new HashMap<>();
    int uniqueId = 2;

    public BookService() {
        Book b1 = new Book(1, "harry potter", "harry_potter.jpg");
        Book b2 = new Book(2, "Rise", "rise.jpeg");
        hmap.put(b1.getId(), b1);
        hmap.put(b2.getId(), b2);
    }

    @Override
    public ArrayList<Book> getBooks() {
        Collection<Book> bookCollection = hmap.values();
        ArrayList<Book> books = new ArrayList<>(bookCollection);
        return books;

    }

    @Override
    public Book getBookById(int bookId) {
        Book book = hmap.get(bookId);
        if (book == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return book;

    }

    @Override
    public Book addBook(Book book) {
        book.setId(uniqueId);
        hmap.put(uniqueId, book);
        uniqueId += 1;
        return book;

    }

    public Book updatebook(Book book, int bookId) {
        Book ExistingBook = hmap.get(book.getId());
        if (ExistingBook == null) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
        if (book.getName() != null) {
            ExistingBook.setName(book.getName());
        }
        if (book.getImageUrl() != null) {
            ExistingBook.setImageUrl(book.getImageUrl());
        }

        return ExistingBook;
    }

    @Override
    public void deleteBook(int bookId) {
        Book book = hmap.get(bookId);
        if (book == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            hmap.remove(bookId);
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }

    }

}
