package com.example.goodreads;

import java.util.*;
import com.example.goodreads.Book;
import com.example.goodreads.BookRepository;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

public class BookService implements BookRepository {
    private HashMap<Integer, Book> hmap = new HashMap<>();

    public BookService() {
        Book b1 = new Book(1, "HarryPorter", "Harryporter.jpg");
        Book b2 = new Book(2, "MarvelComic", "MarvelComic.jpg");

        hmap.put(b1.getId(), b1);
        hmap.put(b2.getId(), b2);
    }

    @Override
    public ArrayList<Book> getBooks() {
        Collection<Book> bookCollection = hmap.values();
        ArrayList<Book> listOfBooks = new ArrayList<>(bookCollection);
        return listOfBooks;
    }

    @Override
    public Book getBookByid(int id) {
        Book book = hmap.get(id);
        if (book == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
 
        return book;
    }

}