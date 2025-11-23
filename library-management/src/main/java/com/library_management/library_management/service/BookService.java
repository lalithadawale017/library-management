package com.library_management.library_management.service;

import com.library_management.library_management.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {

    Book addBook(Book book);

    Book updateBook(Long id, Book book);

    void deleteBook(Long id);

    Book getBookById(Long id);

    List<Book> getAllBooks();

    Page<Book> getBooksPaginated(Pageable pageable);

    List<Book> searchBooks(String keyword);
}
