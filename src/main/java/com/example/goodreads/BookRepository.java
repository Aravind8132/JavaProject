package com.example.goodreads;

import java.util.ArrayList;

public interface BookRepository {
    ArrayList<Book> getBooks();

    Book getBookById(int bookId);

    Book addBook(Book bookId);

    Book updatebook(Book book, int bookId);

    void deleteBook(int bookId);

}
