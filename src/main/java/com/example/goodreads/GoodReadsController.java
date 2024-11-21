package com.example.goodreads;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.goodreads.Book;
import com.example.goodreads.BookService;
import java.util.*;

@RestController
public class GoodReadsController {
    BookService s = new BookService();

    @GetMapping("/books")
    public ArrayList<Book> getBooks() {
        return s.getBooks();
    }

    @GetMapping("/books/{bookId}")
    public Book getBookbyId(@PathVariable("bookId") int bookId) {
        return s.getBookByid(bookId);
    }

}