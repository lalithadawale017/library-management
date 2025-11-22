package repository;

import entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface  BookRepository {

        // Search books by title, author, or category
        List<Book> findByTitleContainingIgnoreCase(String title);

        List<Book> findByAuthorContainingIgnoreCase(String author);

        List<Book> findByCategoryContainingIgnoreCase(String category);
    }


