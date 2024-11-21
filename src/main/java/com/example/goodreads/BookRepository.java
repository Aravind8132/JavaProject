package com.example.goodreads;

import com.example.goodreads.Book;

import java.util.*;

public interface BookRepository {
    ArrayList<Book> getBooks();

    Book getBookByid(int bookId);
}
