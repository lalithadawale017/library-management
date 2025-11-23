package com.library_management.library_management.entity.service.impl;

import com.library_management.library_management.entity.Book;
import com.library_management.library_management.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.library_management.library_management.repository.BookRepository;
import com.library_management.library_management.entity.service.BookService;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(Long id, Book book) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));
        existingBook.setTitle(book.getTitle());
        existingBook.setAuthor(book.getAuthor());
        existingBook.setIsbn(book.getIsbn());
        existingBook.setCategory(book.getCategory());
        existingBook.setPublishedDate(book.getPublishedDate());
        existingBook.setAvailableCopies(book.getAvailableCopies());
        return bookRepository.save(existingBook);
    }

    @Override
    public void deleteBook(Long id) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));
        bookRepository.delete(existingBook);
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Page<Book> getBooksPaginated(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Override
    public List<Book> searchBooks(String keyword) {
        List<Book> byTitle = bookRepository.findByTitleContainingIgnoreCase(keyword);
        List<Book> byAuthor = bookRepository.findByAuthorContainingIgnoreCase(keyword);
        List<Book> byCategory = bookRepository.findByCategoryContainingIgnoreCase(keyword);
        byTitle.addAll(byAuthor);
        byTitle.addAll(byCategory);
        return byTitle;
    }
}
